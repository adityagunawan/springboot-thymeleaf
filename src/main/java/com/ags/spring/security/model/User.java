package com.ags.spring.security.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.ags.spring.security.Role.RoleEnum;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
	
	private String username;                    // max length 50 chars
    private String password; // BCrypted 12345
    private String name;                        // max length 50 chars nama PT
    private boolean enabled = false;                    // boolean
    private boolean accountNonExpired = false;          // boolean
    private boolean accountNonLocked = false;           // boolean
    private boolean credentialsNonExpired = false;      // boolean
    private Role role;
    
    @Override
    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
        authorities.add(role);
        return authorities;
    }

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return credentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
	 public boolean hasRole(RoleEnum roleEnum) {
	        return role.getAuthority().equalsIgnoreCase(roleEnum.getValue());
	    }
	    public boolean hasRole(String authority) {
	        return role.getAuthority().equalsIgnoreCase(authority);
	    }

	    public boolean hasAnyRole(RoleEnum ... roleEnums) {
	        for (RoleEnum roleEnum : roleEnums) {
	            if (role.getAuthority().equalsIgnoreCase(roleEnum.getValue())) {
	                return true;
	            }
	        }
	        return false;
	    }

	    public boolean hasAnyRole(String ... authorities) {
	        for (String authority : authorities) {
	            if (role.getAuthority().equalsIgnoreCase(authority)) {
	                return true;
	            }
	        }
	        return false;
	    }

	    public void setRole(RoleEnum roleEnum) {
	        setRole(roleEnum.getRole());
	    }
	    
	    public Role getRole() {
	        return role;
	    }
	    public void setRole(Role role) {
	        this.role = role;
	    }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public void setAccountNonExpired(boolean accountNonExpired) {
			this.accountNonExpired = accountNonExpired;
		}

		public void setAccountNonLocked(boolean accountNonLocked) {
			this.accountNonLocked = accountNonLocked;
		}

		public void setCredentialsNonExpired(boolean credentialsNonExpired) {
			this.credentialsNonExpired = credentialsNonExpired;
		}
	    
	    
}
