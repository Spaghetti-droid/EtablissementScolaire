package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.entity.Classe;

/**
 * <b>Descrition :</b>
 * <p>
 * Classe permettant le test des converters de classe, qui implémente
 * IConverterTest
 * </p>
 * 
 * @author Jeanne-Marie
 * @author Lucie
 * 
 */
@SpringBootTest
public class ClasseConverterTest implements IConverterTest {

	@Autowired
	private IConverter<ClasseCreateDto, ClasseUpdateDto, Classe> converter;

	@Test
	public void testConvertingCreateDtoToEntity() {

		ClasseCreateDto dto = new ClasseCreateDto();
		dto.setName("5e1");

		Classe returnedClasse = converter.convertCreateDtoToEntity(dto);

		assertThat(returnedClasse).hasFieldOrPropertyWithValue("nom", "5e1");
		assertNotNull(returnedClasse);
	}

	@Test
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		assertNull(converter.convertCreateDtoToEntity(null));
	}

	@Override
	public void testConvertingUpdateDtoToEntity() {

		ClasseUpdateDto dto = new ClasseUpdateDto();
		dto.setId(1);
		dto.setName("5e1");

		Classe returnedClasse = converter.convertUpdateDtoToEntity(dto);

		assertThat(returnedClasse).hasFieldOrPropertyWithValue("id", 1);
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("nom", "5e1");
		assertNotNull(returnedClasse);
	}

	@Test
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}

	@Test
	public void testConvertingEntityToCreateDto() {

		Classe classe = new Classe();
		classe.setNom("5e1");

		ClasseCreateDto returnedDto = converter.convertEntityToCreateDto(classe);

		assertThat(returnedDto).hasFieldOrPropertyWithValue("name", "5e1");
		assertNotNull(returnedDto);
	}

	@Override
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}

	@Test
	public void testConvertingEntityToUpdateDto() {

		Classe classe = new Classe();
		classe.setNom("5e1");

		ClasseCreateDto returnedDto = converter.convertEntityToCreateDto(classe);

		assertThat(returnedDto).hasFieldOrPropertyWithValue("name", "5e1");
		assertNotNull(returnedDto);
	}

	@Test
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}

	@Test
	public void testConvertingListEntityToCreateDto() {
		Classe classe1 = new Classe();
		Classe classe2 = new Classe();
		List<Classe> liste = new ArrayList<>();
		liste.add(classe1);
		liste.add(classe2);

		List<ClasseCreateDto> listeCreateDto = converter.convertListEntityToCreateDto(liste);

		assertNotNull(listeCreateDto);
		ClasseCreateDto classeDto1 = converter.convertEntityToCreateDto(classe1);
		ClasseCreateDto classeDto2 = converter.convertEntityToCreateDto(classe2);
		assertThat(listeCreateDto).contains(classeDto1);
		assertThat(listeCreateDto).contains(classeDto2);
	}

	@Test
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		assertThat(converter.convertListEntityToCreateDto(null)).isEmpty();
	}

	@Test
	public void testConvertingListCreateDtoToEntity() {
		ClasseCreateDto dto1 = new ClasseCreateDto();
		ClasseCreateDto dto2 = new ClasseCreateDto();
		List<ClasseCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		List<Classe> liste = converter.convertListCreateDtoToEntity(listeDto);

		assertNotNull(liste);
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto2));
	}

	@Test
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListCreateDtoToEntity(null)).isEmpty();
	}

	@Test
	public void testConvertingListEntityToUpdateDto() {
		Classe classe1 = new Classe();
		Classe classe2 = new Classe();
		List<Classe> liste = new ArrayList<>();
		liste.add(classe1);
		liste.add(classe2);

		List<ClasseUpdateDto> listeUpdateDto = converter.convertListEntityToUpdateDto(liste);

		assertNotNull(listeUpdateDto);
		ClasseUpdateDto classeDto1 = converter.convertEntityToUpdateDto(classe1);
		ClasseUpdateDto classeDto2 = converter.convertEntityToUpdateDto(classe2);
		assertThat(listeUpdateDto).contains(classeDto1);
		assertThat(listeUpdateDto).contains(classeDto2);
	}

	@Test
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		assertThat(converter.convertListEntityToUpdateDto(null)).isEmpty();
	}

	@Test
	public void testConvertingListUpdateDtoToEntity() {
		ClasseUpdateDto dto1 = new ClasseUpdateDto();
		ClasseUpdateDto dto2 = new ClasseUpdateDto();
		List<ClasseUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		List<Classe> liste = converter.convertListUpdateDtoToEntity(listeDto);

		assertNotNull(liste);
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto2));
	}

	@Test
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListUpdateDtoToEntity(null)).isEmpty();
	}

}
