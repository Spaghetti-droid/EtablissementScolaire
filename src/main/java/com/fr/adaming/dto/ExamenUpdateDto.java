package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fr.adaming.enumeration.Type;

import lombok.Data;


@Data
public class ExamenUpdateDto {

	
	@NotBlank
	private int idExam;
	
	private LocalDate dateExamen;
	
	private Type typeExamen;
	
	private double coefExamen;
	
	private String matiereExamen;
	
}
