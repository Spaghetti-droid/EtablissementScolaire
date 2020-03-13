package com.fr.adaming.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AbsenceCreateDto {
	
	private LocalDate dateStart;
	
	private LocalDate dateEnd;
	
	private String justif;
	
	private String descript;


}
