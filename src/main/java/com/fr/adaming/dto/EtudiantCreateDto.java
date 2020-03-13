package com.fr.adaming.dto;

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
	
	private String s;
	
	private int identity;
	
	private int phone;
	
	private String mail;

	
}
