package com.fr.adaming.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.entity.Examen;

@Component
public class ExamenConverter implements IConverter<ExamenCreateDto, ExamenUpdateDto, Examen> {

	@Autowired
	private MatiereConverter matConverter;
	
	@Override
	public Examen convertCreateDtoToEntity(ExamenCreateDto createDto) {
		if (createDto == null) {
			return null;
		}
		Examen exam = new Examen();
		exam.setCoef(createDto.getCoefExamen());
		if(createDto.getDateExamen()!=null) exam.setDate(LocalDate.parse(createDto.getDateExamen()));
		else exam.setDate(null);
		exam.setMatiere(matConverter.convertUpdateDtoToEntity(createDto.getMatiereExamen()));
		exam.setType(createDto.getTypeExamen());
		return exam;
	}

	@Override
	public ExamenCreateDto convertEntityToCreateDto(Examen entity) {
		if (entity == null) {
			return null;
		}
		ExamenCreateDto examdto = new ExamenCreateDto();
		examdto.setCoefExamen(entity.getCoef());
		examdto.setDateExamen(entity.getDate().toString());
		examdto.setMatiereExamen(matConverter.convertEntityToUpdateDto(entity.getMatiere()));
		examdto.setTypeExamen(entity.getType());
		
		return examdto;
	}

	@Override
	public Examen convertUpdateDtoToEntity(ExamenUpdateDto updateDto) {
		if (updateDto == null) {
			return null;
		}
		Examen exam = new Examen();
		exam.setCoef(updateDto.getCoefExamen());
		if(updateDto.getDateExamen()!=null) exam.setDate(LocalDate.parse(updateDto.getDateExamen()));
		else exam.setDate(null);
		exam.setMatiere(matConverter.convertUpdateDtoToEntity(updateDto.getMatiereExamen()));
		exam.setType(updateDto.getTypeExamen());
		exam.setId(updateDto.getIdExam());
		return exam;
	}

	@Override
	public ExamenUpdateDto convertEntityToUpdateDto(Examen entity) {
		if (entity == null) {
			return null;
		}
		ExamenUpdateDto examdto = new ExamenUpdateDto();
		examdto.setCoefExamen(entity.getCoef());
		examdto.setDateExamen(entity.getDate().toString());
		examdto.setMatiereExamen(matConverter.convertEntityToUpdateDto(entity.getMatiere()));
		examdto.setTypeExamen(entity.getType());
		examdto.setIdExam(entity.getId());
		
		return examdto;
	}

	@Override
	public List<Examen> convertListCreateDtoToEntity(List<ExamenCreateDto> listeCreateDto) {
		if (listeCreateDto == null) {
			return Collections.emptyList();
		}
		List<Examen> exams = new ArrayList<>();
		for(ExamenCreateDto e : listeCreateDto) {
			exams.add(convertCreateDtoToEntity(e));
		}
		return exams;
	}

	@Override
	public List<ExamenCreateDto> convertListEntityToCreateDto(List<Examen> listeEntity) {
		if (listeEntity == null) {
			return Collections.emptyList();
		}
		List<ExamenCreateDto> exams = new ArrayList<>();
		for(Examen e : listeEntity) {
			exams.add(convertEntityToCreateDto(e));
		}
		return exams;
	}

	@Override
	public List<Examen> convertListUpdateDtoToEntity(List<ExamenUpdateDto> listeUpdateDto) {
		if (listeUpdateDto == null) {
			return Collections.emptyList();
		}
		List<Examen> exams = new ArrayList<>();
		for(ExamenUpdateDto e : listeUpdateDto) {
			exams.add(convertUpdateDtoToEntity(e));
		}
		return exams;
	}

	@Override
	public List<ExamenUpdateDto> convertListEntityToUpdateDto(List<Examen> listeEntity) {
		if (listeEntity == null) {
			return Collections.emptyList();
		}
		List<ExamenUpdateDto> exams = new ArrayList<>();
		for(Examen e : listeEntity) {
			exams.add(convertEntityToUpdateDto(e));
		}
		return exams;
	}

	
	
}
