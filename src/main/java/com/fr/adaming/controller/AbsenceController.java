package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Absence;
import com.fr.adaming.converter.IConverter;
import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IAbsenceService;

@RestController
public class AbsenceController implements IAbsenceController {

	@Autowired
	private IAbsenceService service;

	@Autowired
	private IConverter<AbsenceCreateDto, AbsenceUpdateDto, Absence> converter;

	@Override
	public ResponseEntity<ResponseDto> create(@Valid AbsenceCreateDto dto) {
		ResponseDto resp = null;

		AbsenceCreateDto dtoResp = converter
				.convertEntityToCreateDto(service.create(converter.convertCreateDtoToEntity(dto)));

		if (dtoResp != null) {
			// Success
			resp = new ResponseDto(false, "SUCCESS", dtoResp);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			// fail
			resp = new ResponseDto(true, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> deleteById(@Positive int id) {
		ResponseDto resp = null;

		boolean result = service.deleteById(id);

		if (result) {
			resp = new ResponseDto(false, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> update(@Valid AbsenceUpdateDto dto) {
		ResponseDto resp = null;

		boolean result = service.update(converter.convertUpdateDtoToEntity(dto));

		if (result) {
			resp = new ResponseDto(false, "SUCCESS", null);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> readById(@Positive int id) {
		ResponseDto resp = null;

		AbsenceUpdateDto absDto = converter.convertEntityToUpdateDto(service.readById(id));

		if (absDto != null) {
			resp = new ResponseDto(false, "SUCCESS", absDto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

	@Override
	public ResponseEntity<ResponseDto> readAll() {
		ResponseDto resp = null;

		List<AbsenceUpdateDto> listAbsDto = converter.convertListEntityToUpdateDto(service.readAll());

		if (!listAbsDto.isEmpty()) {
			resp = new ResponseDto(false, "SUCCESS", listAbsDto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}

	}

}
