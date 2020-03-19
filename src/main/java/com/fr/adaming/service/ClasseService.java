package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IClasseDao;
import com.fr.adaming.entity.Classe;

@Service
public class ClasseService implements IClasseService {

	@Autowired
	private IClasseDao dao;

	@Override
	public Classe create(Classe classe) {

		if (classe == null || dao.existsById(classe.getId()) || classe.getNom() == null) {
			return null;
		}
		return dao.save(classe);

	}

	@Override
	public List<Classe> readAll() {
		return dao.findAll();
	}

	@Override
	public Classe readById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public boolean deleteById(Integer id) {
		if (existsById(id)) {
			dao.deleteById(id);
			return true;
		}
		return false;

	}

	@Override
	public boolean update(Classe classe) {

		if (classe == null || !dao.existsById(classe.getId())) {
			return false;
		}
		
		dao.save(classe);
		return true;

	}

}
