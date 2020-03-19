package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractService<E> implements IService<E>  {
	
	@Autowired
	JpaRepository<E, Integer> dao;

	@Override
	public List<E> readAll() {
		return dao.findAll();
	}

	@Override
	public E readById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public boolean deleteById(Integer id) {
		try{
			if(!dao.existsById(id)) {
			return false;
		}
		dao.deleteById(id);
		return true;}
		catch(DataIntegrityViolationException e) {
			log.error("Erreur de suppression" +e);
			return false;
		}
		
	}
}
