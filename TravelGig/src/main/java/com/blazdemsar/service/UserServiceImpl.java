package com.blazdemsar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blazdemsar.domain.User;
import com.blazdemsar.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	

	@Override
	public User save(User user) {

		String password = user.getPassword();
		
		user.setPassword(passwordEncoder.encode(password));

		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public boolean existsById(Long userId) {
		return false;
	}

	@Override
	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
	}

	@Override
	public User findById(Long userId) {
		
		Optional<User> optUser = userRepository.findById(userId);
		
		if(optUser.isPresent()) {
			
			return optUser.get();
			
		}
		
		return null;
	}

}
