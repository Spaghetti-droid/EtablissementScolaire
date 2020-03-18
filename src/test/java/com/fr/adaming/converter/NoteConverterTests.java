package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteUpdateDto;

import com.fr.adaming.entity.Note;

public class NoteConverterTests {

	@Autowired
	private NoteConverter converter;

	@Test
	public void testConvertingNoteCreateDtoToNote() {
		// Préparer les inputs
		NoteCreateDto dto = new NoteCreateDto();
		dto.setValue(5);

		// Invoquer l'appli
		Note returnedNote = converter.convertCreateDtoToEntity(dto);

		// Vérifier si les résultats sont des success
		assertThat(returnedNote).hasFieldOrPropertyWithValue("valeur", 5);
		assertNotNull(returnedNote);
	}

	@Test
	public void testConvertingNullNoteCreateDto_shouldReturnNullUser() {
		assertNull(converter.convertCreateDtoToEntity(null));
	}

	@Test
	public void testConvertingNoteUpdateDtoToClasse() {
		// Préparer les inputs
		NoteUpdateDto dto = new NoteUpdateDto();
		dto.setId(1);
		dto.setValue(15);

		// Invoquer l'appli
		Note returnedClasse = converter.convertUpdateDtoToEntity(dto);

		// Vérifier si les résultats sont des success
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("id", 1);
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("valeur", 15);
		assertNotNull(returnedClasse);
	}

	@Test
	public void testConvertingNullNoteUpdateDto_shouldReturnNullUser() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}

	@Test
	public void testConvertingNoteToNoteCreateDto() {
		// Préparer les inputs
		Note note = new Note();
		note.setValeur(15);

		// Invoquer l'appli
		NoteCreateDto returnedDto = converter.convertEntityToCreateDto(note);

		// Vérifier si les résultats sont des success
		assertThat(returnedDto).hasFieldOrPropertyWithValue("value", 15);
		assertNotNull(returnedDto);
	}

	@Test
	public void testConvertingNullNote_shouldReturnNullNoteCreateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}

	@Test
	public void testConvertingNoteToNoteUpdateDto() {
		// Préparer les inputs
		Note note = new Note();
		note.setId(1);
		note.setValeur(15);
		note.setEtudiant(null);
		note.setExamen(null);

		// Invoquer l'appli
		NoteUpdateDto returnedDto = converter.convertEntityToUpdateDto(note);

		// Vérifier si les résultats sont des success
		assertThat(returnedDto).hasFieldOrPropertyWithValue("value", 15);
		assertNotNull(returnedDto);
	}

	@Test
	public void testConvertingNullNote_shouldReturnNullNoteUpdateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
}