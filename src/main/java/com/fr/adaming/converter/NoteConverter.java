package com.fr.adaming.converter;

import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.entity.Note;

public class NoteConverter {

	public static Note convertCreateNoteToNoteCreateDto(NoteCreateDto dto) {

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
}
