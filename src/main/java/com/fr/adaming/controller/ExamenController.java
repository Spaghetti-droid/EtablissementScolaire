package com.fr.adaming.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.entity.Examen;

/**
 * @author Jeanne-Marie
 * @author Gregoire
 * 
 * <b>Description : </b>
 * <p>Controller de l'entite Examen qui etend la classe AbstractController</p>
 *
 */
@RestController
@CrossOrigin
@RequestMapping ( path = "/examen" )
public class ExamenController extends AbstractController<ExamenCreateDto, ExamenUpdateDto, Examen> {
	
}
