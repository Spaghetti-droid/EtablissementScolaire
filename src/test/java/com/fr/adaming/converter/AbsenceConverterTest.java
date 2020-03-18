package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Sexe;

@SpringBootTest
public class AbsenceConverterTest {
	
	@Autowired
	private AbsenceConverter converter;
	
	@Test
	public void testconvertValidCreateDtoToEntity_shouldReturnValidEntity() {
		AbsenceCreateDto dto = new AbsenceCreateDto("2017-03-20", "2017-03-21", "parce que", "c'est comme ça", 
				new EtudiantUpdateDto(1, "coston", "lea", "ici", 69003, "Lyon", Sexe.F, 123456789, 123456789, "bla@bla.bla", null));
		Absence returnedAbsence = converter.convertCreateDtoToEntity(dto);
		
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse(dto.getDateStart()));
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("dateFin", LocalDate.parse(dto.getDateEnd()));
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("justification", dto.getJustif());
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("description", dto.getDescript());
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("etudiant", new Etudiant(1, "coston", "lea", "ici", 69003, "Lyon", Sexe.F, 123456789, 123456789, "bla@bla.bla", null));
		
		assertNotNull(returnedAbsence);
		
		
	}
	
	@Test
	public void testconvertNullCreateDtoToEntity_shouldReturnNull() {
		
		assertNull(converter.convertCreateDtoToEntity(null));
		
	}
	
	@Test
	public void testconvertValidEntityToCreateDto_shouldReturnValidCreateDto() {
		Absence entity = new Absence(LocalDate.parse("2002-03-19"),LocalDate.parse("2002-03-20"), "c'est la vie", "mon pti", 
				new Etudiant(1, "coston", "lea", "ici", 69003, "Lyon", Sexe.F, 123456789, 123456789, "bla@bla.bla", null));
		AbsenceCreateDto returnedDto = converter.convertEntityToCreateDto(entity);
		
		assertThat(returnedDto).hasFieldOrPropertyWithValue("dateStart", entity.getDateDebut().toString());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("dateEnd", entity.getDateFin().toString());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("justif", entity.getJustification());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("descript", entity.getDescription());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("etudiant", new EtudiantUpdateDto(1, "coston", "lea", "ici", 69003, "Lyon", Sexe.F, 123456789, 123456789, "bla@bla.bla", null));
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testconvertNullEntityToCreateDto_shouldReturnNull() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	public void testconvertValidUpdateDtoToEntity_shouldReturnValidEntity() {
		AbsenceUpdateDto dto = new AbsenceUpdateDto(1, "2017-03-20", "2017-03-21", "parce que", "c'est comme ça", 
				new EtudiantUpdateDto(1, "coston", "lea", "ici", 69003, "Lyon", Sexe.F, 123456789, 123456789, "bla@bla.bla", null));
		Absence returnedAbsence = converter.convertUpdateDtoToEntity(dto);
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("id", dto.getIdentifiant());
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("dateDebut", LocalDate.parse(dto.getDateStart()));
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("dateFin", LocalDate.parse(dto.getDateEnd()));
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("justification", dto.getJustif());
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("description", dto.getDescript());
		assertThat(returnedAbsence).hasFieldOrPropertyWithValue("etudiant", new Etudiant(1, "coston", "lea", "ici", 69003, "Lyon", Sexe.F, 123456789, 123456789, "bla@bla.bla", null));
		
		assertNotNull(returnedAbsence);
	}
	
	@Test
	public void testconvertNullUpdateDtoToEntity_shouldReturnNull() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}
	
	@Test
	public void testconvertValidEntityToUpdateDto_shouldReturnValidUpdateDto() {
		Absence entity = new Absence(1, LocalDate.parse("2002-03-18"),LocalDate.parse("2002-03-19"), "c'est la vie", "mon pti", 
				new Etudiant(1, "coston", "lea", "ici", 69003, "Lyon", Sexe.F, 123456789, 123456789, "bla@bla.bla", null));
		AbsenceUpdateDto returnedDto = converter.convertEntityToUpdateDto(entity);
		
		assertThat(returnedDto).hasFieldOrPropertyWithValue("identifiant", entity.getId());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("dateStart", entity.getDateDebut().toString());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("dateEnd", entity.getDateFin().toString());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("justif", entity.getJustification());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("descript", entity.getDescription());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("etudiant", new EtudiantUpdateDto(1, "coston", "lea", "ici", 69003, "Lyon", Sexe.F, 123456789, 123456789, "bla@bla.bla", null));
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testconvertNullEntityToUpdateDto_shouldReturnNull() {
		assertNull(converter.convertEntityToUpdateDto(null));
	}
	
	@Test
	public void testconvertValidListCreateDtoToEntity_shouldListEntity() {
		
	}
	
	@Test
	public void testconvertNullListCreateDtoToEntity_shouldEmptyList() {
		
	}
	
	@Test
	public void testconvertValidListUpdateDtoToEntity_shouldListEntity() {
		
	}
	
	@Test
	public void testconvertNullListUpdateDtoToEntity_shouldEmptyList() {
		
	}
	
	@Test
	public void testconvertValidListEntityToCreateDto_shouldListCreateDto() {
		
	}
	
	@Test
	public void testconvertNullListEntityToCreateDto_shouldEmptyList() {
		
	}
	
	@Test
	public void testconvertValidListEntityToUpdateDto_shouldListUpdateDto() {
		
	}
	
	@Test
	public void testconvertNullListEntityToUpdateDto_shouldEmptyList() {
		
	}

}
