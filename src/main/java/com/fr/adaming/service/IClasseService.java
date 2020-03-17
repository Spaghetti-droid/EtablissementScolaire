package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Classe;

public interface IClasseService {

	public Classe create(Classe entity);

	public List<Classe> readAll();

	public Classe readById(Integer id);

	public boolean existsById(Integer id);

	public boolean deleteById(Integer id);

	public boolean update(Classe matiere);
	
	
	

}
