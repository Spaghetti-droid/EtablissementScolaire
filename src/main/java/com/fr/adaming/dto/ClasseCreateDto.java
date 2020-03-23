package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfert Object pour l'entite Classe, utilise pour la methode POST.
 * Ne contient pas l'identifiant.
 * @author Jeanne-Marie
 *
 */
@Data @NoArgsConstructor @AllArgsConstructor
public class ClasseCreateDto {
	
	@NotBlank
	private String name;
	
}
