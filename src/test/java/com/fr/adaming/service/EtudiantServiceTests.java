package com.fr.adaming.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

	@Sql(statements = "insert into etudiant values(5, 'Rodgers' ,'Steve', '19000205', 'rodgers@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingUserWithExistingEmail_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 55, "rodgers@marvel.fr")));
	}

	@Test
	@Sql(statements = "delete from user", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithNullEmail_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 55, null)));
	}

	@Sql(statements = "insert into etudiant values(5, 'Rodgers' ,'Steve', '19000205', 'rodgers@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingUserWithExistingCni_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 19000205, "rod@marvel.com")));
	}

	@Test
	@Sql(statements = "delete from user", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithNullCni_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 0, "rod@marvel.com")));
	}

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithExistingId_shouldReturnNull() {
		Etudiant etu = new Etudiant("Natasha", "romanof", 151515151, "natR@avengers.fr");
		etu.setId(8);
		assertNull(etuService.create(etu));

	}

	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithNullUser_shouldReturnNull() {
		assertNull(etuService.create(null));
	}

	// ReadAll tests

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testreadAllValidArguments_shouldReturnListOfUser() {
		List<Etudiant> expectedList = new ArrayList<Etudiant>();
		expectedList.add(new Etudiant("Stark", "Tony", 2566826, "ironMan@marvel.fr"));
		assertEquals(expectedList, etuService.readAll());
	}

	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testreadAllEmptyTable_shouldReturnEmptyListOfUser() {
		assertEquals(0, etuService.readAll().size());
	}

	// Update Tests

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateValidArguments_shouldReturnTrue() {
		Etudiant etu = new Etudiant(8, "Natasha", "romanof", 56481, "blackwidow@ggg.fr");
		assertTrue(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateNullEmail_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 545684842, null);
		assertFalse(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateNullCni_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 0, "ironman@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant values(9, 'Rodgers' ,'Steve', 025896435 ,'steverodgers@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateWithExistingEmail_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 545684842, "steverodgers@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant values(9, 'Rodgers' ,'Steve', 025264350 ,'steverodgers@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateWithExistingCni_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 025264350, "ironman@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant values(9, 'Rodgers' ,'Steve', 025264350 ,'steverodgers@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateWithExistingId_shouldReturnFalse() {
		Etudiant etu = new Etudiant(9, "Stark", "Tony", 545684842, "ironman@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	// Delete Tests

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testdeleteByIdValidId_shouldReturnTrue() {
		assertTrue(etuService.deleteById(8));
	}

	@Test
	@Sql(statements = "insert into etudiant values(8, 'Stark' ,'Tony', 545684842 ,'ironman@marvel.fr')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testdeleteByIdInValidId_shouldReturnFalse() {
		assertFalse(etuService.deleteById(0));
	}

}
