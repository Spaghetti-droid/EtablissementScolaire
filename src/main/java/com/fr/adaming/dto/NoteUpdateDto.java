package com.fr.adaming.dto;


import javax.validation.constraints.Positive;

import lombok.Data;

@Data
public class NoteUpdateDto {
	
	@Positive
	private int id;
	
	@Positive
	private int value;
	
	
	private EtudiantUpdateDto etudiant;
	
	
	private ExamenUpdateDto examen;

}
