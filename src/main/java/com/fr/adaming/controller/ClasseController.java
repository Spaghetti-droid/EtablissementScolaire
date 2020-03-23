package com.fr.adaming.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.entity.Classe;

/**
 * @author Jeanne-Marie<br>
 * 
 * <b>Description : </b>
 * <p>Controller de l'entite Classe qui etend la classe AbstractController</p>
 *
 */
@RestController
@CrossOrigin
@RequestMapping ( path = "/classe")
public class ClasseController extends AbstractController<ClasseCreateDto, ClasseUpdateDto, Classe>{


}
	