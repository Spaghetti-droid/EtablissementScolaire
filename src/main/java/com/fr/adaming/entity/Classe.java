package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p><b>Classes des étudiants.</b></p> 
 * <p>Jointures controllés par une autre classe: ManyToOne depuis Etudiant.</p>
 * 
 * @author Isaline
 * 
 *
 */

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Classe {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(nullable = false)
	private String nom;
	
	public Classe(String nom) {
		super();
		this.nom = nom;
	} 

}
