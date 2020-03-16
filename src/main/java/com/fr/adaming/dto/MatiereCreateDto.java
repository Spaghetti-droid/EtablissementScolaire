package com.fr.adaming.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MatiereCreateDto {

	
	private String nomMatiere; 
	
	
	private List<String> emailListMatiere=new ArrayList<String>();
}
