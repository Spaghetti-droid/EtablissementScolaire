package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListCreateDtoToEntity() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListEntityToUpdateDto() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingListUpdateDtoToEntity() {
		// TODO Auto-generated method stub
		
	}

	@Test
	@Override
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		// TODO Auto-generated method stub
		
	}
}