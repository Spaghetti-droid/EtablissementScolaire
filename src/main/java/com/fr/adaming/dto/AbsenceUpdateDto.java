package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfert Object pour l'entite Absence, utilise pour la methode PUT.
 * Contient l'identifiant.
 * @author Isaline
 *
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class AbsenceUpdateDto {
	
	@Positive
	private int identifiant;
	
	@NotBlank
	private String dateStart;
	
	private String dateEnd;
	
	private String justif;
	
	private String descript;
	
	private EtudiantUpdateDto etudiant;

}
