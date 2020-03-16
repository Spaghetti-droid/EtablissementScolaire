package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Matiere;

@Repository
public interface IMatiereDao extends JpaRepository<Matiere, Integer>{
	
	public Matiere findByNom (String nom);
	
	public boolean existsByNom(String nom);
	
	@Query(value = "select * from examen where id_matiere in (select id from matiere where nom = :nom)", nativeQuery = true)
	public Matiere findExamenByNomMatiere(@Param(value = "nom") String nom);

	

}
