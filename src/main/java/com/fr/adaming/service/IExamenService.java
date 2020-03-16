package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Examen;

public interface IExamenService {
	
	public Examen create(Examen entity);

	public List<Examen> readAll();

	public Examen readById(Integer id);

	public boolean existsById(Integer id);

	public boolean deleteById(Integer id);

	public boolean update(Examen matiere);

}
