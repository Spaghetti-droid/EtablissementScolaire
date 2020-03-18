package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Note;

@Component
public class NoteConverter implements IConverter<NoteCreateDto, NoteUpdateDto, Note> {

	@Autowired
	private EtudiantConverter etuConverter;

	@Autowired
	private ExamenConverter examConverter;

	@Override
	public Note convertCreateDtoToEntity(NoteCreateDto createDto) {
		if (createDto == null) {
			return null;
		}
		Note n = new Note();
		n.setValeur(createDto.getValue());
		if (createDto.getEtudiant() != null) {

			Etudiant etu = etuConverter.convertUpdateDtoToEntity(createDto.getEtudiant());
			n.setEtudiant(etu);
		} else {
			n.setEtudiant(null);
		}
		
		if(createDto.getExamen() != null) {
		Examen exam = examConverter.convertUpdateDtoToEntity(createDto.getExamen());
		n.setExamen(exam);
		}else {
			n.setExamen(null);
		}

		return n;
	}

	@Override
	public NoteCreateDto convertEntityToCreateDto(Note entity) {
		if (entity == null) {
			return null;
		}
		NoteCreateDto n = new NoteCreateDto();
		n.setEtudiant(etuConverter.convertEntityToUpdateDto(entity.getEtudiant()));
		n.setExamen(examConverter.convertEntityToUpdateDto(entity.getExamen()));
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
		Etudiant etu = etuConverter.convertUpdateDtoToEntity(updateDto.getEtudiant());
		n.setEtudiant(etu);
		Examen exam = examConverter.convertUpdateDtoToEntity(updateDto.getExamen());
		n.setExamen(exam);
		n.setId(updateDto.getId());

		return n;
	}

	@Override
	public NoteUpdateDto convertEntityToUpdateDto(Note entity) {
		if (entity == null) {
			return null;
		}
		NoteUpdateDto n = new NoteUpdateDto();
		n.setExamen(examConverter.convertEntityToUpdateDto(entity.getExamen()));
		n.setEtudiant(etuConverter.convertEntityToUpdateDto(entity.getEtudiant()));
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
		for (NoteCreateDto e : listeCreateDto) {
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
		for (Note e : listeEntity) {
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
		for (NoteUpdateDto e : listeUpdateDto) {
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
		for (Note e : listeEntity) {
			notes.add(convertEntityToUpdateDto(e));
		}
		return notes;
	}

}
