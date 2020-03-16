package com.fr.adaming.controller;

import java.util.List;

import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.NoteConverter;
import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.INoteService;

@RestController
public class NoteController implements INoteController {

	@Autowired
	private INoteService serv;
	
	@Autowired
	private NoteConverter converter;

	@Override
	public ResponseEntity<ResponseDto> create(NoteCreateDto dto) {
		NoteCreateDto returnedDto = converter
				.convertEntityToCreateDto(serv.create(converter.convertCreateDtoToEntity(dto)));
		ResponseDto resp = null;

		if (returnedDto != null) {
			resp = new ResponseDto(false, "SUCCES", returnedDto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", returnedDto);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> deleteById(int id) {
		boolean result = serv.deleteById(id);
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(false, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> update(NoteUpdateDto dto) {
		boolean result = serv.update(converter.convertUpdateDtoToEntity(dto));
		ResponseDto resp = null;

		if (result) {
			resp = new ResponseDto(true, "SUCCES", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(false, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> readById(@Positive int id) {
		NoteCreateDto returnedDto = converter.convertEntityToCreateDto(serv.readById(id));
		ResponseDto resp = null;

		if (returnedDto != null) {
			resp = new ResponseDto(true, "SUCCES", returnedDto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(false, "FAIL", returnedDto);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> readAll() {
		List<NoteUpdateDto> returnedList = converter.convertListEntityToUpdateDto(serv.readAll());
		ResponseDto resp = null;

		if (returnedList != null) {
			resp = new ResponseDto(true, "SUCCCES", returnedList);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(false, "FAIL", returnedList);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

}
