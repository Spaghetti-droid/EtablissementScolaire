package com.fr.adaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.ResponseDto;

public abstract class AbstractController <C,T, E> implements IController<C, T>{
	
	@Autowired
	IConverter<C, T, E> converter;
	
	public ResponseEntity<ResponseDto> deleteById(int id, Class<S> SClazz) {
		
		
		
		
		boolean result = service.deleteById(id);
		ResponseDto response = null;

		if (result) {
			response = new ResponseDto(false, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response = new ResponseDto(true, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

}
