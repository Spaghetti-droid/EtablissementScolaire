package com.fr.adaming.dto;

import java.time.LocalDate;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Type;

import lombok.Data;


@Data
public class ExamenCreateDto {
	
	private LocalDate dateExamen;
	
	private Type typeExamen;
	
	private double coefExamen;
	
	private String matiereExamen;

}
