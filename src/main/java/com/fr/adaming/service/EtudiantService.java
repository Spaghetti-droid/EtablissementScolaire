package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IEtudiantDao;
import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Note;

@Service
public class EtudiantService implements IEtudiantService {

	@Autowired
	private IEtudiantDao dao;

	@Override
	public Etudiant create(Etudiant etudiant) {
//if ...
		return dao.save(etudiant);
	}

	@Override
	public List<Etudiant> readAll() {

		return dao.findAll();
	}

	@Override
	public Etudiant readById(int id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Etudiant readByEmail(String email) {

		if (email != null && dao.existsByEmail(email)) {
			return dao.findByEmail(email);
		} else {
			return null;
		}
	}

	@Override
	public boolean update(Etudiant etudiant) {
		if (etudiant != null || !dao.existsById(etudiant.getId())) {
			return false;
		} else {
			dao.save(etudiant);
			return true;
		}
	}

	@Override
	public boolean deleteById(int id) {
		if (!dao.existsById(id)) {
			return false;
		}
		dao.deleteById(id);
		return true;
	}

	public boolean existsById(Integer id) {
		if (!dao.existsById(id)) {
			return false;
		}
		return true;
	}

	public List<Note> readNoteByEtudiantEmail(String email) {

		if (email != null && dao.existsByEmail(email)) {

			return dao.findNoteByEtudiantEmail(email);

		} else {

			return null;

		}
	}

	public List<Absence> readAbsenceByEtudiantEmail(String email) {
		
		if (email != null && dao.existsByEmail(email)) {
		
		return dao.findAbsenceByEtudiantEmail(email);
		
		} else {

			return null;

		}
	}
	
	

}
