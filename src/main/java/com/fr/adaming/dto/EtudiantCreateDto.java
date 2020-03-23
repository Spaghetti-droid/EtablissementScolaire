package com.fr.adaming.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fr.adaming.enumeration.Sexe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfert Object pour l'entite Etudiant, utilisé pour la methode POST.
 * Ne contient pas l'identifiant.
 * @author Lucie
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantCreateDto {

	private String name;

	private String surname;

	private String adress;

	private int postalCode;

	private String city;

	private Sexe s;

	@Positive
	private int identity;

	private int phone;

	@Email
	@NotNull
	@NotBlank
	@NotEmpty
	private String mail;

	private ClasseUpdateDto classe;

}
