package com.fr.adaming.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.converter.AbsenceConverter;
import com.fr.adaming.converter.NoteConverter;
import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.service.EtudiantService;


@RestController
@CrossOrigin
@RequestMapping(path = "/etudiant")
public class EtudiantController extends AbstractController<EtudiantCreateDto, EtudiantUpdateDto, Etudiant> {

	@Autowired
	private EtudiantService serviceEtudiant;
	
	@Autowired
	private NoteConverter noteConverter;
	
	@Autowired
	private AbsenceConverter absenceConverter;
	

	@GetMapping(path="/note")
	public ResponseEntity<ResponseDto> notesParEtudiant(@RequestParam(name = "email")String mail){
		List<NoteUpdateDto> notes = noteConverter.convertListEntityToUpdateDto(serviceEtudiant.readNoteByEtudiantEmail(mail));
		ResponseDto resp = null;
		if (!notes.isEmpty()) {
			resp = new ResponseDto(false, WebMappingConstant.SUCCESS_NOTE_ETUDIANT, notes);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	@GetMapping(path="/absence")
	public ResponseEntity<ResponseDto> absenceParEtudiant(@RequestParam(name = "email")String mail){
		List<AbsenceUpdateDto> absences = absenceConverter.convertListEntityToUpdateDto(serviceEtudiant.readAbsenceByEtudiantEmail(mail));
		ResponseDto resp = null;
				
		if (!absences.isEmpty()) {
			resp = new ResponseDto(false, WebMappingConstant.SUCCESS_ABSENCE_ETUDIANT, absences);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
}
