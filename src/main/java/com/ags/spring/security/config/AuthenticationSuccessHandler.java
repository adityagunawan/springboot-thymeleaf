package com.ags.spring.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import com.ags.spring.security.Role.RoleEnum;
import com.ags.spring.security.model.User;

@Component("authenticationSuccessHandler")
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException, ServletException {
		if (response.isCommitted()) {
			return;
		}
		
		User user = (User) auth.getPrincipal();
		if(user.hasAnyRole(RoleEnum.USER)) {
			//set class untuk disimpan di session
			//request.getSession().setAttribute("nama_attribut_session", objectYangMauDisimpan);
			
			//cara ngambil class yang ada di session
			//ObjectYangMauDisimpan object =  (ObjectYangMauDisimpan) request.getSession().getAttribute("nama_attribute_session")
			redirectStrategy.sendRedirect(request, response, "/user/welcome.html");
		}
	}

	
}