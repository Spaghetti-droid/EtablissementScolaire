package com.fr.adaming.deprecated;

import java.util.List;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.service.IService;

public interface IMatiereService extends IService<Matiere>{

	
	public Matiere readByNom(String nom);

	public List<Examen> readExamenByNomMatiere(String nom);

}
