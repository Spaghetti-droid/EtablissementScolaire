package com.fr.adaming.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfert Object pour l'entite Matiere, utilise pour la methode PUT.
 * Contient l'identifiant.
 * @author Matiere
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class MatiereUpdateDto {
	
	@Positive
	private int idMatiere;
	
	@NotBlank
	private String nomMatiere; 
	
	private List<EtudiantUpdateDto> listeEtudiant;

	public MatiereUpdateDto(@Positive int idMatiere, @NotBlank String nomMatiere) {
		super();
		this.idMatiere = idMatiere;
		this.nomMatiere = nomMatiere;
	}
	
		

}
