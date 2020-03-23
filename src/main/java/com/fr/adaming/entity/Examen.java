package com.fr.adaming.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fr.adaming.enumeration.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p><b>Représentation des Examens.</b></p>
 * <p>Jointures controllés par cette classe: ManyToOne vers Matiere.</p>
 * <p>Jointures controllés par une autre classe: ManyToOne depuis Note.</p> 
 * 
 * @author Isaline
 * @author Jeanne-Marie
 * @author Grégoire
 *
 */
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
	
	@ManyToOne
	private Matiere matiere;
	
	
	public Examen(LocalDate date, Type type, double coef, Matiere matiere) {
		super();
		this.date = date;
		this.type = type;
		this.coef = coef;
		this.matiere = matiere;
	}


	public Examen(LocalDate date, Type type, double coef) {
		super();
		this.date = date;
		this.type = type;
		this.coef = coef;
	}

}
