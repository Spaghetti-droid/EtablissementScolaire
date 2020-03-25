package com.fr.adaming.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.entity.Absence;

/**
 * @author Gregoire
 * @author Jeanne-Marie
 * @author Lea
 * @author isaline<br>
 * 
 * <b>Description : </b>
 * <p>Controller de l'entite Absence qui etend la classe AbstractController</p>
 *
 */
@RestController
@CrossOrigin
@RequestMapping(path = "/absence")
public class AbsenceController extends AbstractController<AbsenceCreateDto, AbsenceUpdateDto, Absence> {

}
