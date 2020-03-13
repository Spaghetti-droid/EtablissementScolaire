package com.fr.adaming.entity;

import java.time.LocalDate;

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
public class Absence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private LocalDate dateDebut;
	
	private LocalDate dateFin;
	
	private String justification; 
	
	private String description;
	
//	@ManyToOne
//	private Etudiant etudiant;

	public Absence(LocalDate dateDebut, LocalDate dateFin, String justification, String description,
			Etudiant etudiant) {
		super();
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.justification = justification;
		this.description = description;
	}

}
