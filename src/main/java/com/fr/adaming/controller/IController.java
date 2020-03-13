package com.fr.adaming.controller;


import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fr.adaming.dto.ResponseDto;

@RequestMapping
public interface IController<CT, T> { // CT: CreateDto. T: DTO complet avec id
	
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid CT dto);

	@DeleteMapping
	public ResponseEntity<ResponseDto> deleteById(@RequestParam(name = "id") @Positive int id) ;
	
	@PutMapping
	public ResponseEntity<ResponseDto> update(@RequestBody @Valid T dto) ;

	@GetMapping(path = "/one")
	public ResponseEntity<ResponseDto> readById(@RequestParam(name = "id") @Positive int id) ;

	@GetMapping(path = "/all")
	public ResponseEntity<ResponseDto> readAll();

}
