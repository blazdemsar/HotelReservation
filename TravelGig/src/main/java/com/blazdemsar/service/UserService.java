package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.User;

public interface UserService {
	
	public User save(User user);
	public User findByUsername(String username);
	public List<User> findAll();
	public boolean existsById(Long userId);
	public void deleteById(Long userId);
	public User findById(Long userId);
	
}
