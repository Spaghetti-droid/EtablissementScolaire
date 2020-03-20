package com.fr.adaming.service;

import java.util.List;

public interface IService <E>{
	
	public E create(E entity);

	public List<E> readAll();

	public E readById(Integer id);

	public boolean existsById(Integer id);

	public boolean deleteById(Integer id);
	
	public boolean update(E entite);

}
