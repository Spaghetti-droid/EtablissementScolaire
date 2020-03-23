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
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.service.EtudiantService;

/**
 * @author Lucie
 * @author Gregoire
 * @author Lea
 * @author Thierry
 * @author Jeanne-Marie
 * 
 *         <b>Description : </b>
 *         <p>
 *         Controller de l'entite Etudiant qui etend la classe
 *         AbstractController
 *         </p>
 */
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

	/**
	 * 
	 * <b>Description : </b>
	 * <p>
	 * Methode d'affichage des notes à partir du mail d'un etudiant.
	 * </p>
	 * 
	 * @param mail : email de l'entite
	 * @return listNoteUpdateDto : retourne la liste des notes d'un etudiant
	 */
	@GetMapping(path = "/note")
	public ResponseEntity<ResponseDto> notesParEtudiant(@RequestParam(name = "email") String mail) {
		List<NoteUpdateDto> notes = noteConverter
				.convertListEntityToUpdateDto(serviceEtudiant.readNoteByEtudiantEmail(mail));
		ResponseDto resp = null;
		if (!notes.isEmpty()) {
			resp = new ResponseDto(false, WebMappingConstant.SUCCESS_NOTE_ETUDIANT, notes);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode d'affichage des absences à partir du mail d'un etudiant.
	 * </p>
	 * @param mail : email de l'entite
	 * @return listAbsenceUpdateDto
	 */
	@GetMapping(path = "/absence")
	public ResponseEntity<ResponseDto> absenceParEtudiant(@RequestParam(name = "email") String mail) {
		List<AbsenceUpdateDto> absences = absenceConverter
				.convertListEntityToUpdateDto(serviceEtudiant.readAbsenceByEtudiantEmail(mail));
		ResponseDto resp = null;

		if (!absences.isEmpty()) {
			resp = new ResponseDto(false, WebMappingConstant.SUCCESS_ABSENCE_ETUDIANT, absences);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
