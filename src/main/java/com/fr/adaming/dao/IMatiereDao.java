package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;

/**
 * Interface repository pour la class Matiere implements JpaRepository avec parametres Absence et Integer
 * @author Gregoire
 * 
 */
@Repository
public interface IMatiereDao extends JpaRepository<Matiere, Integer>{
	
	/**
	 * Methode de recherche par nom
	 * @param nom nom de la personne a rechercher
	 * @return Matiere
	 *
	 */
	public Matiere findByNom (String nom);
	
	/**
	 * Methode de existance par nom
	 * @param nom nom de la personne à tester
	 * @return boolean
	 *
	 */
	public boolean existsByNom(String nom);
	
	/**
	 * Methode de recherche d'Examen par nom de Matiere
	 * @param nom nom de la matiere a rechercher
	 * @return List d'Examen
	 *
	 */
	@Query(value = "From Examen where matiere_id in (select id from Matiere where nom = :nom)")
	public List<Examen> findExamenByNomMatiere(@Param(value = "nom") String nom);

	

}
