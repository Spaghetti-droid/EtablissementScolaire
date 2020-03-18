package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fr.adaming.enumeration.Sexe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

	@ManyToMany
	@JoinTable(name = "etudiant_matiere_list")
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

	public Etudiant(int id, String nom, String prenom, Classe classe) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.classe = classe;
	}

	public Etudiant(String nom, String prenom, String adresse, int cp, String ville, Sexe sexe, int cni, int num,
			String email) {
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

	public Etudiant(int id, String nom, String prenom, String adresse, int cp, String ville, Sexe sexe, int cni, int num,
			String email) {
		
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.sexe = sexe;
		this.cni = cni;
		this.num = num;
		this.email = email;
	}
	

}
