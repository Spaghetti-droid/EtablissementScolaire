package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.entity.Classe;

@SpringBootTest
public class ConverterClasseTest {
	
	@Test
	public void testConvertingClasseCreateDtoToClasse() {
		//Préparer les inputs
		ClasseCreateDto dto = new ClasseCreateDto();
		dto.setName("5e1");
		
		//Invoquer l'appli
		Classe returnedClasse = ClasseConverter.convertClasseCreateDtoToClasse(dto);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedClasse).hasFieldOrPropertyWithValue("nom", "5e1");
		assertNotNull(returnedClasse);
	}
	
	@Test
	public void testConvertingNullClasseCreateDto_shouldReturnNullUser() {
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

	
}
