package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IEtudiantDao;
import com.fr.adaming.entity.Etudiant;

@Service
public class EtudiantService implements IEtudiantService {

	@Autowired
	private IEtudiantDao dao;

	@Override
	public Etudiant create(Etudiant etudiant) {

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

//	@Override
//	public Etudiant readByEmail(String email) {
//		return dao.find;
//	}

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
	public boolean delete(Etudiant etudiant) {

		if (etudiant == null || !dao.existsById(etudiant.getId())) {
			return false;
		} else {

			try {
				dao.delete(etudiant);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}

	@Override
	public boolean deleteById(int id) {
		if(!dao.existsById(id)) {
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

}
