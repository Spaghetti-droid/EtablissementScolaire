package com.fr.adaming.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClasseCreateDto {
	
	@NotBlank
	private String name;
	
}
