package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;
import com.fr.adaming.enumeration.Type;
import lombok.Data;


@Data
public class ExamenUpdateDto {

	
	@NotBlank
	private int idExam;
	
	private String dateExamen;
	
	private Type typeExamen;
	
	private double coefExamen;
	
	private  MatiereUpdateDto matiereExamen;
	
}
