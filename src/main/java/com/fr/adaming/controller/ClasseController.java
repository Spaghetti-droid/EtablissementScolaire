package com.fr.adaming.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.service.IClasseService;


@RestController
public class ClasseController implements IClasseController {

	@Autowired
	private IClasseService service;
	@Override
	public ResponseEntity<?> create(ClasseCreateDto dto) {
		ClasseCreateDto result = ClasseConverter.convertVoitureToDtoVoiture(service.create(ConverterVoiture.convertDtoVoitureToVoiture(dto)));
		DtoResponseVoiture response = null;
		if (result == null) {
			response = new DtoResponseVoiture(true, "Erreur de création", result);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} else {
			response = new DtoResponseVoiture(false, "Création réussie", result);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
	}

	@Override
	public ResponseEntity<?> deleteById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> update(ClasseUpdateDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> readById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
