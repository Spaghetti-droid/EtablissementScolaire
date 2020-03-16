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


@SpringBootTest
public class AbsenceServiceTest {
	
	@Autowired
	private IAbsenceService service; 
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript'), (2, '2000-01-05', '2000-01-25', 'justif2', 'descript2')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Valide - Récupération liste Absence")
	@Test
	public void testReadAllAbsence_shouldReturnListAbsenceUpdateDto() {
		assertTrue(service.readAll().size() > 1);
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Valide - Récupération liste Absence si une seule absence")
	@Test
	public void testReadAllAbsence1Absence_shouldReturnListAbsenceUpdateDto() {
		assertTrue(service.readAll().size() == 1);
	}
	
	@DisplayName(value = "Valide - Récupération liste Absence si table vide")
	@Test
	public void testReadAllAbsenceNull_shouldReturnListEmpty() {
		assertTrue(service.readAll().size() == 0);
	}

	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Valide - Récupération produit via Id existant")
	@Test
	public void testReadByIdExistant_shouldReturnProduit() {
		assertNotNull(service.readById(1));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Invalide - Récupération produit via Id inexistant")
	@Test
	public void testReadByIdInexistant_shouldReturnNull() {
		assertNull(service.readById(2));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Valide - Suppression absence via Id existant")
	@Test
	public void testDeleteByIdExistant_shouldReturnTrue() {
		assertTrue(service.deleteById(1));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Invalide - Suppression absence via Id inexistant")
	@Test
	public void testDeleteByIdInexistant_shouldReturnFalse() {
		assertFalse(service.deleteById(2));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Valide - Modification absence")
	@Test
	public void testUpdateAbsenceExistante_shouldReturnTrue() {
		Absence abs = new Absence();
		abs.setId(1);
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		abs.setDateDebut(localDate);
		assertTrue(service.update(abs));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Invalide - Modification absence inexistante")
	@Test
	public void testUpdateAbsenceInexistante_shouldReturnFalse() {
		Absence abs = new Absence();
		abs.setId(2);
		String date = "2019-03-23";
		LocalDate localDate = LocalDate.parse(date);
		abs.setDateDebut(localDate);
		assertFalse(service.update(abs));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Invalide - Modification absence = NULL")
	@Test
	public void testUpdateAbsenceNull_shouldReturnFalse() {
		assertFalse(service.update(null));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Valide - Recherche (existById) absence")
	@Test
	public void testExistByIdExistant_shouldReturnTrue() {
		assertTrue(service.existsById(1));
	}
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Invalide - Recherche (existById) absence via Id inexistant")
	@Test
	public void testExistByIdInexistant_shouldReturnFalse() {
		assertFalse(service.existsById(2));
	}
	
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Valide - Création absence")
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
	
	@Sql(statements = "insert into Absence (id, date_debut, date_fin, justification, description) values (1, '2000-01-05', '2000-01-25', 'justif', 'descript')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Invalide - Création absence avec Id existant")
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
	
	@Sql(statements = "truncate table Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@DisplayName(value = "Invalide - Création absence avec date début nulle (seule contrainte non nullable)")
	@Test
	public void testCreateAbsenceDateDebutNull_shouldReturnNull() {
		Absence abs = new Absence();
		
		Absence newAbs = service.create(abs);
		
		assertNull(newAbs);
	}
	
	@DisplayName(value = "Invalide - Création absence nulle")
	@Test
	public void testCreateAbsenceNull_shouldReturnNull() {
		Absence abs = null;
		assertNull(service.create(abs));
	}
	
	
	
	
	
}
 