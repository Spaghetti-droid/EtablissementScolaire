package com.fr.adaming.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.entity.Note;

/**
 * @author Thierry
 * @author Gregoire
 * @author Jeanne-Marie<br>
 * 
 * <b>Description : </b>
 * <p>Controller de l'entite Note qui etend la classe AbstractController</p>
 *
 */
@RestController
@CrossOrigin
@RequestMapping(path ="/note")
public class NoteController extends AbstractController<NoteCreateDto, NoteUpdateDto, Note> {

}
