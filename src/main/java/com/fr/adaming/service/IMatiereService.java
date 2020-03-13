package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Matiere;

public interface IMatiereService {
	
	public Matiere save(Matiere entity);

	public List<Matiere> findAll();

	public Matiere findById(Integer id);

	public boolean existsById(Integer id);

	public boolean deleteById(Integer id);

	public boolean delete(Matiere entity);
	
	public Matiere update(Matiere matiere);

}
