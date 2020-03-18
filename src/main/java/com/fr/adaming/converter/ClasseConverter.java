package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Etudiant;

@Component
public class ClasseConverter implements IConverter<ClasseCreateDto, ClasseUpdateDto, Classe> {

	@Override
	public Classe convertCreateDtoToEntity(ClasseCreateDto createDto) {
		if (createDto == null) {
			return null;
		}
		Classe classe = new Classe();
		classe.setNom(createDto.getName());
		return classe;
	}

	@Override
	public ClasseCreateDto convertEntityToCreateDto(Classe entity) {
		if (entity == null) {
			return null;
		}
		ClasseCreateDto dto = new ClasseCreateDto();
		dto.setName(entity.getNom());
		return dto;
	}

	@Override
	public Classe convertUpdateDtoToEntity(ClasseUpdateDto updateDto) {
		if (updateDto == null) {
			return null;
		}
		Classe classe = new Classe();
		classe.setNom(updateDto.getName());
		classe.setId(updateDto.getId());
		return classe;
	}

	@Override
	public ClasseUpdateDto convertEntityToUpdateDto(Classe entity) {
		if (entity == null) {
			return null;
		}
		ClasseUpdateDto dto = new ClasseUpdateDto();
		dto.setName(entity.getNom());
		dto.setId(entity.getId());
		return dto;
	}

	@Override
	public List<Classe> convertListCreateDtoToEntity(List<ClasseCreateDto> listeCreateDto) {
		if (listeCreateDto == null) {
			return null;
		}
		List<Classe> liste = new ArrayList<Classe>();
		for (ClasseCreateDto dto : listeCreateDto) {
			liste.add(convertCreateDtoToEntity(dto));
		}
		return liste;
	}

	@Override
	public List<ClasseCreateDto> convertListEntityToCreateDto(List<Classe> listeEntity) {
		if (listeEntity == null) {
			return null;
		}
		List<ClasseCreateDto> dtoliste = new ArrayList<ClasseCreateDto>();
		for (Classe classe : listeEntity) {
			dtoliste.add(convertEntityToCreateDto(classe));
		}
		return dtoliste;
	}

	@Override
	public List<Classe> convertListUpdateDtoToEntity(List<ClasseUpdateDto> listeUpdateDto) {
		if (listeUpdateDto == null) {
			return null;
		}
		List<Classe> liste = new ArrayList<Classe>();
		for (ClasseUpdateDto dto : listeUpdateDto) {
			liste.add(convertUpdateDtoToEntity(dto));
		}
		return liste;
	}

	@Override
	public List<ClasseUpdateDto> convertListEntityToUpdateDto(List<Classe> listeEntity) {
		if (listeEntity == null) {
			return null;
		}
		List<ClasseUpdateDto> dtoliste = new ArrayList<ClasseUpdateDto>();
		for (Classe classe : listeEntity) {
			dtoliste.add(convertEntityToUpdateDto(classe));
		}
		return dtoliste;
	}
}
