package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fr.adaming.enumeration.Sexe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représentation des étudiants.
 * Jointures controllés par cette classe: ManyToOne avec Classe
 * Jointures controllés depuis d'autres classes: ManyToOne depuis Absence, ManyToMany depuis Matiere, ManyToOne depuis Note
 * 
 * @author Isaline
 * @author Lucie
 * @author Grégoire
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

	@Column(unique = true, nullable = false)
	private int cni;
	
	private int num;

	@Column(unique = true, nullable = false)
	private String email;

	@ManyToOne
	private Classe classe;
	
	private String pwd;


	public Etudiant(int id, int cp,  int cni, int num, String email) {
		super();
		this.id = id;
		this.cp=cp;
		this.cni=cni;
		this.num=num;
		this.email=email;
	}
	
	public Etudiant( int cp,  int cni, int num, String email) {
		super();
		
		this.cp=cp;
		this.cni=cni;
		this.num=num;
		this.email=email;
	}



	public Etudiant(String nom, String prenom, int cni, String email) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cni = cni;
		this.email = email;
	}

	public Etudiant(int id, String nom, String prenom, int cni, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cni = cni;
		this.email = email;
	}

	
	

}
