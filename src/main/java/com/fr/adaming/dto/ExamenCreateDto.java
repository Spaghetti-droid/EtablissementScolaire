package com.fr.adaming.dto;



import javax.validation.constraints.NotBlank;
import com.fr.adaming.enumeration.Type;
import lombok.Data;


@Data
public class ExamenCreateDto {
	
	@NotBlank
	private String dateExamen;
	
	private Type typeExamen;
	
	private double coefExamen;
	
	@NotBlank
	private MatiereUpdateDto matiereExamen;

}
