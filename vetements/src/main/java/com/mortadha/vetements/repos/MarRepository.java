package com.mortadha.vetements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mortadha.vetements.entities.Marque;

@Repository
public interface MarRepository extends JpaRepository<Marque, Long>{

}