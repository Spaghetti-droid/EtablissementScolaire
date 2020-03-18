package com.fr.adaming.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.AbsenceConverter;
import com.fr.adaming.converter.EtudiantConverter;
import com.fr.adaming.converter.NoteConverter;
import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.service.IEtudiantService;

@RestController
@CrossOrigin
public class EtudiantController implements IEtudiantController {

	@Autowired
	private IEtudiantService service;
	
	@Autowired
	private NoteConverter noteConverter;
	
	@Autowired
	private AbsenceConverter absenceConverter;
	
	@Autowired
	private EtudiantConverter etuConverter;

	@Override
	public ResponseEntity<ResponseDto> create(EtudiantCreateDto dto) {
		ResponseDto resp = null;

		EtudiantCreateDto dtoResp = etuConverter.convertEntityToCreateDto(service.create(etuConverter.convertCreateDtoToEntity(dto)));

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
		boolean result = service.update(etuConverter.convertUpdateDtoToEntity(dto));
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
		EtudiantUpdateDto etuUpDto = etuConverter.convertEntityToUpdateDto(service.readById(id));
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
		List<EtudiantUpdateDto> etudiants = etuConverter.convertListEntityToUpdateDto(service.readAll());
		ResponseDto resp = null;
		if (etudiants != null) {
			resp = new ResponseDto(false, "SUCCESS", etudiants);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@GetMapping(path="/note")
	public ResponseEntity<ResponseDto> notesParEtudiant(@RequestParam(name = "email")String mail){
		List<NoteCreateDto> notes= new ArrayList<NoteCreateDto>();
		notes = noteConverter.convertListEntityToCreateDto(service.readNoteByEtudiantEmail(mail));
		ResponseDto resp = null;
		if (notes != null) {
			resp = new ResponseDto(false, "SUCCESS", notes);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping(path="/absence")
	public ResponseEntity<ResponseDto> absenceParEtudiant(@RequestParam(name = "email")String mail){
		List<AbsenceCreateDto> absences= new ArrayList<AbsenceCreateDto>();
		ResponseDto resp = null;
		absences = absenceConverter.convertListEntityToCreateDto(service.readAbsenceByEtudiantEmail(mail));
		
		if (absences != null) {
			resp = new ResponseDto(false, "SUCCESS", absences);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
