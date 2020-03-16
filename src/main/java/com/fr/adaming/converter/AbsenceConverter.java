package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.entity.Absence;
import com.fr.adaming.service.IEtudiantService;

@Component
public class AbsenceConverter implements IConverter<AbsenceCreateDto, AbsenceUpdateDto, Absence> {
	
	@Autowired
	private IEtudiantService service;

	@Override
	public Absence convertCreateDtoToEntity(AbsenceCreateDto createDto) {
		if (createDto == null) {
			return null;
		} else {
			Absence abs = new Absence();
			abs.setDateDebut(createDto.getDateStart());
			abs.setDateFin(createDto.getDateEnd());
			abs.setJustification(createDto.getJustif());
			abs.setDescription(createDto.getDescript());
			abs.setEtudiant(service.readById(createDto.getId_etudiant()));
			return abs;
		}
	}

	@Override
	public AbsenceCreateDto convertEntityToCreateDto(Absence absence) {
		if (absence == null) {
			return null;
		} else {
			AbsenceCreateDto absCreateDto = new AbsenceCreateDto();
			absCreateDto.setDateStart(absence.getDateDebut());
			absCreateDto.setDateEnd(absence.getDateFin());
			absCreateDto.setJustif(absence.getJustification());
			absCreateDto.setDescript(absence.getDescription());
			absCreateDto.setId_etudiant(absence.getEtudiant().getId());
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
			abs.setDateDebut(updateDto.getDateStart());
			abs.setDateFin(updateDto.getDateEnd());
			abs.setJustification(updateDto.getJustif());
			abs.setDescription(updateDto.getDescript());
			abs.setEtudiant(service.readById(updateDto.getId_etudiant()));
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
			absUpdateDto.setDateStart(absence.getDateDebut());
			absUpdateDto.setDateEnd(absence.getDateFin());
			absUpdateDto.setJustif(absence.getJustification());
			absUpdateDto.setDescript(absence.getDescription());
			absUpdateDto.setId_etudiant(absence.getEtudiant().getId());
			return absUpdateDto;
		}
	}

	@Override
	public List<Absence> convertListCreateDtoToEntity(List<AbsenceCreateDto> listeCreateDto) {
		if (listeCreateDto.isEmpty()) {
			List<Absence> listEmpty = new ArrayList<Absence>();
			return listEmpty;
		} else {
			List<Absence> list = new ArrayList<Absence>();
			for (AbsenceCreateDto i:listeCreateDto) {
				Absence abs = new Absence();
				abs.setDateDebut(i.getDateStart());
				abs.setDateFin(i.getDateEnd());
				abs.setJustification(i.getJustif());
				abs.setDescription(i.getDescript());
				abs.setEtudiant(service.readById(i.getId_etudiant()));
				list.add(abs);
			}
			return list;
		}
	}

	@Override
	public List<AbsenceCreateDto> convertListEntityToCreateDto(List<Absence> listeAbsence) {
		if (listeAbsence.isEmpty()) {
			List<AbsenceCreateDto> listEmpty = new ArrayList<AbsenceCreateDto>();
			return listEmpty;
		} else {
			List<AbsenceCreateDto> list = new ArrayList<AbsenceCreateDto>();
			for (Absence i: listeAbsence) {
				AbsenceCreateDto absCreateDto = new AbsenceCreateDto();
				absCreateDto.setDateStart(i.getDateDebut());
				absCreateDto.setDateEnd(i.getDateFin());
				absCreateDto.setJustif(i.getJustification());
				absCreateDto.setDescript(i.getDescription());
				absCreateDto.setId_etudiant(i.getEtudiant().getId());
				list.add(absCreateDto);
			}
			return list;
		}
	}

	@Override
	public List<Absence> convertListUpdateDtoToEntity(List<AbsenceUpdateDto> listeUpdateDto) {
		if (listeUpdateDto.isEmpty()) {
			List<Absence> listEmpty = new ArrayList<Absence>();
			return listEmpty;
		} else {
			List<Absence> list = new ArrayList<Absence>();
			for (AbsenceUpdateDto i:listeUpdateDto) {
				Absence abs = new Absence();
				abs.setId(i.getIdentifiant());
				abs.setDateDebut(i.getDateStart());
				abs.setDateFin(i.getDateEnd());
				abs.setJustification(i.getJustif());
				abs.setDescription(i.getDescript());
				abs.setEtudiant(service.readById(i.getId_etudiant()));
				list.add(abs);
			}
			return list;
		}
	}

	@Override
	public List<AbsenceUpdateDto> convertListEntityToUpdateDto(List<Absence> listeAbsence) {
		if (listeAbsence.isEmpty()) {
			List<AbsenceUpdateDto> listEmpty = new ArrayList<AbsenceUpdateDto>();
			return listEmpty;
		} else {
			List<AbsenceUpdateDto> list = new ArrayList<AbsenceUpdateDto>();
			for (Absence i: listeAbsence) {
				AbsenceUpdateDto absUpdateDto = new AbsenceUpdateDto();
				absUpdateDto.setIdentifiant(i.getId());
				absUpdateDto.setDateStart(i.getDateDebut());
				absUpdateDto.setDateEnd(i.getDateFin());
				absUpdateDto.setJustif(i.getJustification());
				absUpdateDto.setDescript(i.getDescription());
				absUpdateDto.setId_etudiant(i.getEtudiant().getId());
				list.add(absUpdateDto);
			}
			return list;
		}
	}

}
