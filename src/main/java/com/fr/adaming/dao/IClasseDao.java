package com.fr.adaming.dao;

import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Classe;

@Repository
public interface IClasseDao extends IDao<Classe> {

	public Classe findByNom (String nom);
}
