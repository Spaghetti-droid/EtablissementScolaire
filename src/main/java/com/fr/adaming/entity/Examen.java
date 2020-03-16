package com.fr.adaming.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fr.adaming.enumeration.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Examen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private LocalDate date;
	
	private Type type;
	
	private double coef;
	
	@ManyToOne()
	private Matiere matiere;
	
//	@OneToMany
//	private Note note;
	
	public Examen(LocalDate date, Type type, double coef, Matiere matiere) {
		super();
		this.date = date;
		this.type = type;
		this.coef = coef;
		this.matiere = matiere;
	}

}
