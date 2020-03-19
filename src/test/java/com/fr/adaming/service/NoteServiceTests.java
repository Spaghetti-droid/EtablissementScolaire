package com.fr.adaming.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Note;
import com.fr.adaming.enumeration.Type;

@SpringBootTest
public class NoteServiceTests {

	@Autowired
	private NoteService service;

	// Valid
	@Test
	@Sql(statements = "insert into examen values (1, 2, '2020-05-21', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Etudiant (id, cp, num, sexe, cni, email) values (1,0,0,0, 123456, 'tm.cloarec@gmail.com')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = { "delete from Note", "delete from Etudiant",
			"delete from Examen" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingValidNote_shouldReturnNote() {

		Etudiant etu = new Etudiant(1, null, null, 123456, "tm.cloarec@gmail.com");

		Examen exam = new Examen();
		exam.setId(1);
		exam.setCoef(2);
		LocalDate date = LocalDate.parse("2020-05-21");
		exam.setDate(date);
		exam.setType(Type.CC);

		Note note = new Note(15, etu, exam);

		Note noteServ = service.create(note);

		// test res
		assertTrue(noteServ.getId() > 0);

	}

	// NegativeValue
	@Test
	@Sql(statements = "insert into examen values (1, 2, '2020-05-21', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Etudiant (id, cp, num, sexe, cni, email) values (1,0,0,0, 123456, 'tm.cloarec@gmail.com' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = { "delete from Note", "delete from Etudiant", "delete from Examen" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingValidNoteWithValeurNull_shouldReturnNull() {

		Etudiant etu = new Etudiant(1, null, null, 123456, "tm.cloarec@gmail.com");

		Examen exam = new Examen();
		exam.setId(1);
		exam.setCoef(2);
		LocalDate date = LocalDate.parse("2020-05-21");
		exam.setDate(date);
		exam.setType(Type.CC);

		Note note = new Note(-5, etu, exam);

		Note noteServ = service.create(note);

		// test res
		assertNull(noteServ);

	}

	// EtudiantNull
	@Test
	@Sql(statements = "insert into examen values (1, 2, '2020-05-21', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = { "delete from Note", "delete from Etudiant",
			"delete from Examen" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingValidNoteWithEtudiantNull_shouldReturnNull() {

		Examen exam = new Examen();
		exam.setId(1);
		exam.setCoef(2);
		LocalDate date = LocalDate.parse("2020-05-21");
		exam.setDate(date);
		exam.setType(Type.CC);

		Note note = new Note(15, null, exam);

		Note noteServ = service.create(note);

		// test res
		assertNull(noteServ);

	}

	// ReadAll

	@Test
	@Sql(statements = "insert into Note (id, valeur) values (1, 15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, valeur) values (2, 18)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAllWithExistingEntires_shouldReturnList() {

		List<Note> noteList = service.readAll();

		assertTrue(noteList.size() == 2);
		assertEquals(15, noteList.get(0).getValeur());
		assertEquals(18, noteList.get(1).getValeur());
	}

	// ReadById

	@Test
	@Sql(statements = "insert into Note (id, valeur) values (1, 15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByIdWithValidId_shouldReturnNote() {
		Note note = new Note(15, null, null);
		Note noteServ = service.readById(1);

		assertEquals(note.getValeur(), noteServ.getValeur());

	}

	// if !exists

	@Test
	public void testReadByIdWithBadId_shouldReturnNull() {

		Note noteServ = service.readById(1);

		assertNull(noteServ);

	}
	// ExistById

		@Test
		@Sql(statements = "insert into Note (id, valeur) values (1, 15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "insert into Note (id, valeur) values (2, 18)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void testExistByID() {

			assertTrue(service.existsById(1));
			assertFalse(service.existsById(5));
		}
		
		// DeleteById
		
		@Test
		@Sql(statements = "insert into Note (id, valeur) values (1, 15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "insert into Note (id, valeur) values (2, 18)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void testDeleteById() {
			
			assertTrue(service.deleteById(1));
	
			
		}
}