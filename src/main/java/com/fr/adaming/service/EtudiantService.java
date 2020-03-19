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
	IEtudiantDao dao;

	@Override
	public Etudiant create(Etudiant etudiant) {
		if (etudiant == null || 
				etudiant.getCni() == 0 ||
				etudiant.getEmail() == null ||
				dao.existsByEmail(etudiant.getEmail()) ||
				dao.existsById(etudiant.getId()) ||
				dao.existsByCni(etudiant.getCni())
					) {
				return null; }
		else {
		return dao.save(etudiant);}
	}

	

	public Etudiant readByEmail(String email) {

		if (email != null && dao.existsByEmail(email)) {
			return dao.findByEmail(email);
		} else {
			return null;
		}
	}

	@Override
	public boolean update(Etudiant etudiant) {
		if (etudiant == null 
				|| !dao.existsById(etudiant.getId())
				|| etudiant.getEmail()==null
				|| etudiant.getCni()==0) {
			return false;
		}
		if(dao.existsByEmail(etudiant.getEmail())&& dao.findByEmail(etudiant.getEmail()).getId()!= etudiant.getId()) {
			return false;
		}
		if(dao.existsByCni(etudiant.getCni())&& dao.findByCni(etudiant.getCni()).getId() != etudiant.getId()) {
			return false;
		}
		dao.save(etudiant);
		return true;
		
	}

	

	
	public List<Note> readNoteByEtudiantEmail(String email) {

		if (email != null && dao.existsByEmail(email)) {

			return dao.findNoteByEtudiantEmail(email);

		} else {

			return Collections.emptyList();

		}
	}


	public List<Absence> readAbsenceByEtudiantEmail(String email) {

		if (email != null && dao.existsByEmail(email)) {

			return dao.findAbsenceByEtudiantEmail(email);

		} else {

			return Collections.emptyList();

		}
	}


}
