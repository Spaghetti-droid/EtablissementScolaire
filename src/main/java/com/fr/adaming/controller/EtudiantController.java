package com.fr.adaming.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.converter.EtudiantConverter;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IEtudiantService;

@RequestMapping
public class EtudiantController implements IEtudiantController {

	@Autowired
	private IEtudiantService service;

	@Override
	public ResponseEntity<ResponseDto> create(EtudiantCreateDto dto) {
		ResponseDto resp = null;

		EtudiantCreateDto dtoResp = EtudiantConverter.convertEtudiantToEtudiantCreateDto(
				service.create(EtudiantConverter.convertEtudiantCreateDtoToEtudiant(dto)));

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
	public ResponseEntity<ResponseDto> deleteById(int id) {
		boolean result = service.deleteById(id);
		ResponseDto resp = null;

		if (result) {

			resp = new ResponseDto(false, "SUCCESS", null);

			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {

			resp = new ResponseDto(true, "FAIL", null);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

		}
	}

	@Override
	public ResponseEntity<ResponseDto> update(EtudiantUpdateDto dto) {
		boolean result = service.update(EtudiantConverter.convertEtudiantUpdateDtoToEtudiant(dto));
		ResponseDto resp = null;

		if (result) {

			resp = new ResponseDto(false, "SUCCESS", null);

			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {

			resp = new ResponseDto(true, "FAIL", null);

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

		}
	}

	@Override
	public ResponseEntity<ResponseDto> readById(int id) {
		EtudiantUpdateDto etuUpDto = EtudiantConverter.convertEtudiantToEtudiantUpdateDto(service.readById(id));
		ResponseDto resp = null;

		if (etuUpDto != null) {
			// SUCCESS
			resp = new ResponseDto(false, "SUCCESS", etuUpDto);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			// FAIL
			resp = new ResponseDto(true, "FAIL", null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

		}
	}

	@Override
	public ResponseEntity<ResponseDto> readAll() {
		List<EtudiantUpdateDto> etudiants = EtudiantConverter.listEtudiantToEtudiantUpdateDto(service.readAll());
		ResponseDto resp = null;
		if (etudiants != null) {
			resp =  new ResponseDto(false, "SUCCESS", etudiants);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}



}