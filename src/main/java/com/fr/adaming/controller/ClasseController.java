package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.service.IClasseService;

@RestController
@CrossOrigin
public class ClasseController implements IClasseController {

	@Autowired
	private IClasseService service;
	
	@Autowired
	private IConverter<ClasseCreateDto, ClasseUpdateDto, Classe> converter;

	@Override
	public ResponseEntity<ResponseDto> create(ClasseCreateDto dto) {
		ClasseCreateDto returnedDto = converter.convertEntityToCreateDto(service.create(converter.convertCreateDtoToEntity(dto)));
		ResponseDto responseDto = null;
		
		if (returnedDto != null) {
			responseDto = new ResponseDto(false,"SUCCESS",returnedDto);
			return ResponseEntity.status(HttpStatus.OK).body(responseDto);
		} else {
			responseDto = new ResponseDto(true, "FAIL", returnedDto);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
		}				
	}

	@Override
	public ResponseEntity<ResponseDto> deleteById(int id) {
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

	@Override
	public ResponseEntity<ResponseDto> update(ClasseUpdateDto dto) {
		boolean result = service.update(converter.convertUpdateDtoToEntity(dto));
		ResponseDto response = null;
		
		if (result) {
			response = new ResponseDto(false,"SUCCESS",null);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response = new ResponseDto(true,"FAIL",null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}		
	}
	

	@Override
	public ResponseEntity<ResponseDto> readById(int id) {
		ClasseUpdateDto returnedDto = converter.convertEntityToUpdateDto(service.readById(id));
		ResponseDto response = null;
		
		if (returnedDto!= null) {
			response = new ResponseDto(false,"SUCCESS",returnedDto);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response = new ResponseDto(true,"FAIL",returnedDto);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}		
	}

	@Override
	public ResponseEntity<ResponseDto> readAll() {
		List<ClasseUpdateDto> returnedList = converter.convertListEntityToUpdateDto(service.readAll());
		ResponseDto response = null;
		
		if (returnedList != null) {
			response =  new ResponseDto(false, "SUCCESS", returnedList);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response = new ResponseDto(true,"FAIL", returnedList);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

}
	