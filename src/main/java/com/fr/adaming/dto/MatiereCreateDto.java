package com.fr.adaming.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class MatiereCreateDto {

	@NotBlank
	private String nomMatiere; 
	
	private List<EtudiantUpdateDto> listeEtudiant;
}
