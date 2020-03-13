package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Etudiant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nom;
	
	private String prenom;
	
	private String adresse;
	
	private int cp;
	
	private String ville;
	
	private Sexe sexe;
	
	@Column(unique = true)
	private int cni;
	
	private int num;
	
	@Column(unique = true)
	private String email; 
	
	
	
	
	//Associations à gérer ! 
//	@OneToMany
//	private Absence absence;
	
	@ManyToOne
	private Classe classe;
	
	@ManyToMany
	private List<Matiere> matiereList;
	
//	@OneToMany
//	private Note note;

}
