package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClasseUpdateDto {

	private int id;
	
	@NotBlank
	private String name;
	
}
