package com.fr.adaming.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AbsenceUpdateDto {
	
	@Positive
	private int identifiant;
	
	@NotNull
	private String dateStart;
	
	private String dateEnd;
	
	private String justif;
	
	private String descript;
	
	private EtudiantUpdateDto etudiant;

}
