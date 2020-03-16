package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;

import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Type;

import lombok.Data;


@Data
public class ExamenCreateDto {
	
	@NotBlank
	private LocalDate dateExamen;
	
	private Type typeExamen;
	
	private double coefExamen;
	
	@NotBlank
	private String matiereExamen;

}
