package com.fr.adaming.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
