package com.fr.adaming.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	private LocalDate date;
	
	private Type type;
	
	private double coef;
	
	@ManyToMany
	private List<Matiere> matiereList;
	
//	@OneToMany
//	private Note note;
	
	public Examen(LocalDate date, Type type, double coef, List<Matiere> matiereList) {
		super();
		this.date = date;
		this.type = type;
		this.coef = coef;
		this.matiereList = matiereList;
	}

}
