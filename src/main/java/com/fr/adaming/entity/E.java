package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class E {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String nom; 
	
	@ManyToMany
	@JoinTable(name = "etudiant_matiere_list")
	private List<Etudiant> etudiantList;

	public E(String nom, List<Etudiant> etudiantList) {
		super();
		this.nom = nom;
		this.etudiantList = etudiantList;
	}

	public E(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public E(String nom) {
		super();
		this.nom = nom;
	}
	
	

}
