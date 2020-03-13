package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Etudiant;


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
	
	public static List<Classe> convertListClasseUpdateDtoToListClasse(List<ClasseUpdateDto> dtoliste) {
		if (dtoliste == null) {
			return null;
		}
		List<Classe> liste = new ArrayList<Classe>();
		for (ClasseUpdateDto dto : dtoliste) {
			liste.add(convertClasseUpdateDtoToClasse(dto));
		}
		return liste;
	}

	public static List<ClasseUpdateDto> convertListClasseToListClasseUpdateDto(List<Classe> liste) {
		if (liste == null) {
			return null;
		}
		List<ClasseUpdateDto> dtoliste = new ArrayList<ClasseUpdateDto>();
		for (Classe classe : liste) {
			dtoliste.add(convertClasseToClasseUpdateDto(classe));
		}
		return dtoliste;
	}
}
