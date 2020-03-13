package com.fr.adaming.dto;



import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Sexe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantUpdateDto {

	@Positive
	private int identifiant;
	
	private String name;
	
	private String surname;
	
	private String adress;
	
	private int postalCode;
	
	private String city;
	
	private Sexe s;
	
	@NotNull
	private int identity;
	
	private int phone;
	
	@Email
	private String mail;
	
	private Classe classroom; 
	
	private List<Matiere> listMatiere;
	
	
}
