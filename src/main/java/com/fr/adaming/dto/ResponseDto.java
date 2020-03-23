package com.fr.adaming.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfert Object retourne par les differentes methodes du Controller.
 * Contient un boolean isError, un message et un body de type DTO.
 * @author Gregoire
 *
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseDto {
	
	private boolean isError;
	
	private String message;
	
	private Object body;

}
