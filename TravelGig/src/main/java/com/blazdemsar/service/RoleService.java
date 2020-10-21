package com.blazdemsar.service;

import java.util.List;

import com.blazdemsar.domain.Role;

public interface RoleService {
	
	public Role save(Role role);
	public List<Role> findAll();
	public Role findById(Long roleId);
	
}
