package com.fr.adaming.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.entity.Matiere;

@Service
public class MatiereService {

	@Autowired
	private IMatiereDao dao;

	public Matiere save(Matiere entity) {
		return dao.save(entity);
	}

	public List<Matiere> findAll() {
		return dao.findAll();
	}

	public Matiere findById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	public void deleteById(Integer id) {
		dao.deleteById(id);
	}

	public void delete(Matiere entity) {
		dao.delete(entity);
	}
	
	public Matiere update(Matiere matiere) {
		
		return dao.save(matiere);
		
	}
	
	
	
}
