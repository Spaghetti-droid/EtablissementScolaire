package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Classe;



/**
 * Interface repository pour la class Classe implements JpaRepository<Classe, Integer>
 * @author Gregoire Brebner
 * 
 */

@Repository
public interface IClasseDao extends JpaRepository<Classe, Integer>{
	
	/**
	 * Methode de recherche par nom
	 * @param nom
	 * @return Classe
	 *
	 */

	public Classe findByNom (String nom);
}
