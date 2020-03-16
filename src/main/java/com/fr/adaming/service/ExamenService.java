package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;


import com.fr.adaming.dao.IExamenDao;
import com.fr.adaming.entity.Examen;


public class ExamenService implements IExamenService {
	@Autowired
	private IExamenDao dao;
	
	@Override
	public Examen create(Examen exam) {
		try {
			if(exam == null) {
				return null;
			}
			return dao.save(exam);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Examen> readAll() {
		return dao.findAll();
	}

	@Override
	public Examen readById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public boolean deleteById(Integer id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Examen exam) {
		try {
			if (exam == null ) {
					return false;
			}
			dao.save(exam);
			return true;
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return false;
		}
	}

}
