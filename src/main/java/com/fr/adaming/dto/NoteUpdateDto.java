package com.fr.adaming.dto;


import javax.validation.constraints.Positive;

import lombok.Data;

/**
 * Data Transfert Object pour l'entite Note, utilise pour la methode PUT.
 * Contient l'identifiant.
 * @author Thierry
 *
 */
@Data
public class NoteUpdateDto {
	
	@Positive
	private int id;
	
	@Positive
	private int value;
	
	
	private EtudiantUpdateDto etudiant;
	
	
	private ExamenUpdateDto examen;

}
