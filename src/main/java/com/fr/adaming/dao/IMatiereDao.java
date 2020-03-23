package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;

/**
 * Interface repository pour la class Matiere implements JpaRepository<Matiere, Integer>
 * @author Gregoire Brebner
 * 
 */
@Repository
public interface IMatiereDao extends JpaRepository<Matiere, Integer>{
	
	/**
	 * Methode de recherche par nom
	 * @param nom
	 * @return Matiere
	 *
	 */
	public Matiere findByNom (String nom);
	
	/**
	 * Methode de existance par nom
	 * @param nom
	 * @return boolean
	 *
	 */
	public boolean existsByNom(String nom);
	
	/**
	 * Methode de recherche d'Examen par nom de Matiere
	 * @param nom
	 * @return List<Examen>
	 *
	 */
	@Query(value = "From Examen where matiere_id in (select id from Matiere where nom = :nom)")
	public List<Examen> findExamenByNomMatiere(@Param(value = "nom") String nom);

	

}
