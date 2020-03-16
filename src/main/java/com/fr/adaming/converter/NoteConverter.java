package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Note;

public class NoteConverter {

	public static Note convertNoteCreateDtoToNote(NoteCreateDto dto) {

		if (dto == null) {
			return null;
		}
		Note n = new Note();
		n.setValeur(dto.getValue());

		return n;
	}

	public static NoteCreateDto convertNoteToNoteCreateDto(Note note) {
		if (note == null) {
			return null;

		}
		NoteCreateDto dto = new NoteCreateDto();
		dto.setValue(note.getValeur());
		return dto;
	}

	public static Note convertNoteUpdateDtoToNote(NoteUpdateDto dto) {

		if (dto == null) {
			return null;
		}
		Note n = new Note();
		n.setValeur(dto.getValue());

		return n;
	}

	public static NoteUpdateDto convertNoteToNoteUpdateDto(Note note) {
		if (note == null) {
			return null;

		}
		NoteUpdateDto dto = new NoteUpdateDto();
		dto.setValue(note.getValeur());
		return dto;
	}

	public static List<Note> convertListNoteUpdateDtoToListNote(List<NoteUpdateDto> listDto) {
		if (listDto == null) {
			return null;
		}
		List<Note> list = new ArrayList<Note>();
		for (NoteUpdateDto dto : listDto) {
			list.add(convertNoteUpdateDtoToNote(dto));
		}
		return list;
	}

	public static List<NoteUpdateDto> convertListNoteToListNotUpdateDto(List<Note> list) {
		if (list == null) {
			return null;
		}
		List<NoteUpdateDto> listDto = new ArrayList<NoteUpdateDto>();
		for (Note note : list) {
			listDto.add(convertNoteToNoteUpdateDto(note));
		}
		return listDto;
	}
}
