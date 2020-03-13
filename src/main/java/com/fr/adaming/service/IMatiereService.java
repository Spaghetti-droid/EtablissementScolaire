package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Matiere;

public interface IMatiereService {
	
	public Matiere create(Matiere entity);

	public List<Matiere> readAll();

	public Matiere readById(Integer id);

	public boolean existsById(Integer id);

	public boolean deleteById(Integer id);
	
	public boolean update(Matiere matiere);

}
