package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

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
		absCDto.setDateStart(localDate);
		absCDto.setDateEnd(localDate);
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
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(5, '02010201' ,'email@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Conversion absence to CreateDto")
	@Test
	public void testConvertingAbsenceToCreateDto_shouldReturnAbsenceCreateDto() {
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		Etudiant etudiant = service.readById(5);
		Absence abs = new Absence(localDate, localDate, "justif", "descript", etudiant);
		
		AbsenceCreateDto absCDTOReturned = converter.convertEntityToCreateDto(abs);
				
		assertNotNull(absCDTOReturned);
		assertEquals(localDate, absCDTOReturned.getDateStart());
		assertEquals(localDate, absCDTOReturned.getDateEnd());
		assertEquals("justif", absCDTOReturned.getJustif());
		assertEquals("descript", absCDTOReturned.getDescript());
//		assertEquals(5, absCDTOReturned.getId_etudiant());
	
	}
	
	
	
	
	
	
	
}
