package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Classe;

@SpringBootTest
public class ClasseServiceTest {

	@Autowired
	private IClasseService service;
	
	// METHODE CREATION
	
	@Sql(statements = "delete from classe where nom = '5e1'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingValidClasse_shouldReturnClasseWithId () {
	
	Classe userInput = new Classe("5e1");
	assertEquals(0, userInput.getId());
			
	
	Classe returnedClasse = service.create(userInput);
	assertTrue(returnedClasse.getId() > 0);
	}
		
	@Test
	public void testCreatingClasseWithNullName_shouldReturnNull() {
		assertNull(service.create(new Classe(null)));
	}
	
	@Sql(statements = "insert into classe (id, nom) values(5,'5e1')",
			executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 5", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test	
	public void testCreatingClasseWithExistingId_shouldReturnNull() {
		Classe classe = new Classe();
		classe.setId(5);
		assertNull(service.create(classe));
	}
		
	@Test 
	public void testCreatingUserNull_shouldReturnNUll() {
		assertNull(service.create(null));
	}
		
	// METHODE DELETE
		
	@Sql(statements = "insert into classe (id, nom) values(1,'5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingValidId_shouldReturnTrue() {
		boolean retour = service.deleteById(1);
		assertTrue(retour);
	}
		
	@Sql(statements = "insert into classe (id, nom) values(1,'5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingInvalidId_shouldReturnFalse() {
		boolean retour = service.deleteById(3);
		assertFalse(retour);
	}
		
	// METHODE UPDATE
	
	@Sql(statements = "insert into classe (id, nom) values(1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id=1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingValidClasse_shouldReturnTrue() {
		Classe classe = new Classe(1,"5e1");
		classe.setNom("5e2");
			
		boolean retour = service.update(classe);
		assertTrue(retour);
	}
		
	@Test
	public void testUpdatingClasseNull_shouldReturnFalse() {
		assertFalse(service.update(null));
	}
		
	@Sql(statements = "insert into classe (id, nom) values(1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id=1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingClasseWithNullName_shouldReturnFalse() {
		Classe classe = new Classe(1, null);
		assertFalse(service.update(classe));
	}
		
	@Sql(statements = "insert into classe (id, nom) values(1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id=1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingClasseWithInvalidId_shouldReturnFalse() {
		Classe classe = new Classe(2,"5e1");
		assertFalse(service.update(classe));
	}
		
	
	// METHODE READ BY ID
	@Sql(statements = "insert into classe (id, nom) values(1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id=1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingClasseWithValidId_shouldReturnUser() {
		Classe returnedClasse = service.readById(1);
		assertNotNull(returnedClasse);
		assertEquals(1, returnedClasse.getId());
	}
		
	@Sql(statements = "insert into classe (id, nom) values(1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id=1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingClasseWithInvalidId_shouldReturnNull () {
		assertNull(service.readById(2));
	}
		
	@Test
	public void testReadingClasseNull_shouldReturnNull() {
		Classe returnedUser = service.readById(1);
		assertNull(returnedUser);
	}
		
		
	// METHODE READ ALL
	@Sql(statements = {"insert into classe (id, nom) values (1, '5e1')", "insert into classe (id, nom) values (2, '4e2')"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from classe where id=1","delete from classe where id=2"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingAllClasse_shouldReturnClasseList () {
		List<Classe> liste = service.readAll();
		assertNotNull(liste);
		assertThat(liste).hasSize(2);
	}
	
	@Sql (statements = "delete from classe", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testReadingAllClasse_shouldReturnEmptyList () {
		List<Classe> liste = service.readAll();
		assertThat(liste).isEmpty();
	}
		
}
