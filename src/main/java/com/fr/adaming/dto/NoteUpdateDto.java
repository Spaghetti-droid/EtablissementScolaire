package com.fr.adaming.dto;


import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class NoteUpdateDto {
	
	@Positive
	private int id;
	
	@Positive
	private int value;
	
	@NotNull
	private EtudiantUpdateDto etudiant;
	
	@NotNull
	private ExamenUpdateDto examen;

}
