package com.ags.spring.security.config;

import com.ags.spring.security.web.LoggingAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoggingAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private UserDetailsService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    private AuthenticationFailureHandler authenticationfailureHandler;
    
    @Autowired
    private CsrfAccessDeniedHandler csrfAccessDeniedHandler;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /* standart config */
        /*
    	 http
         .authorizeRequests()
             .antMatchers(
                     "/",
                     "/js/**",
                     "/css/**",
                     "/img/**",
                     "/webjars/**").permitAll()
             .antMatchers("/user/**").hasRole("USER")
             .anyRequest().authenticated()
         .and()
         .formLogin()
             .loginPage("/login")
             .permitAll()
         .and()
         .logout()
             .invalidateHttpSession(true)
             .clearAuthentication(true)
             .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
             .logoutSuccessUrl("/login?logout")
             .permitAll()
         .and()
         .exceptionHandling()
             .accessDeniedHandler(accessDeniedHandler);
        */
        http.authorizeRequests()
        	.antMatchers("/user/**").hasAuthority("user") // for access any page in "/user" has to login as user
	        .antMatchers(
	                "/",
	                "/js/**",
	                "/css/**",
	                "/img/**",
	                "/webjars/**",
	                "/login.html/**"
	         ).permitAll() //any directory allow to access without login
             .anyRequest().fullyAuthenticated() // besides directory whit .permitAll() must login to access them
             .and()
             .exceptionHandling()
                 .accessDeniedHandler(csrfAccessDeniedHandler)
             .and()
                 .sessionManagement()
                 .invalidSessionUrl("/login.html?error=exp-sess")
             .and()
	             .formLogin()
	             .loginPage("/login.html")
	             .successHandler(authenticationSuccessHandler)
	             .failureHandler(authenticationfailureHandler)
	             .permitAll()
             .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login.html?logout")
                .permitAll()
          ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /* standart config */
        /*
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
            .and()
                .withUser("manager").password("password").roles("MANAGER");
         */

    	auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

}