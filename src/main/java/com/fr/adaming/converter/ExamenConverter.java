package com.fr.adaming.converter;

import java.util.List;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.entity.Examen;

public class ExamenConverter implements IConverter<ExamenCreateDto, ExamenUpdateDto, Examen> {

	@Override
	public Examen convertCreateDtoToEntity(ExamenCreateDto createDto) {
		Examen exam = new Examen();
		exam.setCoef(createDto.getCoefExamen());
		exam.setDate(createDto.getDateExamen());

		exam.setType(createDto.getTypeExamen());
		return exam;
	}

	@Override
	public ExamenCreateDto convertEntityToCreateDto(Examen entity) {
		ExamenCreateDto examdto = new ExamenCreateDto();
		
		return null;
	}

	@Override
	public Examen convertUpdateDtoToEntity(ExamenUpdateDto updateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExamenUpdateDto convertEntityToUpdateDto(Examen entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Examen> convertListCreateDtoToEntity(List<ExamenCreateDto> listeCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamenCreateDto> convertListEntityToCreateDto(List<Examen> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Examen> convertListUpdateDtoToEntity(List<ExamenUpdateDto> listeUpdateDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExamenUpdateDto> convertListEntityToUpdateDto(List<Examen> listeEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
