package com.fr.adaming.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Type;

@SpringBootTest
public class ExamenServiceTest {
	
	@Autowired
	private ExamenService service;
	
	// METHODE CREATION
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into matiere values (1, 'maths') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testCreatingValidExamen_shouldReturnExamenWithId () {
	Matiere mat = new Matiere(1, "maths");	
	Examen examenInput = new Examen();
	LocalDate date = LocalDate.parse("2020-05-21");
	examenInput.setCoef(2);
	examenInput.setDate(date);
	examenInput.setType(Type.CC);
	examenInput.setMatiere(mat);
	assertEquals(0, examenInput.getId());
				
	Examen returnedExamen = service.create(examenInput);
	assertNotEquals(0, returnedExamen.getId());
	
	}
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into matiere values (1, 'maths') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testCreatingExamenWithNotExistingMatiere_shouldReturnNull() {
		Examen examenInput = new Examen();
		LocalDate date = LocalDate.parse("2020-05-21");
		Matiere mat = new Matiere(2, "anglais");
		
		examenInput.setCoef(2);
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		examenInput.setMatiere(mat);
		assertNull(service.create(examenInput));
	}
	
	
	@Test
	public void testCreatingNullExamen_shouldReturnNull() {
		Examen exam = null;
		assertNull(service.create(exam));
		
		
	}
	
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingExamenNullDate_shouldReturnNull() {
		Examen examenInput = new Examen();
		
		examenInput.setCoef(2);
		examenInput.setDate(null);
		examenInput.setType(Type.CC);
		assertNull(service.create(examenInput));
		
	}
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingExamenNullMatiere_shouldReturnNull() {
		Examen examenInput = new Examen();
		LocalDate date = LocalDate.parse("2020-05-21");
		
		examenInput.setCoef(2);
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		examenInput.setMatiere(null);
		assertNull(service.create(examenInput));
		
	}
		
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testCreatingExamenExistingId_shouldReturnNull() {
		Examen examenInput = new Examen();
		examenInput.setId(1);
		examenInput.setCoef(2);
		LocalDate date = LocalDate.parse("2020-05-21");
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		assertNull(service.create(examenInput));
		
	}
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testReadAllWithContent_shouldReturnListOfExamen() {
		 List<Examen> expectedList = new ArrayList<Examen>();
	        expectedList.add(new Examen(1, LocalDate.parse("2020-03-17"),null,2,null));
	        assertEquals(expectedList, service.readAll());
	
	}
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadAllWithNoContent_shouldReturnEmptyList() {
		 assertEquals(0, service.readAll().size());
		
	}
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testReadByIdValidId_shouldReturnExamen() {
		assertEquals(new Examen(1, LocalDate.parse("2020-03-17"),null,2,null),service.readById(1));
	
	}
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testReadByIdInValidId_shouldReturnNull() {
		assertNull(service.readById(2));
	
	}
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testExistsByIdValidId_shouldReturnTrue() {
		assertTrue(service.existsById(1));
	
	}
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testExistsByIdInValidId_shouldReturnFalse() {
		assertFalse(service.existsById(2));
	
	}
	
	
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into matiere values (1, 'maths') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testUpdateValidId_shouldTrue() {
		Matiere mat = new Matiere(1, "maths");
		Examen examenInput = new Examen();
		examenInput.setId(1);
		examenInput.setCoef(2);
		LocalDate date = LocalDate.parse("2020-05-21");
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		examenInput.setMatiere(mat);
		
		assertTrue(service.update(examenInput));
		
	}
	
	
	@Test
	public void testUpdateNullExamen_shouldReturnFalse() {
		assertFalse(service.update(null));
	}
	
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testUpdateInValidId_shouldReturnFalse() {
		Examen examenInput = new Examen();
		examenInput.setId(2);
		examenInput.setCoef(2);
		LocalDate date = LocalDate.parse("2020-05-21");
		examenInput.setDate(date);
		examenInput.setType(Type.CC);
		
		assertFalse(service.update(examenInput));
		
	}
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into examen values (1, 2, '2020-03-17', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testUpdateInValidDate_shouldReturnFalse() {
		Examen examenInput = new Examen();
		examenInput.setId(1);
		examenInput.setCoef(2);
		examenInput.setDate(null);
		examenInput.setType(Type.CC);
		
		assertFalse(service.update(examenInput));
		
	}
	
}