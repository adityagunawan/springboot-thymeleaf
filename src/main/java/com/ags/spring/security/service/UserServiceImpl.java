package com.ags.spring.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ags.spring.security.model.Role;
import com.ags.spring.security.model.User;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDetails user = getUser();
		UserDetails nullUser = new User();
		if (username.equals(user.getUsername())) {
			return user;
		} else {
			return nullUser;
		}
		
	}
	
	@Override
	public User getUser() {
		User user = new User();
		user.setName("aditya gunawan saputra");
		user.setUsername("adit");
		user.setPassword("$2a$10$nLGAaDJ4BHVJxf4Vac7AJeCtn9SyXKdTy0lI.37n4EP14RJikSWVy"); //12345
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		Role role = new Role("user");
		user.setRole(role);
		return user;
	}
	

}
