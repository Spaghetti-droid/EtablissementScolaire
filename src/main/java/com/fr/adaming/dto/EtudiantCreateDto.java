package com.fr.adaming.dto;

import com.fr.adaming.entity.Sexe;

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
	
	private int identity;
	
	private int phone;
	
	private String mail;

	
}
