package com.blazdemsar.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blazdemsar.domain.Role;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username: "+username);
		
		com.blazdemsar.domain.User userEntity = userService.findByUsername(username);
		
		System.out.println(userEntity);
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		
		for (Role role : userEntity.getRoles() ) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
			System.out.println("role: "+role);
		}
		
		return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), authorities);
		//return new User(userEntity.getName(), userEntity.getPassword(), authorities);
	}

}
