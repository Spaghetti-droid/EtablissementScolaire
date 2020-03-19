package com.fr.adaming.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.converter.ExamenConverter;
import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.service.IMatiereService;

@RestController
@CrossOrigin
@RequestMapping ( path = "/matiere" )
public class MatiereController extends AbstractController<MatiereCreateDto, MatiereUpdateDto, Matiere> {
	
	@Autowired
	private IMatiereService servicematiere;
	
	@Autowired
	private ExamenConverter examenConverter;
	
	@GetMapping(path="/examens")
	public ResponseEntity<ResponseDto> examenParMatiere(@RequestParam(name = "nom")String nomMatiere){
		List<ExamenCreateDto> examens = examenConverter.convertListEntityToCreateDto(servicematiere.readExamenByNomMatiere(nomMatiere));
		ResponseDto resp = null;
		
		if (examens != null) {
			resp = new ResponseDto(false, "SUCCESS", examens);
			return ResponseEntity.status(HttpStatus.OK).body(resp);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

}
