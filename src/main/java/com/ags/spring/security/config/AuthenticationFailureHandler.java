package com.ags.spring.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

@Component("authenticationFailureHandler")
public class AuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private Logger logger = LogManager.getLogger(AuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authExc) throws IOException, ServletException {
        logger.fatal("Seseorang Gagal Masuk - alasan:" + authExc.getMessage());
        if (authExc instanceof LockedException) {
            redirectStrategy.sendRedirect(request, response, "/login.html?error=acc-locked");
        } else if (authExc instanceof DisabledException) {
            redirectStrategy.sendRedirect(request, response, "/login.html?error=acc-disabled");
        } else if (authExc instanceof CredentialsExpiredException) {
            redirectStrategy.sendRedirect(request, response, "/login.html?error=acc-credential-expired");
        } else if (authExc instanceof AccountExpiredException) {
            redirectStrategy.sendRedirect(request, response, "/login.html?error=acc-expired");
        } else {
            redirectStrategy.sendRedirect(request, response, "/login.html?error=acc-invalid");
        }
	}
	
}