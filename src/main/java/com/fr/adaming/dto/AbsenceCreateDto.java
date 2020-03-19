package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AbsenceCreateDto {
	
	@NotBlank
	private String dateStart;
	
	private String dateEnd;
	
	private String justif;
	
	private String descript;
	
	
	private EtudiantUpdateDto etudiant;


}
