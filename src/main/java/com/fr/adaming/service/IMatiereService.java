package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;

public interface IMatiereService extends IService<Matiere>{

	
	public Matiere readByNom(String nom);


	
	public List<Examen> readExamenByNomMatiere(String nom);

}
