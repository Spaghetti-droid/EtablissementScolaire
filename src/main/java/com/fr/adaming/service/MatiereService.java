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
	private IMatiereDao daoMatiere;
	
	@Autowired
	private IEtudiantDao daoEtudiant;
	

	public Matiere create(Matiere mat) {

			if (mat == null || daoMatiere.existsById(mat.getId())|| mat.getNom()==null) {
				return null;
			} 
			List<Etudiant> entryEtu = mat.getEtudiantList();
			List<Etudiant> etudiantsValides = new ArrayList<>();
			for(Etudiant e : entryEtu) {
				if(daoEtudiant.existsById(e.getId())) etudiantsValides.add(e);
			}
			mat.setEtudiantList(etudiantsValides);

			return daoMatiere.save(mat);

			

		

	}

	

	public Matiere readByNom(String nom) {

		return daoMatiere.findByNom(nom);

	}

	
	public boolean update(Matiere mat) {

		if (mat == null || !daoMatiere.existsById(mat.getId()) || mat.getNom() == null) {
			return false;
		}
		List<Etudiant> entryEtu = mat.getEtudiantList();
		List<Etudiant> etudiantsValides = new ArrayList<>();
		if(entryEtu!=null) {
		for(Etudiant e : entryEtu) {
			if(daoEtudiant.existsById(e.getId())) etudiantsValides.add(e);
		}}
		mat.setEtudiantList(etudiantsValides);

		daoMatiere.save(mat);
		return true;

		

	}

	public List<Examen> readExamenByNomMatiere(String nom) {

		if (nom != null && daoMatiere.existsByNom(nom)) {
			return daoMatiere.findExamenByNomMatiere(nom);
		} else {
			return Collections.emptyList();
		}

	}

}
