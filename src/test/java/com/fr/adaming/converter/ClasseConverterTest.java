package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.entity.Classe;

@SpringBootTest
public class ClasseConverterTest {
	
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
}
