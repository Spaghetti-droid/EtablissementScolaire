package com.fr.adaming.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Note;


/**
 * Interface repository pour la class Etudiant implementant JpaRepository pour  Etudiant, Integer
 * @author Gregoire
 * 
 */
@Repository
public interface IEtudiantDao extends JpaRepository<Etudiant, Integer> {
	
	/**
	 * Methode de existance par email
	 * @param email de la personne a rechercher
	 * @return boolean
	 *
	 */
	public boolean existsByEmail(String email);
	
	/**
	 * Methode de recherche par email
	 * @param email de la personne a rechercher
	 * @return Etudiant
	 *
	 */
	public Etudiant findByEmail(String email);
	
	/**
	 * Methode de recherche par cni
	 * @param cni de la personne a rechercher
	 * @return Etudiant
	 *
	 */
	public Etudiant findByCni(int cni);
	
	/**
	 * Methode de existance par cni
	 * @param cni de la personne a rechercher
	 * @return boolean
	 *
	 */
	public boolean existsByCni(int cni);
	
	
	/**
	 * Methode de recherche de Note par email Etudiant
	 * @param email de la personne concernée
	 * @return List de Note
	 *
	 */
	@Query(value = "From Note where etudiant_id in (select id from Etudiant where email = :email)")
	public List<Note> findNoteByEtudiantEmail(@Param(value = "email") String email);
	
	/**
	 * Methode de recherche d'Absence par email Etudiant
	 * @param email de la personne concernée
	 * @return List d'Absence
	 *
	 */
	@Query(value = "From Absence where etudiant_id in (select id from Etudiant where email = :email)")
	public List<Absence> findAbsenceByEtudiantEmail(@Param(value = "email") String email);

}
