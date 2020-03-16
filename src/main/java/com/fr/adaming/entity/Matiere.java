package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Matiere {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nom; 
	
//	@ManyToMany
//	private List<Examen> examenList;
	
	@ManyToMany
	private List<Etudiant> etudiantList;

	public Matiere(String nom, List<Etudiant> etudiantList) {
		super();
		this.nom = nom;
		this.etudiantList = etudiantList;
	}

}
