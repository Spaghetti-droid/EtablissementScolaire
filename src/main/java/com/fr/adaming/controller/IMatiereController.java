package com.fr.adaming.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;


@RequestMapping ( path = "/matiere" )
public interface IMatiereController extends IController<MatiereCreateDto, MatiereUpdateDto> {
	
	

}
