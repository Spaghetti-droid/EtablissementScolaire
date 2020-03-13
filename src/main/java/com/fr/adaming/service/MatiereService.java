package com.fr.adaming.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.entity.Matiere;

@Service
public class MatiereService implements IMatiereService{

	@Autowired
	private IMatiereDao dao;

	public Matiere create(Matiere entity) {
		
		return dao.save(entity);
		
	}

	public List<Matiere> readAll() {
		return dao.findAll();
	}

	public Matiere readById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	public boolean deleteById(Integer id) {
		dao.deleteById(id);
		return false;
	}

	public boolean delete(Matiere entity) {
		dao.delete(entity);
		return false;
	}
	
	public boolean update(Matiere matiere) {
		
		dao.save(matiere);
		
		return true;
		

		
	}	
	
	
}
