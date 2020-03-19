package com.fr.adaming.deprecated;

import org.springframework.web.bind.annotation.RequestMapping;

import com.fr.adaming.controller.IController;
import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;

@RequestMapping ( path = "/classe")
public interface IClasseController extends IController<ClasseCreateDto, ClasseUpdateDto> {

}
