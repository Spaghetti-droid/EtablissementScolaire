package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Note {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private int valeur;
	
	@Column(nullable = false)
	@ManyToOne
	private Etudiant etudiant;
	
	@Column(nullable = false)
	@ManyToOne
	private Examen examen;

	public Note(int valeur, Etudiant etudiant, Examen examen) {
		super();
		this.valeur = valeur;
		this.etudiant = etudiant;
		this.examen = examen;
	}

}
