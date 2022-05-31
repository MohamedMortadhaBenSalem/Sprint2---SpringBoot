package com.mortadha.vetements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mortadha.vetements.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}