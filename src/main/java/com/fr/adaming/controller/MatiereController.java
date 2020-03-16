package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fr.adaming.converter.MatiereConverter;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IMatiereService;



public class MatiereController implements IMatiereController {
	
	@Autowired
	private IMatiereService service;
	
	@Autowired
	private MatiereConverter converter;

	@Override
	public ResponseEntity<ResponseDto> create(@Valid MatiereCreateDto dto) {
		MatiereCreateDto returnedDto = converter.convertEntityToCreateDto(service.create(converter.convertCreateDtoToEntity(dto)));
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
	public ResponseEntity<ResponseDto> deleteById(@Positive int id) {
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
	public ResponseEntity<ResponseDto> update(@Valid MatiereUpdateDto dto) {
		boolean result = service.update(converter.convertUpdateDtoToEntity(dto));
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
	public ResponseEntity<ResponseDto> readById(@Positive int id) {
		MatiereUpdateDto returnedDto =converter.convertEntityToUpdateDto(service.readById(id));
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
		List<MatiereUpdateDto> returnedList = converter.convertListEntityToUpdateDto(service.readAll());
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
