package com.fr.adaming.dto;

import javax.validation.constraints.Positive;

import com.fr.adaming.enumeration.Type;
import lombok.Data;


@Data
public class ExamenUpdateDto {

	
	@Positive
	private int idExam;
	
	private String dateExamen;
	
	private Type typeExamen;
	
	private double coefExamen;
	
	private  MatiereUpdateDto matiereExamen;
	
}
