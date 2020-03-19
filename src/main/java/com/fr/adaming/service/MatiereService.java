package com.fr.adaming.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IEtudiantDao;
import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;

@Service
public class MatiereService extends AbstractService<Matiere> {

	@Autowired
	private IMatiereDao dao;
	
	@Autowired
	private IEtudiantDao etuDao;
	

	public Matiere create(Matiere mat) {

		

			if (mat == null || dao.existsById(mat.getId())|| mat.getNom()==null) {

				return null;

			} 
			List<Etudiant> entryEtu = mat.getEtudiantList();
			List<Etudiant> etudiantsValides = new ArrayList<>();
			for(Etudiant e : entryEtu) {
				if(etuDao.existsById(e.getId())) etudiantsValides.add(e);
			}
			mat.setEtudiantList(etudiantsValides);

			return dao.save(mat);

			

		

	}

	

	public Matiere readByNom(String nom) {

		return dao.findByNom(nom);

	}

	

	public boolean delete(Matiere mat) {
		dao.delete(mat);
		return false;
	}

	public boolean update(Matiere mat) {

		if (mat == null || !dao.existsById(mat.getId()) || mat.getNom() == null) {

			return false;

		}
		List<Etudiant> entryEtu = mat.getEtudiantList();
		List<Etudiant> etudiantsValides = new ArrayList<>();
		if(entryEtu!=null) {
		for(Etudiant e : entryEtu) {
			if(etuDao.existsById(e.getId())) etudiantsValides.add(e);
		}}
		mat.setEtudiantList(etudiantsValides);

		dao.save(mat);
		return true;

		

	}

	public List<Examen> readExamenByNomMatiere(String nom) {

		if (nom != null && dao.existsByNom(nom)) {
			return dao.findExamenByNomMatiere(nom);
		} else {
			return Collections.emptyList();
		}

	}

}
