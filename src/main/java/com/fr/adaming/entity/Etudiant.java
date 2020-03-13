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

import com.fr.adaming.enumeration.Sexe;

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
	
	@ManyToOne
	private Classe classe;
	
	@ManyToMany
	private List<Matiere> matiereList;
	
//	@OneToMany
//	private Absence absence;
		
//	@OneToMany
//	private Note note;

	public Etudiant(String nom, String prenom, String adresse, int cp, String ville, Sexe sexe, int cni, int num,
			String email, Classe classe, List<Matiere> matiereList) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.sexe = sexe;
		this.cni = cni;
		this.num = num;
		this.email = email;
		this.classe = classe;
		this.matiereList = matiereList;
	}
	
}
