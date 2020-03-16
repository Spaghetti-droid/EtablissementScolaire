package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Etudiant;

public interface IEtudiantService {

		//create
		public Etudiant create(Etudiant etudiant);
		
		//readAll
		public List<Etudiant> readAll();
		
		//readById
		public Etudiant readById(int id);
		
		//readByEmail
		public Etudiant readByEmail(String email);
		
		//update
		public boolean update(Etudiant etudiant);
		
		//deleteById
		public boolean deleteById(int id);
		
		public boolean existsById(Integer id);
	}

