package com.fr.adaming.dto;


import javax.validation.constraints.Positive;

import lombok.Data;

/**
 * Data Transfert Object pour l'entite Note, utilise pour la methode POST.
 * Ne contient pas l'identifiant.
 * @author Thierry
 *
 */
@Data
public class NoteCreateDto {
	
	@Positive
	private int value; 
	

	private EtudiantUpdateDto etudiant;
	

	private ExamenUpdateDto examen;

}
