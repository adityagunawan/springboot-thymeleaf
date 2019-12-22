package com.ags.spring.security.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{
	private String authority;
	
	public Role() {}
	
	public Role(String authority) {
        setAuthority(authority);
    }

	@Override
	public String getAuthority() {
		return authority;
	}
	
	 public void setAuthority(String authority) {
	        this.authority = authority;
	    }

}
