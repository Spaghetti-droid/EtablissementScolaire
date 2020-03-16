package com.fr.adaming.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;


@RequestMapping(path = "/etudiant")
public interface IEtudiantController extends IController<EtudiantCreateDto, EtudiantUpdateDto> {
	


}
