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

import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.enumeration.Sexe;

/**
 * @author Thierry
 * @author Lea
 * @author Jeanne-Marie
 * @author Isaline
 * 
 *  <b>Description : </b>
 * <p>Classe de test pour le converter de l'entite Absence, implemente IConverterTest</p>
 *
 */
@SpringBootTest
public class AbsenceConverterTest implements IConverterTest {
	
	@Autowired
	private AbsenceConverter converter;
	
	@Test
	@Override
	public void testConvertingCreateDtoToEntity() {
		
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
	
	@Override
	@Test
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
		assertNull(converter.convertCreateDtoToEntity(null));
		
	}

	@Override
	@Test
	public void testConvertingUpdateDtoToEntity() {
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

	@Override
	@Test
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		assertNull(converter.convertUpdateDtoToEntity(null));
		
	}

	@Override
	@Test
	public void testConvertingEntityToCreateDto() {
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

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
		
	}

	@Override
	@Test
	public void testConvertingEntityToUpdateDto() {
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

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		assertNull(converter.convertEntityToUpdateDto(null));
		
	}

	@Override
	@Test
	public void testConvertingListEntityToCreateDto() {
		Absence absence1 = new Absence();
		Absence absence2 = new Absence();
		List<Absence> listeEntity = new ArrayList<>();
		listeEntity.add(absence1);
		listeEntity.add(absence2);
		
		List<AbsenceCreateDto> listeDto = converter.convertListEntityToCreateDto(listeEntity);
		
		assertNotNull(listeDto);
		assertThat(listeDto).contains(converter.convertEntityToCreateDto(absence1));
		assertThat(listeDto).contains(converter.convertEntityToCreateDto(absence2));
		
	}

	@Override
	@Test
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
			assertThat(converter.convertListEntityToCreateDto(null)).isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingListCreateDtoToEntity() {
		AbsenceCreateDto absence1 = new AbsenceCreateDto();
		AbsenceCreateDto absence2 = new AbsenceCreateDto();
		List<AbsenceCreateDto> listeCreateDto = new ArrayList<>();
		listeCreateDto.add(absence1);
		listeCreateDto.add(absence2);
		
		List<Absence> liste = converter.convertListCreateDtoToEntity(listeCreateDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertCreateDtoToEntity(absence1));
		assertThat(liste).contains(converter.convertCreateDtoToEntity(absence2));
		
	}

	@Override
	@Test
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListCreateDtoToEntity(null)).isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingListEntityToUpdateDto() {
		Absence absence1 = new Absence();
		Absence absence2 = new Absence();
		List<Absence> liste = new ArrayList<>();
		liste.add(absence1);
		liste.add(absence2);
		
		List<AbsenceUpdateDto> listeUpdateDto = converter.convertListEntityToUpdateDto(liste);
		
		assertNotNull(listeUpdateDto);
		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto(absence1));
		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto(absence2));
		
	}

	@Override
	@Test
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		assertThat(converter.convertListEntityToUpdateDto(null)).isEmpty();
		
	}

	@Override
	@Test
	public void testConvertingListUpdateDtoToEntity() {
		AbsenceUpdateDto absence1 = new AbsenceUpdateDto();
		AbsenceUpdateDto absence2 = new AbsenceUpdateDto();
		List<AbsenceUpdateDto> listeUpdateDto = new ArrayList<>();
		listeUpdateDto.add(absence1);
		listeUpdateDto.add(absence2);
		
		List<Absence> liste = converter.convertListUpdateDtoToEntity(listeUpdateDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(absence1));
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(absence2));
		
	}

	@Override
	@Test
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListUpdateDtoToEntity(null)).isEmpty();
		
	}

}
