package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.service.IEtudiantService;

@SpringBootTest
public class AbsenceConverterTest {
	
	@Autowired
	private IConverter<AbsenceCreateDto, AbsenceUpdateDto, Absence> converter;
	
	@Autowired
	private IEtudiantService service;

	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion CreateDto to Absence")
	@Test
	public void testConvertingCreateDtoToAbsence_shouldReturnAbsence() {
		AbsenceCreateDto absCDto = new AbsenceCreateDto();
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		absCDto.setDateStart(date);
		absCDto.setDateEnd(date);
		absCDto.setJustif("justif");
		absCDto.setDescript("descript");
		absCDto.setId_etudiant(5);
		
		Absence absReturned = converter.convertCreateDtoToEntity(absCDto);
		
		assertNotNull(absReturned);
		assertEquals(localDate, absReturned.getDateDebut());
		assertEquals(localDate, absReturned.getDateFin());
		assertEquals("justif", absReturned.getJustification());
		assertEquals("descript", absReturned.getDescription());
		assertEquals(5, absReturned.getEtudiant().getId());
		assertEquals("email@email.com", absReturned.getEtudiant().getEmail());
	}
	
	@DisplayName(value = "Invalid - Conversion CreateDto NULL to Absence")
	@Test
	public void testConvertingCreateDtoNULLToAbsence_shouldReturnNull() {
		AbsenceCreateDto absCDto = null;
		Absence absReturned = converter.convertCreateDtoToEntity(absCDto);
		
		assertNull(absReturned);
	}
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion absence to CreateDto")
	@Test
	public void testConvertingAbsenceToCreateDto_shouldReturnAbsenceCreateDto() {
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		Etudiant etudiant = service.readById(5);
		Absence abs = new Absence(1, localDate, localDate, "justif", "descript", etudiant);
		
		AbsenceCreateDto absCDTOReturned = converter.convertEntityToCreateDto(abs);
				
		assertNotNull(absCDTOReturned);
		assertEquals("2019-03-23", absCDTOReturned.getDateStart());
		assertEquals("2019-03-23", absCDTOReturned.getDateEnd());
		assertEquals("justif", absCDTOReturned.getJustif());
		assertEquals("descript", absCDTOReturned.getDescript());
		assertEquals(5, absCDTOReturned.getId_etudiant());
	}
	
	@DisplayName(value = "Conversion absence NULL to CreateDto")
	@Test
	public void testConvertingAbsenceNULLToCreateDto_shouldReturnNull() {
		Absence abs = null;
		
		AbsenceCreateDto absCDTOReturned = converter.convertEntityToCreateDto(abs);
				
		assertNull(absCDTOReturned);
	}
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion UpdateDto to Absence")
	@Test
	public void testConvertingUpdateDtoToAbsence_shouldReturnAbsence() {
		AbsenceUpdateDto absUDto = new AbsenceUpdateDto();
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		absUDto.setIdentifiant(1);
		absUDto.setDateStart(date);
		absUDto.setDateEnd(date);
		absUDto.setJustif("justif");
		absUDto.setDescript("descript");
		absUDto.setId_etudiant(5);
		
		Absence absReturned = converter.convertUpdateDtoToEntity(absUDto);
		
		assertNotNull(absReturned);
		assertEquals(1, absReturned.getId());
		assertEquals(localDate, absReturned.getDateDebut());
		assertEquals(localDate, absReturned.getDateFin());
		assertEquals("justif", absReturned.getJustification());
		assertEquals("descript", absReturned.getDescription());
		assertEquals(5, absReturned.getEtudiant().getId());
		assertEquals("email@email.com", absReturned.getEtudiant().getEmail());
	}
	
	@DisplayName(value = "Conversion UpdateDto Nulle to Absence")
	@Test
	public void testConvertingUpdateDtoNULLToAbsence_shouldReturnNULL() {
		AbsenceUpdateDto absUDto = null;
		
		Absence absReturned = converter.convertUpdateDtoToEntity(absUDto);
		
		assertNull(absReturned);
	}
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion absence to UpdateDto")
	@Test
	public void testConvertingAbsenceToUpdateDto_shouldReturnAbsenceUpdateDto() {
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		Etudiant etudiant = service.readById(5);
		Absence abs = new Absence(1, localDate, localDate, "justif", "descript", etudiant);
		
		AbsenceUpdateDto absUDTOReturned = converter.convertEntityToUpdateDto(abs);
				
		assertNotNull(absUDTOReturned);
		assertEquals(1, absUDTOReturned.getIdentifiant());
		assertEquals("2019-03-23", absUDTOReturned.getDateStart());
		assertEquals("2019-03-23", absUDTOReturned.getDateEnd());
		assertEquals("justif", absUDTOReturned.getJustif());
		assertEquals("descript", absUDTOReturned.getDescript());
		assertEquals(5, absUDTOReturned.getId_etudiant());
	
	}
	
	@DisplayName(value = "Conversion absence NULLE to UpdateDto")
	@Test
	public void testConvertingAbsenceNULLToUpdateDto_shouldReturnNull() {
		Absence abs = null;
		
		AbsenceUpdateDto absUDTOReturned = converter.convertEntityToUpdateDto(abs);
				
		assertNull(absUDTOReturned);
	}
	
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion liste absenceCreateDto to liste absence")
	@Test
	public void testConvertingListCreateDtoToAbsence_shouldReturnListAbsence() {
		AbsenceCreateDto absCDto = new AbsenceCreateDto();
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		absCDto.setDateStart(date);
		absCDto.setDateEnd(date);
		absCDto.setJustif("justif");
		absCDto.setDescript("descript");
		absCDto.setId_etudiant(5);
		
		AbsenceCreateDto absCDto1 = new AbsenceCreateDto();
		absCDto1.setDateStart(date);
		absCDto1.setDateEnd(date);
		absCDto1.setJustif("justif");
		absCDto1.setDescript("descript");
		absCDto1.setId_etudiant(5);
		
		List<AbsenceCreateDto> listCDto = new ArrayList<AbsenceCreateDto>();
		listCDto.add(absCDto);
		listCDto.add(absCDto1);
		
		List<Absence> listAbsReturned = converter.convertListCreateDtoToEntity(listCDto);
		
		assertTrue(listAbsReturned.size() > 1);		
	}
	
	@DisplayName(value = "Conversion liste empty absenceCreateDto to liste absence")
	@Test
	public void testConvertingListNULLCreateDtoToAbsence_shouldReturnListEmpty() {
		List<AbsenceCreateDto> listCDto = new ArrayList<AbsenceCreateDto>();

		List<Absence> listAbsReturned = converter.convertListCreateDtoToEntity(listCDto);
		
		assertTrue(listAbsReturned.size() == 0);		
	}
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion liste absenceUpdateDto to liste absence")
	@Test
	public void testConvertingListUpdateDtoToAbsence_shouldReturnListAbsence() {
		AbsenceUpdateDto absUDto = new AbsenceUpdateDto();
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		absUDto.setIdentifiant(1);
		absUDto.setDateStart(date);
		absUDto.setDateEnd(date);
		absUDto.setJustif("justif");
		absUDto.setDescript("descript");
		absUDto.setId_etudiant(5);
		
		AbsenceUpdateDto absUDto1 = new AbsenceUpdateDto();
		absUDto1.setIdentifiant(2);
		absUDto1.setDateStart(date);
		absUDto1.setDateEnd(date);
		absUDto1.setJustif("justif");
		absUDto1.setDescript("descript");
		absUDto1.setId_etudiant(5);
		
		List<AbsenceUpdateDto> listUDto = new ArrayList<AbsenceUpdateDto>();
		listUDto.add(absUDto);
		listUDto.add(absUDto1);
		
		List<Absence> listAbsReturned = converter.convertListUpdateDtoToEntity(listUDto);
		
		assertTrue(listAbsReturned.size() > 1);		
	}
	
	@DisplayName(value = "Conversion liste absenceUpdateDto NULL to liste absence")
	@Test
	public void testConvertingListNULLUpdateDtoToAbsence_shouldReturnListEmpty() {		
		List<AbsenceUpdateDto> listUDto = new ArrayList<AbsenceUpdateDto>();
		
		List<Absence> listAbsReturned = converter.convertListUpdateDtoToEntity(listUDto);
		
		assertTrue(listAbsReturned.size() == 0);		
	}
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion liste absence to liste absenceCreateDto")
	@Test
	public void testConvertingListAbsenceToCreateDto_shouldReturnListCreateDto() {
		Absence abs = new Absence();
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		abs.setId(1);
		abs.setDateDebut(localDate);
		abs.setDateFin(localDate);
		abs.setJustification("justif");
		abs.setDescription("descript");
		abs.setEtudiant(service.readById(5));
		
		Absence abs1 = new Absence();
		abs1.setId(2);
		abs1.setDateDebut(localDate);
		abs1.setDateFin(localDate);
		abs1.setJustification("justif");
		abs1.setDescription("descript");
		abs1.setEtudiant(service.readById(5));
		
		List<Absence> listAbs = new ArrayList<Absence>();
		listAbs.add(abs);
		listAbs.add(abs1);
		
		List<AbsenceCreateDto> listAbsCDTOReturned = converter.convertListEntityToCreateDto(listAbs);
				
		assertTrue(listAbsCDTOReturned.size() > 1);		
	}
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion liste absence NULL to liste absenceCreateDto")
	@Test
	public void testConvertingListAbsenceNULLToCreateDto_shouldReturnListEmpty() {		
		List<Absence> listAbs = new ArrayList<Absence>();
	
		List<AbsenceCreateDto> listAbsCDTOReturned = converter.convertListEntityToCreateDto(listAbs);
				
		assertTrue(listAbsCDTOReturned.size() == 0);		
	}
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion liste absence to liste absenceUpdateDto")
	@Test
	public void testConvertingListAbsenceToUpdateDto_shouldReturnListUpdateDto() {
		Absence abs = new Absence();
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		abs.setId(1);
		abs.setDateDebut(localDate);
		abs.setDateFin(localDate);
		abs.setJustification("justif");
		abs.setDescription("descript");
		abs.setEtudiant(service.readById(5));
		
		Absence abs1 = new Absence();
		abs1.setId(2);
		abs1.setDateDebut(localDate);
		abs1.setDateFin(localDate);
		abs1.setJustification("justif");
		abs1.setDescription("descript");
		abs1.setEtudiant(service.readById(5));
		
		List<Absence> listAbs = new ArrayList<Absence>();
		listAbs.add(abs);
		listAbs.add(abs1);
		
		List<AbsenceUpdateDto> listAbsUDTOReturned = converter.convertListEntityToUpdateDto(listAbs);
				
		assertTrue(listAbsUDTOReturned.size() > 1);		
	}
	
	@DisplayName(value = "Conversion liste absence NULL to liste absenceUpdateDto")
	@Test
	public void testConvertingListAbsenceNULLToUpdateDto_shouldReturnListEmpty() {		
		List<Absence> listAbs = new ArrayList<Absence>();
		
		List<AbsenceUpdateDto> listAbsUDTOReturned = converter.convertListEntityToUpdateDto(listAbs);
				
		assertTrue(listAbsUDTOReturned.size() == 0);		
	}
	
}
