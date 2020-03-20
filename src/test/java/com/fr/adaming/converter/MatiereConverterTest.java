package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.entity.Matiere;

@SpringBootTest
public class MatiereConverterTest implements IConverterTest{

	@Autowired
	private IConverter<MatiereCreateDto, MatiereUpdateDto, Matiere> converter;
	
	@Test
	@Override
	public void testConvertingCreateDtoToEntity() {
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
	@Override
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		assertNull(converter.convertCreateDtoToEntity(null));
	}
	
	@Test
	@Override
	public void testConvertingUpdateDtoToEntity() {
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
	@Override
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}

	@Test
	@Override
	public void testConvertingEntityToCreateDto() {
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
	@Override
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	@Override
	public void testConvertingEntityToUpdateDto() {
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
	@Override
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	@Override
	public void testConvertingListEntityToUpdateDto() {
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
	@Override
	public void  testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		assertThat(converter.convertListEntityToUpdateDto(null)).isEmpty();
	}
	
	@Test
	@Override
	public void testConvertingListUpdateDtoToEntity () {
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
	@Override
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListUpdateDtoToEntity(null)).isEmpty();
	}

	
	@Test
	@Override
	public void testConvertingListEntityToCreateDto() {
		Matiere matiere1 = new Matiere();
		Matiere matiere2 = new Matiere();
		List<Matiere> liste = new ArrayList<>();
		liste.add(matiere1);
		liste.add(matiere2);
		
		List<MatiereCreateDto> listeCreateDto = converter.convertListEntityToCreateDto(liste);
		
		assertNotNull(listeCreateDto);
		assertThat(listeCreateDto).contains(converter.convertEntityToCreateDto(matiere1));
		assertThat(listeCreateDto).contains(converter.convertEntityToCreateDto(matiere2));
	}
	
	@Test
	@Override
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		assertThat(converter.convertListEntityToCreateDto(null)).isEmpty();;
	}
	
	@Test
	@Override
	public void testConvertingListCreateDtoToEntity () {
		MatiereCreateDto dto1 = new MatiereCreateDto();
		MatiereCreateDto dto2 = new MatiereCreateDto();
		List<MatiereCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Matiere> liste = converter.convertListCreateDtoToEntity(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto2));
	}
	
	@Test
	@Override
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListCreateDtoToEntity(null)).isEmpty();

	}
}
