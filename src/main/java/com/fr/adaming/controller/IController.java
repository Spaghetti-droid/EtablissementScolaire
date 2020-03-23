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

/**
 * @author Gregoire
 * @author Jeanne-Marie
 * @author Isaline
 * 
 *         <b>Description : </b>
 *         <p>
 *         Interface pour les Controllers, implemente par la classe abstraite
 *         AbstractController Definis les méthodes CRUD : Create, Update, Read
 *         (all et ByID) et Delete.
 *         </p>
 *
 * @param <C> CreateDto
 * @param <T> UpdateDto
 * 
 */

@RequestMapping
public interface IController<C, T> { 

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour creer une Entite.
	 * </p>
	 * 
	 * @param dto : CreateDTO de l'Entité
	 * @return ResponseDto : null ou objet CreateDto
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> create(@RequestBody @Valid C dto);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour supprimer une Entite via son id.
	 * </p>
	 * 
	 * @param id : id de l'entite
	 * @return boolean true or false
	 */
	@DeleteMapping
	public ResponseEntity<ResponseDto> deleteById(@RequestParam(name = "id") @Positive int id);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour modifer une Entite.
	 * </p>
	 * 
	 * @param dto : Updatedto de l'entite
	 * @return boolean true or false
	 */
	@PutMapping
	public ResponseEntity<ResponseDto> update(@RequestBody @Valid T dto);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher une Entite via son id.
	 * </p>
	 * 
	 * @param id : id de l'entite
	 * @return UpdateDto
	 */
	@GetMapping(path = "/one")
	public ResponseEntity<ResponseDto> readById(@RequestParam(name = "id") @Positive int id);

	/**
	 * <b>Description : </b>
	 * <p>
	 * Methode pour afficher la liste des donnees d'une entite.
	 * </p>
	 * 
	 * @return listDto
	 */
	@GetMapping(path = "/all")
	public ResponseEntity<ResponseDto> readAll();

}
