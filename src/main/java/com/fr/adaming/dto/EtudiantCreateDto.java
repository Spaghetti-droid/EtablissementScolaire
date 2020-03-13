package com.fr.adaming.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fr.adaming.enumeration.Sexe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantCreateDto {

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

	
}
