package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Matiere;

@Repository
public interface IMatiereDao extends JpaRepository<Matiere, Integer>{
	
	public Matiere findByNom (String nom);

}
