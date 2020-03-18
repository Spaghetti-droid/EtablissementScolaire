package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IEtudiantDao;
import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;

@Service
public class MatiereService implements IMatiereService {

	@Autowired
	private IMatiereDao dao;
	
	@Autowired
	private IEtudiantDao etuDao;

	public Matiere create(Matiere mat) {

		

			if (mat == null || dao.existsById(mat.getId())) {

				return null;

			} 
			if (!etuDao.exists(mat.getEtudiantList())) {
				return null;
			}

			return dao.save(mat);

			

		

	}

	public List<Matiere> readAll() {
		return dao.findAll();
	}

	public Matiere readById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public Matiere readByNom(String nom) {

		return dao.findByNom(nom);

	}

	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	public boolean deleteById(Integer id) {

		if (dao.existsById(id)) {
			dao.deleteById(id);
			return true;
		} else {
			return false;
		}
	}

	public boolean delete(Matiere mat) {
		dao.delete(mat);
		return false;
	}

	public boolean update(Matiere mat) {

		if (mat == null || !dao.existsById(mat.getId()) || mat.getNom() == null) {

			return false;

		}
		

		dao.save(mat);
		return true;

		

	}

	public List<Examen> readExamenByNomMatiere(String nom) {

		if (nom != null && dao.existsByNom(nom)) {
			return dao.findExamenByNomMatiere(nom);
		} else {
			return null;
		}

	}

}
