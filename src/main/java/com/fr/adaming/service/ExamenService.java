package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IExamenDao;
import com.fr.adaming.entity.Examen;

@Service
public class ExamenService implements IExamenService {
	@Autowired
	private IExamenDao dao;
	
	@Override
	public Examen create(Examen exam) {
		
			if(exam == null || exam.getDate()==null||dao.existsById(exam.getId())) {
				return null;
			}
			
			
			return dao.save(exam);
		
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
		
			if (exam == null 
					|| !dao.existsById(exam.getId())
					|| exam.getDate()==null) {
					return false;
			}
			dao.save(exam);
			return true;
		
	}

}
