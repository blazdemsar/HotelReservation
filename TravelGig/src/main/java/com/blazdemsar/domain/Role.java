package com.blazdemsar.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name="role")
public class Role {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long roleId;
    
    private String roleName;
    
    @JsonBackReference  // used to avoid cyclic reference
	@ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<User>();
    
    public Role() {
    	
    }
    
    public Role(String roleName) {
    	super();
    	this.roleName = roleName;
    }

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	/*
	 * @Override public String toString() { return "Role [roleId=" + roleId +
	 * ", roleName=" + roleName + ", users=" + users + "]"; }
	 */
	
}

