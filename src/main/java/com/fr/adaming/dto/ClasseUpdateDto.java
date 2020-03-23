package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfert Object pour l'entite Classe, utilise pour la methode PUT.
 * Contient l'identifiant.
 * @author Jeanne-Marie
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseUpdateDto {
	
	@Positive
	private int id;
	
	@NotBlank
	private String name;
	
}
