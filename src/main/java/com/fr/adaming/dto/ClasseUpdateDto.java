package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClasseUpdateDto {

	private int id;
	
	@NotBlank
	private String name;
	
}
