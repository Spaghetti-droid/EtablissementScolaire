package com.fr.adaming.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Absence;
/**
 * Class AbsenceServiceTest, implementant l'interface IserviceTest, permettant de test AbsenceService
 * @author Lea
 * @author Isaline
 *
 */

@SpringBootTest
public class AbsenceServiceTest implements IServiceTest{
	
	@Autowired
	private AbsenceService service; 
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript'), (2, '2000-01-05', '2000-01-25', 'justif2', 'descript2')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadAllWithContent_shouldReturnList() {
		assertTrue(service.readAll().size() > 1);
	}
	
	
	
	@Test
	@Override
	public void testReadAllNoContent_shouldReturnEmptyList() {
		assertTrue(service.readAll().size() == 0);
	}

	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdValidId_shouldReturnEntity() {
		assertNotNull(service.readById(1));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
		assertNull(service.readById(2));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(service.deleteById(1));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(service.deleteById(2));
	}
	
	
	/**
	 * Methode permettant de tester l'update d'un objet avec un Id existant dans la bd
	 *le resultat du test devrait etre positif
	 */
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateAbsenceExistante_shouldReturnTrue() {
		Absence abs = new Absence();
		abs.setId(1);
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		abs.setDateDebut(localDate);
		assertTrue(service.update(abs));
	}
	
	/**
	 * Methode permettant de tester l'update d'un objet avec un Id inexistant
	 * le resultat du test devrait etre négatif
	 */
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateAbsenceInexistante_shouldReturnFalse() {
		Absence abs = new Absence();
		abs.setId(2);
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		abs.setDateDebut(localDate);
		assertFalse(service.update(abs));
	}
	
	
	/**
	 * Methode permettant de tester l'update d'un objet null
	 * le resultat du test devrait etre négatif
	 */
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateAbsenceNull_shouldReturnFalse() {
		assertFalse(service.update(null));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
		assertTrue(service.existsById(1));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {
		assertFalse(service.existsById(2));
	}
	
	
	/**
	 * Methode permettant de tester la creation d'un objet
	 * le resultat du test devrait etre un objet
	 */
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateAbsence_shouldReturnAbsence() {
		Absence abs = new Absence();
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		abs.setDateDebut(localDate);
		
		Absence newAbs = service.create(abs);
		
		assertNotNull(newAbs);
		assertEquals(localDate, newAbs.getDateDebut());
		assertNotEquals(0, newAbs.getId());
	}
	
	
	/**
	 * Methode permettant de tester la creation d'un objet avec un id déja utilisé
	 * le resultat du test devrait etre null
	 */
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateAbsenceIdExistant_shouldReturnNull() {
		Absence abs = new Absence();
		abs.setId(1);
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		abs.setDateDebut(localDate);
		
		Absence newAbs = service.create(abs);
		
		assertNull(newAbs);
	}
	

	/**
	 * Methode permettant de tester la creation d'un objet avec une date de début null
	 * le resultat du test devrait etre null
	 */
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreateAbsenceDateDebutNull_shouldReturnNull() {
		Absence abs = new Absence();
		
		Absence newAbs = service.create(abs);
		
		assertNull(newAbs);
	}
	
	/**
	 * Methode permettant de tester la creation d'un objet null
	 * le resultat du test devrait etre null
	 */
	@DisplayName(value = "Invalide - Création absence nulle")
	@Test
	public void testCreateAbsenceNull_shouldReturnNull() {
		Absence abs = null;
		assertNull(service.create(abs));
	}
	
	
	
	
	
}
 