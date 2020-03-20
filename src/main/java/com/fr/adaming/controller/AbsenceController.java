package com.fr.adaming.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.entity.Absence;

@RestController
@CrossOrigin
@RequestMapping(path = "/absence")
public class AbsenceController extends AbstractController<AbsenceCreateDto, AbsenceUpdateDto, Absence> {

}
