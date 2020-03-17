package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.controller.IController;
import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.entity.Classe;

@SpringBootTest
public class ClasseConverterTest {
	
	@Autowired
	private IConverter<ClasseCreateDto, ClasseUpdateDto, Classe> converter;
	
	@Test
	public void testConvertingClasseCreateDtoToClasse() {
		//Préparer les inputs
		ClasseCreateDto dto = new ClasseCreateDto();
		dto.setName("5e1");
		
		//Invoquer l'appli
		Classe returnedClasse = converter.convertCreateDtoToEntity(dto);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("nom", "5e1");
		assertNotNull(returnedClasse);
	}
	
	@Test
	public void testConvertingNullClasseCreateDto_shouldReturnNullUser() {
		assertNull(converter.convertCreateDtoToEntity(null));
	}
	
	@Test
	public void testConvertingClasseUpdateDtoToClasse() {
		//Préparer les inputs
		ClasseUpdateDto dto = new ClasseUpdateDto();
		dto.setId(1);
		dto.setName("5e1");
		
		//Invoquer l'appli
		Classe returnedClasse = converter.convertUpdateDtoToEntity(dto);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("id", 1);
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("nom", "5e1");
		assertNotNull(returnedClasse);
	}
	
	@Test
	public void testConvertingNullClasseUpdateDto_shouldReturnNullUser() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}

	@Test
	public void testConvertingClasseToClasseCreateDto() {
		//Préparer les inputs
		Classe classe = new Classe();
		classe.setNom("5e1");
		
		//Invoquer l'appli
		ClasseCreateDto returnedDto = converter.convertEntityToCreateDto(classe);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedDto).hasFieldOrPropertyWithValue("name", "5e1");
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullClasse_shouldReturnNullClasseCreateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	public void testConvertingClasseToClasseUpdateDto() {
		//Préparer les inputs
		Classe classe = new Classe();
		classe.setNom("5e1");
		
		//Invoquer l'appli
		ClasseCreateDto returnedDto = converter.convertEntityToCreateDto(classe);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedDto).hasFieldOrPropertyWithValue("name", "5e1");
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullClasse_shouldReturnNullClasseUpdateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	public void testConvertingListClasseToListClasseUpdateDto () {
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
	public void testConvertingNullListClasse_shouldReturnNullListClasseUpdateDto() {
		assertNull(converter.convertListEntityToUpdateDto(null));
	}
	
	@Test
	public void testConvertingListClasseUpdateDtoToListClasse () {
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
	public void testConvertingNullListClasseUpdateDto_shouldReturnNullList() {
		assertNull(converter.convertListUpdateDtoToEntity(null));
	}
	
}
