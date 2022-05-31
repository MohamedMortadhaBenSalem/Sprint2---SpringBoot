package com.mortadha.vetements.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mortadha.vetements.entities.Marque;
import com.mortadha.vetements.entities.Vetement;
    @RepositoryRestResource(path = "rest")
	public interface VetementRepository extends JpaRepository<Vetement, Long> {
    	
    	@Query("select p from Vetement p where p.nomVetement like %:vet%")
		List<Vetement> findByNomVetement(@Param("vet") String nom);
    	
		List<Vetement> findByNomVetementContains(String nom);
		List<Vetement> findByMarqueIdMar(Long id);
		List<Vetement> findByOrderByNomVetementAsc();
		
		@Query("select p from Vetement p where p.nomVetement like %:nom and p.prixVetement > :prix")
		List<Vetement> findByNomPrix (@Param("nom") String nom,@Param("prix") Double prix);
		
		@Query("select p from Vetement p where p.marque = ?1")
		List<Vetement> findByMarque (Marque marque);
		
		@Query("select p from Vetement p order by p.nomVetement ASC, p.prixVetement DESC")
		List<Vetement> trierVetementsNomsPrix ();
		
		@Query("select p from Vetement p, Marque m where p.marque.idMar = m.idMar and m.nomMar like %:nomMar%")
		List<Vetement> findByNomMarque(@Param("nomMar") String nomMar);
		
		
	}
	
