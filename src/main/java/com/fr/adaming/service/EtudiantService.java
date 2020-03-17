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
	private IEtudiantDao etuDao;

	@Override
	public Etudiant create(Etudiant etudiant) {
		if (etudiant == null || 
				etudiant.getCni() == 0 ||
				etudiant.getEmail() == null ||
				etuDao.existsByEmail(etudiant.getEmail()) ||
				etuDao.existsById(etudiant.getId()) ||
				etuDao.existsByCni(etudiant.getCni())
					) {
				return null; }
		else {
		return etuDao.save(etudiant);}
	}

	@Override
	public List<Etudiant> readAll() {

		return etuDao.findAll();
	}

	@Override
	public Etudiant readById(int id) {
		return etuDao.findById(id).orElse(null);
	}

	@Override
	public Etudiant readByEmail(String email) {

		if (email != null && etuDao.existsByEmail(email)) {
			return etuDao.findByEmail(email);
		} else {
			return null;
		}
	}

	@Override
	public boolean update(Etudiant etudiant) {
		if (etudiant == null 
				|| !etuDao.existsById(etudiant.getId())
				|| etudiant.getEmail()==null
				|| etudiant.getCni()==0) {
			return false;
		}
		if(etuDao.existsByEmail(etudiant.getEmail())&& etuDao.findByEmail(etudiant.getEmail()).getId()!= etudiant.getId()) {
			return false;
		}
		if(etuDao.existsByCni(etudiant.getCni())&& etuDao.findByCni(etudiant.getCni()).getId() != etudiant.getId()) {
			return false;
		}
		etuDao.save(etudiant);
		return true;
		
	}

	@Override
	public boolean deleteById(int id) {
		if (!etuDao.existsById(id)) {
			return false;
		}
		etuDao.deleteById(id);
		return true;
	}

	public boolean existsById(Integer id) {
		if (!etuDao.existsById(id)) {
			return false;
		}
		return true;
	}

	@Override
	public List<Note> readNoteByEtudiantEmail(String email) {

		if (email != null && etuDao.existsByEmail(email)) {

			return etuDao.findNoteByEtudiantEmail(email);

		} else {

			return null;

		}
	}

	@Override
	public List<Absence> readAbsenceByEtudiantEmail(String email) {

		if (email != null && etuDao.existsByEmail(email)) {

			return etuDao.findAbsenceByEtudiantEmail(email);

		} else {

			return null;

		}
	}


}
