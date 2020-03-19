package com.fr.adaming.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.entity.Note;

@RestController
@CrossOrigin
@RequestMapping(path ="/note")
public class NoteController extends AbstractController<NoteCreateDto, NoteUpdateDto, Note> {

}
