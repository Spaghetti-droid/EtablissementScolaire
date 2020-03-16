package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;

@Repository
public interface IMatiereDao extends JpaRepository<Matiere, Integer>{
	
	public Matiere findByNom (String nom);
	
	public boolean existsByNom(String nom);
	
	@Query(value = "select * from examen where matiere_id in (select id from matiere where nom = :nom)", nativeQuery = true)
	public List<Examen> findExamenByNomMatiere(@Param(value = "nom") String nom);

	

}
