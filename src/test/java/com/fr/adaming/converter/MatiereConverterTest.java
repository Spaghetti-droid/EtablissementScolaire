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
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Matiere;

@SpringBootTest
public class MatiereConverterTest {

	@Autowired
	private IConverter<MatiereCreateDto, MatiereUpdateDto, Matiere> converter;
	
	@Test
	public void testConvertingMatiereCreateDtoToMatiere() {
		//Préparer les inputs
		MatiereCreateDto dto = new MatiereCreateDto();
		dto.setNomMatiere("maths");
		
		//Invoquer l'appli
		Matiere returnedMatiere = converter.convertCreateDtoToEntity(dto);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedMatiere).hasFieldOrPropertyWithValue("nom", "maths");
		assertNotNull(returnedMatiere);
	}
	
	@Test
	public void testConvertingNullMatiereCreateDto_shouldReturnNullMatiere() {
		assertNull(converter.convertCreateDtoToEntity(null));
	}
	
	@Test
	public void testConvertingMatiereUpdateDtoToMatiere() {
		//Préparer les inputs
		MatiereUpdateDto dto = new MatiereUpdateDto();
		dto.setIdMatiere(1);
		dto.setNomMatiere("maths");
		
		//Invoquer l'appli
		Matiere returnedMatiere = converter.convertUpdateDtoToEntity(dto);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedMatiere).hasFieldOrPropertyWithValue("id", 1);
		assertThat(returnedMatiere).hasFieldOrPropertyWithValue("nom", "maths");
		assertNotNull(returnedMatiere);
	}
	
	@Test
	public void testConvertingNullMatiereUpdateDto_shouldReturnNullMatiere() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}

	@Test
	public void testConvertingMatiereToMatiereCreateDto() {
		//Préparer les inputs
		Matiere matiere = new Matiere();
		matiere.setNom("maths");
		
		//Invoquer l'appli
		MatiereCreateDto returnedDto = converter.convertEntityToCreateDto(matiere);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedDto).hasFieldOrPropertyWithValue("nomMatiere", "maths");
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullMatiere_shouldReturnNullMatiereCreateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	public void testConvertingMatiereToMatiereUpdateDto() {
		//Préparer les inputs
		Matiere matiere = new Matiere();
		matiere.setNom("maths");
		
		//Invoquer l'appli
		MatiereCreateDto returnedDto = converter.convertEntityToCreateDto(matiere);
		
		//Vérifier si les résultats sont des success
		assertThat(returnedDto).hasFieldOrPropertyWithValue("nomMatiere", "maths");
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullMatiere_shouldReturnNullMatiereUpdateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	public void testConvertingListMatiereToListMatiereUpdateDto () {
		Matiere matiere1 = new Matiere();
		Matiere matiere2 = new Matiere();
		List<Matiere> liste = new ArrayList<>();
		liste.add(matiere1);
		liste.add(matiere2);
		
		List<MatiereUpdateDto> listeUpdateDto = converter.convertListEntityToUpdateDto(liste);
		
		assertNotNull(listeUpdateDto);
		MatiereUpdateDto classeDto1 = converter.convertEntityToUpdateDto(matiere1);
		MatiereUpdateDto classeDto2 = converter.convertEntityToUpdateDto(matiere2);
		assertThat(listeUpdateDto).contains(classeDto1);
		assertThat(listeUpdateDto).contains(classeDto2);
	}
	
	@Test
	public void testConvertingNullListMatiere_shouldReturnNullListMatiereUpdateDto() {
		assertNull(converter.convertListEntityToUpdateDto(null));
	}
	
	@Test
	public void testConvertingListMatiereUpdateDtoToListMatiere () {
		MatiereUpdateDto dto1 = new MatiereUpdateDto();
		MatiereUpdateDto dto2 = new MatiereUpdateDto();
		List<MatiereUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Matiere> liste = converter.convertListUpdateDtoToEntity(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto2));
	}
	
	@Test
	public void testConvertingNullListClasseUpdateDto_shouldReturnNullList() {
		assertNull(converter.convertListUpdateDtoToEntity(null));
	}
}
