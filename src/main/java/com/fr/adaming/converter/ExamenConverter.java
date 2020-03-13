package com.fr.adaming.converter;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.entity.Examen;

public class ExamenConverter {

	public static Examen convertExamenCreateDtoToExamen (ExamenCreateDto dto) {
		if (dto == null) {
			return null;
		}
		Examen u = new Examen();
		
	}
	
	public static ExamenCreateDto convertExamenToExamenCreateDto (Examen examen) {
		if (examen == null) {
			return null;
		}
		ExamenCreateDto dto = new ExamenCreateDto();
	}
	
	public static Examen convertExamenUpdateDtoToExamen (ExamenUpdateDto dto) {
		if (dto == null) {
			return null;
		}
		Examen examen = new Examen();
		
	}
	
	public static ExamenUpdateDto convertExamenToExamenUpdateDto (Examen examen) {
		if (examen == null) {
			return null;
		}
		ClasseCreateDto dto = new ClasseCreateDto();
	}
}
