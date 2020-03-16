package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.entity.Classe;

@SpringBootTest
public class ExamenConverterTest {

	
	@Test
	public void testConvertingExamenCreateDtoToExamen() {
		ExamenCreateDto dto = new ExamenCreateDto();
		dto.setCoefExamen(2);
		dto.setDateExamen();
		
		
		//Invoquer l'appli
		Classe returnedClasse = ClasseConverter.convertClasseCreateDtoToClasse(dto);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("nom", "5e1");
		assertNotNull(returnedClasse);
	}
	
	@Test
	public void testConvertingNullExamenCreateDto_shouldReturnNullExamen() {
		assertNull(ClasseConverter.convertClasseCreateDtoToClasse(null));
	}
	
	@Test
	public void testConvertingClasseUpdateDtoToClasse() {
		//Préparer les inputs
		ClasseUpdateDto dto = new ClasseUpdateDto();
		dto.setId(1);
		dto.setName("5e1");
		
		//Invoquer l'appli
		Classe returnedClasse = ClasseConverter.convertClasseUpdateDtoToClasse(dto);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("id", 1);
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("nom", "5e1");
		assertNotNull(returnedClasse);
	}
	
	@Test
	public void testConvertingNullClasseUpdateDto_shouldReturnNullUser() {
		assertNull(ClasseConverter.convertClasseUpdateDtoToClasse(null));
	}

	@Test
	public void testConvertingClasseToClasseCreateDto() {
		//Préparer les inputs
		Classe classe = new Classe();
		classe.setNom("5e1");
		
		//Invoquer l'appli
		ClasseCreateDto returnedDto = ClasseConverter.convertClasseToClasseCreateDto(classe);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedDto).hasFieldOrPropertyWithValue("name", "5e1");
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullClasse_shouldReturnNullClasseCreateDto() {
		assertNull(ClasseConverter.convertClasseToClasseCreateDto(null));
	}
	
	@Test
	public void testConvertingClasseToClasseUpdateDto() {
		//Préparer les inputs
		Classe classe = new Classe();
		classe.setNom("5e1");
		
		//Invoquer l'appli
		ClasseCreateDto returnedDto = ClasseConverter.convertClasseToClasseCreateDto(classe);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedDto).hasFieldOrPropertyWithValue("name", "5e1");
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullClasse_shouldReturnNullClasseUpdateDto() {
		assertNull(ClasseConverter.convertClasseToClasseCreateDto(null));
	}
	
	@Test
	public void testConvertingListClasseToListClasseUpdateDto () {
		Classe classe1 = new Classe();
		Classe classe2 = new Classe();
		List<Classe> liste = new ArrayList<>();
		liste.add(classe1);
		liste.add(classe2);
		
		List<ClasseUpdateDto> listeUpdateDto = ClasseConverter.convertListClasseToListClasseUpdateDto(liste);
		
		assertNotNull(listeUpdateDto);
		ClasseUpdateDto classeDto1 = ClasseConverter.convertClasseToClasseUpdateDto(classe1);
		ClasseUpdateDto classeDto2 = ClasseConverter.convertClasseToClasseUpdateDto(classe2);
		assertThat(listeUpdateDto).contains(classeDto1);
		assertThat(listeUpdateDto).contains(classeDto2);
	}
	
	@Test
	public void testConvertingNullListClasse_shouldReturnNullListClasseUpdateDto() {
		assertNull(ClasseConverter.convertListClasseToListClasseUpdateDto(null));
	}
	
	@Test
	public void testConvertingListClasseUpdateDtoToListClasse () {
		ClasseUpdateDto dto1 = new ClasseUpdateDto();
		ClasseUpdateDto dto2 = new ClasseUpdateDto();
		List<ClasseUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Classe> liste = ClasseConverter.convertListClasseUpdateDtoToListClasse(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(ClasseConverter.convertClasseUpdateDtoToClasse(dto1));
		assertThat(liste).contains(ClasseConverter.convertClasseUpdateDtoToClasse(dto2));
	}
	
	@Test
	public void testConvertingNullListClasseUpdateDto_shouldReturnNullList() {
		assertNull(ClasseConverter.convertListClasseUpdateDtoToListClasse(null));
	}
	
}
