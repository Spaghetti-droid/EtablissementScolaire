package com.fr.adaming.converter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.entity.Absence;

@Component
public class AbsenceConverter implements IConverter<AbsenceCreateDto, AbsenceUpdateDto, Absence> {
	
	@Autowired
	private EtudiantConverter converter;

	@Override
	public Absence convertCreateDtoToEntity(AbsenceCreateDto createDto) {
		if (createDto == null) {
			return null;
		} else {
			Absence abs = new Absence();
			if(createDto.getDateStart()!=null)
			abs.setDateDebut(LocalDate.parse(createDto.getDateStart()));
			else abs.setDateDebut(null);
			if(createDto.getDateEnd()!=null)
			abs.setDateFin(LocalDate.parse(createDto.getDateEnd()));
			else abs.setDateFin(null);
			abs.setJustification(createDto.getJustif());
			abs.setDescription(createDto.getDescript());
			abs.setEtudiant(converter.convertUpdateDtoToEntity(createDto.getEtudiant()));
			return abs;
		}
	}

	@Override
	public AbsenceCreateDto convertEntityToCreateDto(Absence absence) {
		if (absence == null) {
			return null;
		} else {
			AbsenceCreateDto absCreateDto = new AbsenceCreateDto();
			if (absence.getDateDebut() != null) {
				absCreateDto.setDateStart(absence.getDateDebut().toString());
			} else {
				absCreateDto.setDateStart(null);
			}
			
			if (absence.getDateFin() != null) {
				absCreateDto.setDateEnd(absence.getDateFin().toString());
			} else {
				absCreateDto.setDateEnd(null);
			}
			absCreateDto.setJustif(absence.getJustification());
			absCreateDto.setDescript(absence.getDescription());
			absCreateDto.setEtudiant(converter.convertEntityToUpdateDto(absence.getEtudiant()));
			return absCreateDto;
		}
	}


	@Override
	public Absence convertUpdateDtoToEntity(AbsenceUpdateDto updateDto) {
		if (updateDto == null) {
			return null;
		} else {
			Absence abs = new Absence();
			abs.setId(updateDto.getIdentifiant());
			if(updateDto.getDateStart()!=null) {
				abs.setDateDebut(LocalDate.parse(updateDto.getDateStart()));
			}else {
				abs.setDateDebut(null);
			}
			if(updateDto.getDateEnd()!=null) {
				abs.setDateFin(LocalDate.parse(updateDto.getDateEnd()));
			} else {
				abs.setDateFin(null);
			}
			abs.setJustification(updateDto.getJustif());
			abs.setDescription(updateDto.getDescript());
			abs.setEtudiant(converter.convertUpdateDtoToEntity(updateDto.getEtudiant()));
			return abs;
		}
	}

	@Override
	public AbsenceUpdateDto convertEntityToUpdateDto(Absence absence) {
		if (absence == null) {
			return null;
		} else {
			AbsenceUpdateDto absUpdateDto = new AbsenceUpdateDto();
			absUpdateDto.setIdentifiant(absence.getId());
			if (absence.getDateDebut() != null) {
				absUpdateDto.setDateStart(absence.getDateDebut().toString());
			} else {
				absUpdateDto.setDateStart(null);
			}
			
			if (absence.getDateFin() != null) {
				absUpdateDto.setDateEnd(absence.getDateFin().toString());
			} else {
				absUpdateDto.setDateEnd(null);
			}
			absUpdateDto.setJustif(absence.getJustification());
			absUpdateDto.setDescript(absence.getDescription());
			absUpdateDto.setEtudiant(converter.convertEntityToUpdateDto(absence.getEtudiant()));
			return absUpdateDto;
		}
	}

	@Override
	public List<Absence> convertListCreateDtoToEntity(List<AbsenceCreateDto> listeCreateDto) {
		if (listeCreateDto == null) {
			return Collections.emptyList();
		} else {
		List<Absence> abs = new ArrayList<>();
		for(AbsenceCreateDto e : listeCreateDto) {
			abs.add(convertCreateDtoToEntity(e));
		}
		return abs;
		}
	}

	@Override
	public List<AbsenceCreateDto> convertListEntityToCreateDto(List<Absence> listeEntity) {
		if (listeEntity == null) {
			return Collections.emptyList();
		} else {
		List<AbsenceCreateDto> abs = new ArrayList<>();
		for(Absence e : listeEntity) {
			abs.add(convertEntityToCreateDto(e));
		}
		return abs;
		}
	}
	

	@Override
	public List<Absence> convertListUpdateDtoToEntity(List<AbsenceUpdateDto> listeUpdateDto) {
		if (listeUpdateDto == null) {
			return Collections.emptyList();
		} else {
		List<Absence> abs = new ArrayList<>();
		for(AbsenceUpdateDto e : listeUpdateDto) {
			abs.add(convertUpdateDtoToEntity(e));
		}
		return abs;
		}
	}
	

	@Override
	public List<AbsenceUpdateDto> convertListEntityToUpdateDto(List<Absence> listeEntity) {
		if (listeEntity == null) {
			return Collections.emptyList();
		} else {
		List<AbsenceUpdateDto> abs = new ArrayList<>();
		for(Absence e : listeEntity) {
			abs.add(convertEntityToUpdateDto(e));
		}
		return abs;
		}
	}
	

}
