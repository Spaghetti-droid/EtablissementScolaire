package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.E;
import com.fr.adaming.enumeration.Type;

@SpringBootTest
public class ExamenConverterTest {

	@Autowired
	private ExamenConverter converter;

	
	@Test
	public void testConvertingExamenCreateDtoToExamen_shouldReturnValidExamen() {
		ExamenCreateDto dto = new ExamenCreateDto();
		dto.setCoefExamen(2);
		dto.setDateExamen("2019-03-23");
		dto.setTypeExamen(Type.CC);
		dto.setMatiereExamen(new MatiereUpdateDto(1,"maths",null));
		Examen returnedExamen = converter.convertCreateDtoToEntity(dto);
		
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("date", LocalDate.parse(dto.getDateExamen()));
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("type", dto.getTypeExamen());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("coef", dto.getCoefExamen());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("matiere", new E(1, "maths", null));
		assertNotNull(returnedExamen);
	}
	
	@Test
	public void testConvertingNullExamenCreateDto_shouldReturnNull() {
		assertNull(converter.convertCreateDtoToEntity(null));
	}
	
	@Test
	public void testConvertingEntityUpdateDtoToEntity_shouldReturnValidEntity() {
		ExamenUpdateDto dto = new ExamenUpdateDto();
		dto.setCoefExamen(2);
		dto.setDateExamen("2019-03-23");
		dto.setTypeExamen(Type.CC);
		dto.setIdExam(1);
		dto.setMatiereExamen(new MatiereUpdateDto(1,"maths",null));
		
		Examen returnedExamen = converter.convertUpdateDtoToEntity(dto);
		
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("date",LocalDate.parse(dto.getDateExamen()));
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("type", dto.getTypeExamen());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("coef", dto.getCoefExamen());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("id", dto.getIdExam());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("matiere", new E(1, "maths", null));
		assertNotNull(returnedExamen);
	}
	
	@Test
	public void testConvertingNullExamenUpdateDto_shouldReturnNull() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}

	@Test
	public void testConvertingExamenToExamenCreateDto() {
		Examen examen = new Examen();
		examen.setCoef(2);
		examen.setDate(LocalDate.parse("2019-03-23"));
		examen.setMatiere(new E(1, "maths", null));
		examen.setType(Type.CC);
		
		ExamenCreateDto returnedDto = converter.convertEntityToCreateDto(examen);
	
		assertThat(returnedDto).hasFieldOrPropertyWithValue("dateExamen", examen.getDate().toString());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("typeExamen", examen.getType());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("coefExamen", examen.getCoef());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("matiereExamen", new MatiereUpdateDto(1, "maths", null));
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullExamen_shouldReturnNull() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	public void testConvertingExamenToExamenUpdateDto() {
		Examen examen = new Examen();
		examen.setCoef(2);
		examen.setDate(LocalDate.parse("2019-03-23"));
		examen.setMatiere(new E(1, "maths", null));
		examen.setType(Type.CC);
		examen.setId(1);
		
		ExamenUpdateDto returnedDto = converter.convertEntityToUpdateDto(examen);
	
		assertThat(returnedDto).hasFieldOrPropertyWithValue("dateExamen", examen.getDate().toString());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("typeExamen", examen.getType());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("coefExamen", examen.getCoef());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("idExam", 1);
		assertThat(returnedDto).hasFieldOrPropertyWithValue("matiereExamen", new MatiereUpdateDto(1, "maths", null));
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullExamen_shouldReturnNullExamenUpdateDto() {
		assertNull(converter.convertEntityToUpdateDto(null));
	}
	
	@Test
	public void testConvertingListExamenToListExamenUpdateDto () {
		Examen examen1 = new Examen(1,LocalDate.parse("2019-03-23"),Type.CC,2d,new E(1, "maths", null));
		Examen examen2 = new Examen(2,LocalDate.parse("2019-03-23"),Type.CC,2d,new E(2, "francais", null));
		List<Examen> liste = new ArrayList<>();
		liste.add(examen1);
		liste.add(examen2);
		
		List<ExamenUpdateDto> listeUpdateDto = converter.convertListEntityToUpdateDto(liste);
		
		assertNotNull(listeUpdateDto);
		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto(examen1));
		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto(examen2));
	}
	
	@Test
	public void testConvertingNullListExamen_shouldReturnNullListExamenUpdateDto() {
		assertNull(converter.convertListEntityToUpdateDto(null));
	}
	
	@Test
	public void testConvertingListExamenUpdateDtoToListExamen () {
		ExamenUpdateDto dto1 = new ExamenUpdateDto();
		ExamenUpdateDto dto2 = new ExamenUpdateDto();
		List<ExamenUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Examen> liste = converter.convertListUpdateDtoToEntity(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto2));
	}
	
	@Test
	public void testConvertingNullListExamenUpdateDto_shouldReturnNullListExamen() {
		assertNull(converter.convertListUpdateDtoToEntity(null));
	}
	
	@Test
	public void testConvertingListExamenToListExamenCreateDto () {
		Examen examen1 = new Examen(1,LocalDate.parse("2019-03-23"),Type.CC,2d,new E(1, "maths", null));
		Examen examen2 = new Examen(2,LocalDate.parse("2019-03-23"),Type.CC,2d,new E(2, "francais", null));
		List<Examen> liste = new ArrayList<>();
		liste.add(examen1);
		liste.add(examen2);
		
		List<ExamenCreateDto> listeDto = converter.convertListEntityToCreateDto(liste);
		
		assertNotNull(listeDto);
		assertThat(listeDto).contains(converter.convertEntityToCreateDto(examen1));
		assertThat(listeDto).contains(converter.convertEntityToCreateDto(examen2));
	}
	
	@Test
	public void testConvertingNullListExamen_shouldReturnNullListExamenCreateDto() {
		assertNull(converter.convertListEntityToCreateDto(null));
	}
	
	@Test
	public void testConvertingListExamenCreateDtoToListExamen () {
		ExamenCreateDto dto1 = new ExamenCreateDto();
		ExamenCreateDto dto2 = new ExamenCreateDto();
		List<ExamenCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Examen> liste = converter.convertListCreateDtoToEntity(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto2));
	}
	
	@Test
	public void testConvertingNullListExamenCreateDto_shouldReturnNullListExamen() {
		assertNull(converter.convertListCreateDtoToEntity(null));
	}
	
}
