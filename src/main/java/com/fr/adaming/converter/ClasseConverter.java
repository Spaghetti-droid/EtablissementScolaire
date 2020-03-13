package com.fr.adaming.converter;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.entity.Classe;


public class ClasseConverter {

	public static Classe convertClasseCreateDtoToClasse(ClasseCreateDto dto) {
		if (dto == null) {
			return null;
		}
		Classe classe = new Classe();
		classe.setNom(dto.getName());
		return classe;
		
	}
	
	public static ClasseCreateDto convertClasseToClasseCreateDto(Classe classe) {
		if (classe == null) {
			return null;
		}
		ClasseCreateDto dto = new ClasseCreateDto();
		dto.setName(classe.getNom());
		return dto;
	}
	
	public static Classe convertClasseUpdateDtoToClasse(ClasseUpdateDto dto) {
		if (dto == null) {
			return null;
		}
		Classe classe = new Classe();
		classe.setNom(dto.getName());
		classe.setId(dto.getId());
		return classe;
		
	}
	
	public static ClasseUpdateDto convertClasseToClasseUpdateDto(Classe classe) {
		if (classe == null) {
			return null;
		}
		ClasseUpdateDto dto = new ClasseUpdateDto();
		dto.setName(classe.getNom());
		dto.setId(classe.getId());
		return dto;
	}
}
