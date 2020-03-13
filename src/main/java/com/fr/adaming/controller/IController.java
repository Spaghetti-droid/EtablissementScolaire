package com.fr.adaming.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ResponseDto;

@RequestMapping
public interface IController<CT, T> { // CT: CreateDto. T: DTO complet avec id
	
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody CT dto);

	@DeleteMapping
	public ResponseEntity<ResponseDto> deleteById(@RequestParam(name = "id") int id) ;

	@PutMapping
	public ResponseEntity<ResponseDto> update(@RequestBody T dto) ;

	@GetMapping(path = "/one")
	public ResponseEntity<ResponseDto> readById(@RequestParam(name = "id") int id) ;

	@GetMapping(path = "/all")
	public ResponseEntity<List<EtudiantUpdateDto>> readAll();

}
