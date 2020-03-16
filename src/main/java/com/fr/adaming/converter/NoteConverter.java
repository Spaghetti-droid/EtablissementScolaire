package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import javax.management.ServiceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.entity.Note;
import com.fr.adaming.entity.Note;
import com.fr.adaming.entity.Note;
import com.fr.adaming.service.IEtudiantService;
import com.fr.adaming.service.IExamenService;

@Component
public class NoteConverter implements IConverter<NoteCreateDto, NoteUpdateDto, Note>{
	
	@Autowired
	private IEtudiantService serviceE;
	
	@Autowired
	private IExamenService serviceExam;

	@Override
	public Note convertCreateDtoToEntity(NoteCreateDto createDto) {
		if (createDto == null) {
			return null;
		}
		Note n = new Note();
		n.setValeur(createDto.getValue());
		n.setEtudiant(serviceE.readByEmail(createDto.getNometudiant()));
		n.setExamen(serviceExam.readById(createDto.getIdexamen()));
		
		return n;
	}

	@Override
	public NoteCreateDto convertEntityToCreateDto(Note entity) {
		if(entity == null) {
			return null;
		}
		NoteCreateDto n = new NoteCreateDto();
		n.setIdexamen(entity.getExamen().getId());
		n.setNometudiant(entity.getEtudiant().getEmail());
		n.setValue(entity.getValeur());
		
		return n;
	}

	@Override
	public Note convertUpdateDtoToEntity(NoteUpdateDto updateDto) {
		if (updateDto == null) {
			return null;
		}
		Note n = new Note();
		n.setValeur(updateDto.getValue());
		n.setEtudiant(serviceE.readByEmail(updateDto.getNometudiant()));
		n.setExamen(serviceExam.readById(updateDto.getIdexamen()));
		n.setId(updateDto.getId());
		
		return n;
	}

	@Override
	public NoteUpdateDto convertEntityToUpdateDto(Note entity) {
		if(entity == null) {
			return null;
		}
		NoteUpdateDto n = new NoteUpdateDto();
		n.setIdexamen(entity.getExamen().getId());
		n.setNometudiant(entity.getEtudiant().getEmail());
		n.setValue(entity.getValeur());
		n.setId(entity.getId());
		
		return n;
	}

	@Override
	public List<Note> convertListCreateDtoToEntity(List<NoteCreateDto> listeCreateDto) {
		if (listeCreateDto == null) {
			return null;
		}
		List<Note> n = new ArrayList<Note>();
		for(NoteCreateDto e : listeCreateDto) {
			n.add(convertCreateDtoToEntity(e));
		}
		return n;
	}

	@Override
	public List<NoteCreateDto> convertListEntityToCreateDto(List<Note> listeEntity) {
		if (listeEntity == null) {
			return null;
		}
		List<NoteCreateDto> notes = new ArrayList<NoteCreateDto>();
		for(Note e : listeEntity) {
			notes.add(convertEntityToCreateDto(e));
		}
		return notes;
	}

	@Override
	public List<Note> convertListUpdateDtoToEntity(List<NoteUpdateDto> listeUpdateDto) {
		if (listeUpdateDto == null) {
			return null;
		}
		List<Note> n = new ArrayList<Note>();
		for(NoteUpdateDto e : listeUpdateDto) {
			n.add(convertUpdateDtoToEntity(e));
		}
		return n;
	}

	@Override
	public List<NoteUpdateDto> convertListEntityToUpdateDto(List<Note> listeEntity) {
		if (listeEntity == null) {
			return null;
		}
		List<NoteUpdateDto> notes = new ArrayList<NoteUpdateDto>();
		for(Note e : listeEntity) {
			notes.add(convertEntityToUpdateDto(e));
		}
		return notes;
	}

	
}
