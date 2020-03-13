package com.fr.adaming.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Classe {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String nom; 
	
	//Association à gérer
	
	@OneToOne
	private Etudiant etudiant;

}
