package com.fr.adaming.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping
public interface IController<CT, T> { // CT: CreateDto. T: DTO complet avec id
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody CT dto);

	@DeleteMapping
	public ResponseEntity<?> deleteById(@RequestParam(name = "id") int id) ;

	@PutMapping
	public ResponseEntity<?> update(@RequestBody T dto) ;

	@GetMapping(path = "/one")
	public ResponseEntity<?> readById(@RequestParam(name = "id") int id) ;

	@GetMapping(path = "/all")
	public ResponseEntity<?> readAll();

}
