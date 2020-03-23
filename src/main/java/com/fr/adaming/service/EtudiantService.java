package com.fr.adaming.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IEtudiantDao;
import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Note;

@Service
public class EtudiantService extends AbstractService<Etudiant>{

	@Autowired
	IEtudiantDao daoEtudiant;

	@Override
	public Etudiant create(Etudiant etudiant) {
		if (etudiant == null || 
				etudiant.getCni() == 0 ||
				etudiant.getEmail() == null ||
				daoEtudiant.existsByEmail(etudiant.getEmail()) ||
				daoEtudiant.existsById(etudiant.getId()) ||
				daoEtudiant.existsByCni(etudiant.getCni())
					) {
				return null; }
		else {
		return daoEtudiant.save(etudiant);}
	}

	

	/**
	 * <b>Methode permettant l'affichage d'un étudiant par son email </b>
	 * @param email Email de l'etudiant à afficher (UNQIUE)
	 * @return l'etudiant recherche
	 */
	public Etudiant readByEmail(String email) {

		if (email != null && daoEtudiant.existsByEmail(email)) {
			return daoEtudiant.findByEmail(email);
		} else {
			return null;
		}
	}

	@Override
	public boolean update(Etudiant etudiant) {
		if (etudiant == null 
				|| !daoEtudiant.existsById(etudiant.getId())
				|| etudiant.getEmail()==null
				|| etudiant.getCni()==0) {
			return false;
		}
		if(daoEtudiant.existsByEmail(etudiant.getEmail())&& daoEtudiant.findByEmail(etudiant.getEmail()).getId()!= etudiant.getId()) {
			return false;
		}
		if(daoEtudiant.existsByCni(etudiant.getCni())&& daoEtudiant.findByCni(etudiant.getCni()).getId() != etudiant.getId()) {
			return false;
		}
		daoEtudiant.save(etudiant);
		return true;
		
	}

	

	
	/**
	 * <b>Methode permettant l'affichage des notes d'un étudiant par son email </b>
	 * @param email Email de l'etudiant
	 * @return Liste des notes de l'etudiant
	 */
	public List<Note> readNoteByEtudiantEmail(String email) {

		if (email != null && daoEtudiant.existsByEmail(email)) {

			return daoEtudiant.findNoteByEtudiantEmail(email);

		} else {

			return Collections.emptyList();

		}
	}

	/**
	 * <b>Methode permettant l'affichage des absences d'un étudiant par son email </b>
	 * @param email Email de l'etudiant
	 * @return Liste des absences de l'etudiant
	 */
	public List<Absence> readAbsenceByEtudiantEmail(String email) {

		if (email != null && daoEtudiant.existsByEmail(email)) {

			return daoEtudiant.findAbsenceByEtudiantEmail(email);

		} else {

			return Collections.emptyList();

		}
	}


}
