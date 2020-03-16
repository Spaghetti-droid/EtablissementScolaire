package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Absence;


public interface IAbsenceService {
	
	public Absence create(Absence absence);

	public List<Absence> readAll();

	public Absence readById(Integer id);

	public boolean existsById(Integer id);

	public boolean deleteById(Integer id);
	
	public boolean update(Absence absence);
	
}
