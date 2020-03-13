package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.ClasseConverter;
import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IClasseService;

@RestController
public class ClasseController implements IClasseController {

	@Autowired
	private IClasseService service;

	@Override
	public ResponseEntity<ResponseDto> create(ClasseCreateDto dto) {
		ClasseCreateDto returnedDto = ClasseConverter.convertClasseToClasseCreateDto(service.create(ClasseConverter.convertClasseCreateDtoToClasse(dto)));
		ResponseDto responseDto = null;
		
		if (returnedDto != null) {
			responseDto = new ResponseDto(false,"SUCCES",returnedDto);
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
			response = new ResponseDto(true, "SUCCES", null);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response = new ResponseDto(false, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> update(ClasseUpdateDto dto) {
		boolean result = service.update(ClasseConverter.convertClasseUpdateDtoToClasse(dto));
		ResponseDto response = null;
		
		if (result) {
			response = new ResponseDto(true,"SUCCES",null);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response = new ResponseDto(false,"FAIL",null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}		
	}
	

	@Override
	public ResponseEntity<ResponseDto> readById(int id) {
		ClasseCreateDto returnedDto = ClasseConverter.convertClasseToClasseCreateDto(service.readById(id));
		ResponseDto response = null;
		
		if (returnedDto!= null) {
			response = new ResponseDto(false,"SUCCES",returnedDto);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response = new ResponseDto(true,"FAIL",returnedDto);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}		
	}

	@Override
	public ResponseEntity<ResponseDto> readAll() {
		List<ClasseUpdateDto> returnedList = ClasseConverter.convertListClasseToListClasseUpdateDto(service.readAll());
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
	