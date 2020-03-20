package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.entity.Note;

@SpringBootTest
public class NoteConverterTests implements IConverterTest {

	@Autowired
	private NoteConverter converter;

	@Test
	@Override
	public void testConvertingCreateDtoToEntity() {
		
		NoteCreateDto dto = new NoteCreateDto();
		dto.setValue(5);
		Note returnedNote = converter.convertCreateDtoToEntity(dto);
		assertThat(returnedNote).hasFieldOrPropertyWithValue("valeur", 5);
		assertNotNull(returnedNote);
	}

	@Test
	@Override
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		assertNull(converter.convertCreateDtoToEntity(null));
	}

	@Test
	@Override
	public void testConvertingUpdateDtoToEntity() {
		NoteUpdateDto dto = new NoteUpdateDto();
		dto.setId(1);
		dto.setValue(15);
		Note returnedClasse = converter.convertUpdateDtoToEntity(dto);

		assertThat(returnedClasse).hasFieldOrPropertyWithValue("id", 1);
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("valeur", 15);
		assertNotNull(returnedClasse);
	}

	@Test
	@Override
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}

	@Test
	@Override
	public void testConvertingEntityToCreateDto() {

		Note note = new Note();
		note.setValeur(15);
		NoteCreateDto returnedDto = converter.convertEntityToCreateDto(note);

		assertThat(returnedDto).hasFieldOrPropertyWithValue("value", 15);
		assertNotNull(returnedDto);
	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}

	@Test
	@Override
	public void testConvertingEntityToUpdateDto() {
		Note note = new Note();
		note.setId(1);
		note.setValeur(15);
		note.setEtudiant(null);
		note.setExamen(null);

		NoteUpdateDto returnedDto = converter.convertEntityToUpdateDto(note);

		assertThat(returnedDto).hasFieldOrPropertyWithValue("value", 15);
		assertNotNull(returnedDto);
	}

	@Test
	@Override
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	@Override
	public void testConvertingListEntityToCreateDto() {
		Note note1 = new Note();
		Note note2 = new Note();
		List<Note> liste = new ArrayList<>();
		liste.add(note1);
		liste.add(note2);
		
		List<NoteCreateDto> listeUpdateDto = converter.convertListEntityToCreateDto(liste);
		
		assertNotNull(listeUpdateDto);
		NoteCreateDto classeDto1 = converter.convertEntityToCreateDto(note1);
		NoteCreateDto classeDto2 = converter.convertEntityToCreateDto(note2);
		assertThat(listeUpdateDto).contains(classeDto1);
		assertThat(listeUpdateDto).contains(classeDto2);
		
	}

	@Test
	@Override
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		assertThat(converter.convertListEntityToCreateDto(null)).isEmpty();
		
	}

	@Test
	@Override
	public void testConvertingListCreateDtoToEntity() {
		NoteCreateDto dto1 = new NoteCreateDto();
		NoteCreateDto dto2 = new NoteCreateDto();
		List<NoteCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Note> liste = converter.convertListCreateDtoToEntity(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto2));
		
		
	}

	@Test
	@Override
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListCreateDtoToEntity(null)).isEmpty();
		
	}

	@Test
	@Override
	public void testConvertingListEntityToUpdateDto() {
		Note note1 = new Note();
		Note note2 = new Note();
		List<Note> liste = new ArrayList<>();
		liste.add(note1);
		liste.add(note2);
		
		List<NoteUpdateDto> listeUpdateDto = converter.convertListEntityToUpdateDto(liste);
		
		assertNotNull(listeUpdateDto);
		NoteUpdateDto classeDto1 = converter.convertEntityToUpdateDto(note1);
		NoteUpdateDto classeDto2 = converter.convertEntityToUpdateDto(note2);
		assertThat(listeUpdateDto).contains(classeDto1);
		assertThat(listeUpdateDto).contains(classeDto2);
		
	}

	@Test
	@Override
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		assertThat(converter.convertListEntityToUpdateDto(null)).isEmpty();
		
	}

	@Test
	@Override
	public void testConvertingListUpdateDtoToEntity() {
		NoteUpdateDto dto1 = new NoteUpdateDto();
		NoteUpdateDto dto2 = new NoteUpdateDto();
		List<NoteUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Note> liste = converter.convertListUpdateDtoToEntity(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto2));
		
	}

	@Test
	@Override
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListUpdateDtoToEntity(null)).isEmpty();
		
	}
}