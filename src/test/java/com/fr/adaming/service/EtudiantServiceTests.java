package com.fr.adaming.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.enumeration.Sexe;

@SpringBootTest
public class EtudiantServiceTests {

	@Autowired
	private IEtudiantService etuService;

	@BeforeAll
	public static void beforeAllMethodTest() {
		System.out.println("*********Before all method*********");
	}

	@AfterAll
	public static void afterAllMethodTest() {
		System.out.println("*********After all method*********");
	}

	// Create Tests

	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingValidUser_shouldReturnUserWithId() {

		// Préparer les inputs
		Etudiant etuInput = new Etudiant("Rodgers", "Steve", "avengersTower", 69000, "Lyon", Sexe.M, 156148431,
				0236151414, "rodgers@marvel.fr");
		assertEquals(0, etuInput.getId());
		// Invoquer l'appli
		Etudiant returnEtu = etuService.create(etuInput);

		// Vérifier le retour
		assertTrue(returnEtu.getId() > 0);
		// Ne peut pas utiliser les méthodes que l'ont a redéfinis dans la dao

	}

	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingUserWithExistingEmail_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 55, "rodgers@marvel.fr")));
	}

	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithNullEmail_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 55, null)));
	}

	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingUserWithExistingCni_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 19000205, "rod@marvel.com")));
	}

	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithNullCni_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 0, "rod@marvel.com")));
	}

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithExistingId_shouldReturnNull() {
		Etudiant etu = new Etudiant("Natasha", "romanof", 151515151, "natR@avengers.fr");
		etu.setId(5);
		assertNull(etuService.create(etu));

	}

	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithNullUser_shouldReturnNull() {
		assertNull(etuService.create(null));
	}

	// ReadAll tests

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testreadAllValidArguments_shouldReturnListOfUser() { 
		List<Etudiant> expectedList = new ArrayList<Etudiant>();
		expectedList.add(new Etudiant(5, "rodger", "steve", null, 69500, null, null, 19000205, 0235645611, "rodgers@marvel.fr"));
		assertEquals(expectedList, etuService.readAll());
	}

	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testreadAllEmptyTable_shouldReturnEmptyListOfUser() {
		assertEquals(0, etuService.readAll().size());
	}

	// Update Tests

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateValidArguments_shouldReturnTrue() { 
		Etudiant etu = new Etudiant(5, "Rodgers", "Steve", 19000205, "rodgers@avengers.fr");
		assertTrue(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateNullEmail_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 545684842, null);
		assertFalse(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateNullCni_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 0, "ironman@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(9, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateWithExistingEmail_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 545684842, "rodgers@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(9, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateWithExistingCni_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 19000205, "ironman@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(9, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateWithExistingId_shouldReturnFalse() {
		Etudiant etu = new Etudiant(9, "Stark", "Tony", 545684842, "ironman@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	// Delete Tests

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testdeleteByIdValidId_shouldReturnTrue() {
		assertTrue(etuService.deleteById(8));
	}

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testdeleteByIdInValidId_shouldReturnFalse() {
		assertFalse(etuService.deleteById(0));
	}

	
	
}
