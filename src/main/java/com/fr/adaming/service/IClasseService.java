package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Classe;


public interface IClasseService {

	public List<Classe> readAll();
	
	public Classe readById(int id);
	
	public boolean deleteById(int id);
	
	public Classe create (Classe user);
	
	public boolean update (Classe user);
	
	

}
