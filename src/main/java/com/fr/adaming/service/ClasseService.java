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
		try {
			if(classe == null || dao.existsById(classe.getId())) {
				return null;
			}
			return dao.save(classe);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return null;
		}
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
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Classe classe) {
		try {
			if (classe == null || !dao.existsById(classe.getId()) ) {
					return false;
			}
			dao.save(classe);
			return true;
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Classe findByNom(String nom) {
		System.out.println("DEBUG");
		return dao.findByNom(nom);
	}

	

}
