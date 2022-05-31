package com.mortadha.vetements.service;

import java.util.List;

import com.mortadha.vetements.entities.Role;


public interface RoleService {
	 List <Role> findAll();
	    
	 Role saveRole(Role r);
	 Role updateRole(Role r);
	 Role getRole (Long idRole);
}