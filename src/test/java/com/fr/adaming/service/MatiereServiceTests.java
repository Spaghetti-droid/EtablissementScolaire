package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Sexe;

@SpringBootTest
public class MatiereServiceTests {
	
	@Autowired
	private IMatiereService service;
	
	//Matiere par defaut
	
	public static final String NOM = "matty";
	public static final int ID = 1;
	
	// Etudiant par defaut
	
	private static final int IDETU = 1;
	private static final int CNI = 1;
	private static final String EMAIL = "bob@email.com";
	
	// versions sql des strings
	
	private static final String NOMSQL = "'" + NOM + "'";
	private static final String EMAILSQL = "'" + EMAIL + "'";
	
	// *** Create ***
	
	// Valid
	@Test
	@Sql(statements = "insert into Etudiant (id, cp, num, sexe, cni, email) values (1,0,0,0, "+CNI+", "+EMAILSQL+")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from matiere_etudiant_list","delete from Etudiant where id=" + IDETU, "delete from Matiere where nom = "+NOMSQL}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingValidMatiere_shouldReturnMatiere(){
		
		List<Etudiant> etuList= new ArrayList<Etudiant>();
		
		etuList.add(new Etudiant(IDETU, null, null, CNI, EMAIL));
		
		Matiere mat = new Matiere(NOM, etuList);

		Matiere matOut = service.create(mat);

		// test res
		assertTrue(matOut.getId() > 0); 
		
	}
	// NomNull
	
	@Test
	@Sql(statements = "insert into Etudiant (id, cp, num, sexe, cni, email) values (1,0,0,0, "+CNI+", "+EMAILSQL+")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from matiere_etudiant_list","delete from Etudiant where id=" + IDETU, "delete from Matiere where nom = "+NOMSQL}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingMatiereWithNomNull_shouldReturnNull(){
		
		List<Etudiant> etuList= new ArrayList<Etudiant>();
		
		etuList.add(new Etudiant(IDETU, null, null, CNI, EMAIL));
		
		Matiere mat = new Matiere(null, etuList);

		Matiere matOut = service.create(mat);

		// test res
		assertNull(matOut); 
		
	}
	
	// ID pre-existant
	
	@Test
	@Sql(statements = "insert into Etudiant (id, cp, num, sexe, cni, email) values (1,0,0,0, "+CNI+", "+EMAILSQL+")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from matiere_etudiant_list","delete from Etudiant where id=" + IDETU, "delete from Matiere where nom = 'bob'"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingMatiereWithIDExistant_shouldReturnNull(){
		
		List<Etudiant> etuList= new ArrayList<Etudiant>();
		
		etuList.add(new Etudiant(IDETU, null, null, CNI, EMAIL));
		
		Matiere mat = new Matiere(1, NOM, etuList);

		Matiere matOut = service.create(mat);

		// test res
		assertNull(matOut); 
		
	}
	
	// *** ReadAll ***

	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAllWithExistingEntires_shouldReturnList() {
		
		List<Matiere> matList = service.readAll();
		
		assertTrue(matList.size() == 2);
		assertEquals("bob", matList.get(0).getNom());
		assertEquals("fish", matList.get(1).getNom());
		
		
	}
	
	// *** ReadById ***
	
	//if ID exists
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByIdWithValidId_shouldReturnMatiere() {
		Matiere matin = new Matiere(1, "bob", null);
		Matiere matout = service.readById(1);

		assertEquals(matin.getNom(), matout.getNom());

	}
	
	// if !exists
	
	public void testReadByIdWithBadId_shouldReturnNull() {
		
		Matiere matout = service.readById(1);

		assertNull(matout);

	}
	
	// *** ReadByNom ***
	
	// nom valid
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByNomWithValidNom_shouldReturnMatiere() {
		
		Matiere matOut = service.readByNom("bob");
		
		assertEquals("bob", matOut.getNom());
		
		
	}
	
	// nom null
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByNomWithNullNom_shouldReturnNull() {
		
		Matiere matOut = service.readByNom(null);
		
		assertNull(matOut);
		
		
	}
	
	// nom absent
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByNomWithBadNom_shouldReturnNull() {
		
		Matiere matOut = service.readByNom("Fred");
		
		assertNull(matOut);
		
		
	}
	
	// *** ExistsById ***
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testExistsById() {
		
		assertTrue(service.existsById(1));
		assertFalse(service.existsById(5));
		
		
	}	
	
	// *** DeleteById ***
	
	// Existing ID
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeleteById_shouldReturnTrueIfIdExists() {
		
		assertTrue(service.deleteById(2));
		assertFalse(service.deleteById(2));		
		
	}
	
	// *** Update ***
	
	// valide
	
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateWithValidInput_ShouldReturnTrue() {
		
		Matiere mat = new Matiere(1, "Zargothrax", null);
		
		assertTrue(service.update(mat));
		
	}
	
	// mauvais id
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateWithBadId_ShouldReturnFalse() {
		
		Matiere mat = new Matiere(20, "Zargothrax", null);
		
		assertFalse(service.update(mat));
		
	}
	
	// nom null 
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateWithNullNom_ShouldReturnFalse() {
		
		Matiere mat = new Matiere(1, null, null);
		
		assertFalse(service.update(mat));
		
	}
	
	// *** readExamenByNomMatiere ***
	
	// valide
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (1, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (2, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (3, 2, '2000-01-01', 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadExamenByValidNomMat_shouldReturnList() {
		
		List<Examen> exams = service.readExamenByNomMatiere("bob");
		
		assertTrue(exams.size() == 2);
		
	}
//	
//	// mauvais nom
//	
//	@Test
//	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (1, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (2, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (3, 2, '2000-01-01', 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "delete from Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	public void testReadExamenByBadNomMat_shouldReturnNull() {
//		
//		List<Examen> exams = service.readExamenByNomMatiere("bob");
//		
//		assertTrue(exams.size() == 2);
//		
//	}
//	
//	// nom null
//	
//	@Test
//	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (1, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (2, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (3, 2, '2000-01-01', 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "delete from Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	public void testReadExamenByNullNomMat_shouldReturnNull() {
//		
//		List<Examen> exams = service.readExamenByNomMatiere("bob");
//		
//		assertTrue(exams.size() == 2);
//		
//	}
	
	// pas d'examens

	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadEmptyExamenByNomMat_shouldReturnEmptyList() {
		
		List<Examen> exams = service.readExamenByNomMatiere("bob");
		
		assertThat(exams).isEmpty();;
		
	}
	
}
