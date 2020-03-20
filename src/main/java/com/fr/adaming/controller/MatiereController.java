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
import com.fr.adaming.converter.ExamenConverter;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.service.MatiereService;

@RestController
@CrossOrigin
@RequestMapping ( path = "/matiere" )
public class MatiereController extends AbstractController<MatiereCreateDto, MatiereUpdateDto, Matiere> {
	
	@Autowired
	private MatiereService servicematiere;
	
	@Autowired
	private ExamenConverter examenConverter;
	
	@GetMapping(path="/examens")
	public ResponseEntity<ResponseDto> examenParMatiere(@RequestParam(name = "nom")String nomMatiere){
		List<ExamenUpdateDto> examens = examenConverter.convertListEntityToUpdateDto(servicematiere.readExamenByNomMatiere(nomMatiere));
		ResponseDto resp = null;
		
		if (!examens.isEmpty()) {
			resp = new ResponseDto(false, WebMappingConstant.SUCCESS_EXAM_MATIERE, examens);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			resp = new ResponseDto(true, WebMappingConstant.FAIL_EXAM_MATIERE, examens);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
		}
	}

}
