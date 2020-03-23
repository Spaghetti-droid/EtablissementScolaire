package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Note;

/**
 * Class EtudiantServiceTest, implementant l'interface IserviceTest, permettant de tester EtudiantService
 * @author Lea
 * @author Lucie
 * @author Gregoire
 *
 */
@SpringBootTest
public class EtudiantServiceTests implements IServiceTest{

	@Autowired
	private EtudiantService etuService;



	// Create Tests


	/**
	 * Methode permettant de tester la creation d'un objet
	 * le resultat du test devrait etre un objet
	 */
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingValidUser_shouldReturnUserWithId() {

		// Préparer les inputs
		Etudiant etuInput = new Etudiant(69000, 156148431, 0236151414, "rodgers@marvel.fr");
		assertEquals(0, etuInput.getId());
		// Invoquer l'appli
		Etudiant returnEtu = etuService.create(etuInput);

		// Vérifier le retour
		assertTrue(returnEtu.getId() > 0);
		// Ne peut pas utiliser les méthodes que l'ont a redéfinis dans la dao

	}


	/**
	 * Methode permettant de tester la creation d'un objet avec un champ email déjà utilisé
	 * le resultat du test devrait etre null
	 */
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingUserWithExistingEmail_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 55, "rodgers@marvel.fr")));
	}

	/**
	 * Methode permettant de tester la creation d'un objet avec un champ email null
	 * le resultat du test devrait etre null
	 */
	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithNullEmail_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 55, null)));
	}

	/**
	 * Methode permettant de tester la creation d'un objet avec un champ cni déjà utilisé
	 * le resultat du test devrait etre null
	 */
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingUserWithExistingCni_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 19000205, "rod@marvel.com")));
	}

	/**
	 * Methode permettant de tester la creation d'un objet avec un champ cni null
	 * le resultat du test devrait etre null
	 */
	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithNullCni_shouldReturnNull() {
		assertNull(etuService.create(new Etudiant("Martin", "Martine", 0, "rod@marvel.com")));
	}

	/**
	 * Methode permettant de tester la creation d'un objet avec un champ id déjà utilisé
	 * le resultat du test devrait etre null
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithExistingId_shouldReturnNull() {
		Etudiant etu = new Etudiant("Natasha", "romanof", 151515151, "natR@avengers.fr");
		etu.setId(5);
		assertNull(etuService.create(etu));

	}

	/**
	 * Methode permettant de tester la creation d'un objet null
	 * le resultat du test devrait etre null
	 */
	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingUserWithNullUser_shouldReturnNull() {
		assertNull(etuService.create(null));
	}

	// ReadAll tests

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values(5, 19000205, 69500, 'rodgers@marvel.fr' , 235645611)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadAllWithContent_shouldReturnList() { 
		List<Etudiant> expectedList = new ArrayList<Etudiant>();
		expectedList.add(new Etudiant(5, 69500, 19000205, 235645611, "rodgers@marvel.fr"));
		assertEquals(expectedList, etuService.readAll());
	}

	@Test
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadAllNoContent_shouldReturnEmptyList() {
		assertEquals(0, etuService.readAll().size());
	}
	
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values(5, 19000205, 69500, 'rodgers@marvel.fr' , 235645611)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadByIdValidId_shouldReturnEntity() { 
		Etudiant expectedEtu = new Etudiant(5, 69500, 19000205, 235645611, "rodgers@marvel.fr");
		assertEquals(expectedEtu, etuService.readById(5));
	}
	
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() { 

		assertNull(etuService.readById(3));
	}
	
	/**
	 * Methode permettant de tester la recherche d'un objet par email, parametre valide
	 * le resultat du test devrait etre un objet
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testreadByValidEmail_shouldReturnEtudiant() { 

		Etudiant expectedEtu = new Etudiant(5, 69500, 19000205, 235645611, "rodgers@marvel.fr");
		expectedEtu.setNom("Rodgers");
		expectedEtu.setPrenom("Steve");
		assertEquals(expectedEtu, etuService.readByEmail("rodgers@marvel.fr"));
		
	}
	/**
	 * Methode permettant de tester la recherche d'un objet par email, parametre non valide
	 * le resultat du test devrait etre null
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testreadByInvalidEmail_shouldReturnNull() { 

		assertNull(etuService.readByEmail("Aaaaaaaah@marvel.fr"));
	}
	
	/**
	 * Methode permettant de tester la recherche d'un objet par email, parametre null
	 * le resultat du test devrait etre null
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testreadByNullEmail_shouldReturnNull() { 

		assertNull(etuService.readByEmail(null));
	}	

	/**
	 * Methode permettant de tester l'update d'un objet, parametres valides
	 * le resultat du test devrait etre positif
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(5, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateValidArguments_shouldReturnTrue() { 
		Etudiant etu = new Etudiant(5, "Rodgers", "Steve", 19000205, "rodgers@avengers.fr");
		assertTrue(etuService.update(etu));
	}

	/**
	 * Methode permettant de tester l'update d'un objet, champ email null
	 * le resultat du test devrait etre négatif
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateNullEmail_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 545684842, null);
		assertFalse(etuService.update(etu));
	}

	/**
	 * Methode permettant de tester l'update d'un objet, champ cni null
	 * le resultat du test devrait etre négatif
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateNullCni_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 0, "ironman@marvel.fr");
		assertFalse(etuService.update(etu));
	}
	
	/**
	 * Methode permettant de tester l'update d'un objet, objet null
	 * le resultat du test devrait etre négatif
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateNullEntity_shouldReturnFalse() {
		assertFalse(etuService.update(null));
	}
	
	/**
	 * Methode permettant de tester l'update d'un objet, champ id non valide
	 * le resultat du test devrait etre négatif
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateInvalidId_shouldReturnFalse() {
		Etudiant etu = new Etudiant(10001, "Stark", "Tony", 6, "ironman@marvel.fr");
		assertFalse(etuService.update(etu));
	}
	
	/**
	 * Methode permettant de tester l'update d'un objet, champ cni déjà utilisé
	 * le resultat du test devrait etre négatif
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(9, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateConflictingCni_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 19000205, "ironman@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	/**
	 * Methode permettant de tester l'update d'un objet, champ email déjà utilisé
	 * le resultat du test devrait etre négatif
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(9, 19000205, 69500, 'rodgers@marvel.fr','Rodgers' , 0235645611, 'Steve')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testupdateWithExistingEmail_shouldReturnFalse() {
		Etudiant etu = new Etudiant(8, "Stark", "Tony", 545684842, "rodgers@marvel.fr");
		assertFalse(etuService.update(etu));
	}

	
	/**
	 * Methode permettant de tester l'update d'un objet, champ id déjà utilisé
	 * le resultat du test devrait etre négatif
	 */
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
	@Override
	public void testDeletingValidId_shouldReturnTrue() {
		assertTrue(etuService.deleteById(8));
	}

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 0235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {
		assertFalse(etuService.deleteById(0));
	}

	/**
	 * Methode permettant de tester la recherche d'Absence par email Etudiant, parametre valide
	 * le resultat du test devrait etre une liste d'absence
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into absence (id, date_debut, date_fin, description, justification, etudiant_id) values (1, '2020-05-21', '2020-05-21', 'blah', 'blah', 8)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into absence (id, date_debut, date_fin, description, justification, etudiant_id) values (2, '2020-05-25', '2020-05-25', 'blah', 'blah', 8)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAbsenceByValidEmail_shouldReturnList() {
		
		Etudiant etu = new Etudiant();
		etu.setId(8);
		etu.setCni(545684842);
		etu.setCp(69500);
		etu.setEmail("ironman@marvel.fr");
		etu.setNom("Stark");
		etu.setPrenom("Tony");
		etu.setNum(235645611);
		
		Absence	a1 = new Absence();
		a1.setId(1);
		a1.setDateDebut(LocalDate.parse("2020-05-21"));
		a1.setDateFin(LocalDate.parse("2020-05-21"));
		a1.setDescription("blah");
		a1.setJustification("blah");
		a1.setEtudiant(etu);
		
		Absence	a2 = new Absence();
		a2.setId(2);
		a2.setDateDebut(LocalDate.parse("2020-05-25"));
		a2.setDateFin(LocalDate.parse("2020-05-25"));
		a2.setDescription("blah");
		a2.setJustification("blah");
		a2.setEtudiant(etu);
		
		
		List<Absence> expectedList = new ArrayList<>();
		expectedList.add(a1);
		expectedList.add(a2);
		
		List<Absence> absenceList = etuService.readAbsenceByEtudiantEmail("ironman@marvel.fr");
		
		assertTrue(absenceList.size() == 2);
		assertEquals(expectedList, absenceList);
		
	}
	
	/**
	 * Methode permettant de tester la recherche d'Absence par email Etudiant, parametre non valide
	 * le resultat du test devrait etre une liste vide
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into absence (id, date_debut, date_fin, description, justification, etudiant_id) values (1, '2020-05-21', '2020-05-21', 'blah', 'blah', 8)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into absence (id, date_debut, date_fin, description, justification, etudiant_id) values (2, '2020-05-25', '2020-05-25', 'blah', 'blah', 8)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAbsenceByInvalidEmail_shouldReturnEmptyList() {
		
		List<Absence> absenceList = etuService.readAbsenceByEtudiantEmail("batman@marvel.fr");
		
		assertThat(absenceList).isEmpty();
		
	}
	
	/**
	 * Methode permettant de tester la recherche d'Absence par email Etudiant, parametre null
	 * le resultat du test devrait etre une liste vide
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into absence (id, date_debut, date_fin, description, justification, etudiant_id) values (1, '2020-05-21', '2020-05-21', 'blah', 'blah', 8)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into absence (id, date_debut, date_fin, description, justification, etudiant_id) values (2, '2020-05-25', '2020-05-25', 'blah', 'blah', 8)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAbsenceByNullEmail_shouldReturnEmptyList() {
		
		List<Absence> absenceList = etuService.readAbsenceByEtudiantEmail(null);
		
		assertThat(absenceList).isEmpty();
		
	}
	
	/**
	 * Methode permettant de tester la recherche de Note par email Etudiant, parametre valide
	 * le resultat du test devrait etre une liste de Note
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen (id, coef, date) values (1, 2, '2020-05-21') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen (id, coef, date) values (2, 2, '2020-05-21') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, valeur, etudiant_id, examen_id) values (1, 15, 8, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, valeur, etudiant_id, examen_id) values (2, 18, 8, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadNoteByValidEmail_shouldReturnList() {
		
		Etudiant etu = new Etudiant();
		etu.setId(8);
		etu.setCni(545684842);
		etu.setCp(69500);
		etu.setEmail("ironman@marvel.fr");
		etu.setNom("Stark");
		etu.setPrenom("Tony");
		etu.setNum(235645611);
		
		Examen ex1 = new Examen();
		ex1.setId(1);
		ex1.setCoef(2);
		ex1.setDate(LocalDate.parse("2020-05-21"));
		
		Examen ex2 = new Examen();
		ex2.setId(2);
		ex2.setCoef(2);
		ex2.setDate(LocalDate.parse("2020-05-21"));
		
		Note n1 = new Note();
		n1.setId(1);
		n1.setValeur(15);
		n1.setEtudiant(etu);
		n1.setExamen(ex1);
		
		Note n2 = new Note();
		n2.setId(2);
		n2.setValeur(18);
		n2.setEtudiant(etu);
		n2.setExamen(ex2);
		
		List<Note> expectedList = new ArrayList<>();
		expectedList.add(n1);
		expectedList.add(n2);
		
		List<Note> noteList = etuService.readNoteByEtudiantEmail("ironman@marvel.fr");
		
		assertTrue(noteList.size() == 2);
		assertEquals(expectedList, noteList);
		
	}
	
	/**
	 * Methode permettant de tester la recherche de Note par email Etudiant, parametre non valide
	 * le resultat du test devrait etre une liste vide
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen (id, coef, date) values (1, 2, '2020-05-21') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen (id, coef, date) values (2, 2, '2020-05-21') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, valeur, etudiant_id, examen_id) values (1, 15, 8, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, valeur, etudiant_id, examen_id) values (2, 18, 8, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadNoteByInvalidEmail_shouldReturnEmptyList() {
		
		List<Note> noteList = etuService.readNoteByEtudiantEmail("batman@marvel.fr");
		
		assertThat(noteList).isEmpty();
		
	}
	
	/**
	 * Methode permettant de tester la recherche de Note par email Etudiant, parametre null
	 * le resultat du test devrait etre une liste vide
	 */
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen (id, coef, date) values (1, 2, '2020-05-21') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen (id, coef, date) values (2, 2, '2020-05-21') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, valeur, etudiant_id, examen_id) values (1, 15, 8, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, valeur, etudiant_id, examen_id) values (2, 18, 8, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadNoteByNullEmail_shouldReturnEmptyList() {
		
		List<Note> noteList = etuService.readNoteByEtudiantEmail(null);
		
		assertThat(noteList).isEmpty();
		
	}

	@Override
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values(5, 19000205, 69500, 'rodgers@marvel.fr' , 235645611)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdValidId_ShouldReturnTrue() {
		
		assertTrue(etuService.existsById(5));
		
	}

	@Override
	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values(5, 19000205, 69500, 'rodgers@marvel.fr' , 235645611)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsByIdInValidId_ShouldReturnFalse() {

		assertFalse(etuService.existsById(10001));
		
	}
	
	
}
