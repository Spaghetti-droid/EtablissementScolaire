package com.fr.adaming.dto;




import java.util.List;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String name;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String surname;
	
	private String adress;
	
	private int postalCode;
	
	private String city;
	
	private Sexe s;
	
	@Positive
	private int identity;
	
	private int phone;
	
	@Email
	@NotNull
	private String mail;
	
	private ClasseUpdateDto classe;

	private List<MatiereUpdateDto> matiere;
	
}
