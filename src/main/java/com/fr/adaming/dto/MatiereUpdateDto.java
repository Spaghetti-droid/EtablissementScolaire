package com.fr.adaming.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ManyToMany;

import com.fr.adaming.entity.Etudiant;

import lombok.Data;

@Data
public class MatiereUpdateDto {
	
	private int idMatiere;
	

	private String nomMatiere; 
	
	
	private List<String> emailListMatiere=new ArrayList<String>();

}
