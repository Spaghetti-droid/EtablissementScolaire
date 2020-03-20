package com.fr.adaming.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;

@SpringBootTest
public class MatiereServiceTests implements IServiceTest {
	
	@Autowired
	private MatiereService service;
	
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
	@Sql(statements = "delete from etudiant_matiere_list",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql(statements = "delete from etudiant_matiere_list",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingMatiereWithNomNull_shouldReturnNull(){
		
		List<Etudiant> etuList= new ArrayList<Etudiant>();
		
		etuList.add(new Etudiant(IDETU, null, null, CNI, EMAIL));
		
		Matiere mat = new Matiere(null, etuList);

		Matiere matOut = service.create(mat);

		// test res
		assertNull(matOut); 
		
	}
	
	// Matiere NULL
	
	@Test
	@Sql(statements = "insert into Etudiant (id, cp, num, sexe, cni, email) values (1,0,0,0, "+CNI+", "+EMAILSQL+")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant_matiere_list",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingMatiereNull_shouldReturnNull(){
		
		Matiere matOut = service.create(null);

		// test res
		assertNull(matOut); 
		
	}
	// ID pre-existant
	
	@Test
	@Sql(statements = "insert into Etudiant (id, cp, num, sexe, cni, email) values (1,0,0,0, "+CNI+", "+EMAILSQL+")", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant_matiere_list",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Override
	public void testReadAllWithContent_shouldReturnList(){
		
		List<Matiere> matList = service.readAll();
		
		assertTrue(matList.size() == 2);
		assertEquals("bob", matList.get(0).getNom());
		assertEquals("fish", matList.get(1).getNom());
		
		
	}
	
	
	@Override
	public void testReadAllNoContent_shouldReturnEmptyList() {
		
		List<Matiere> matList = service.readAll();
		assertThat(matList).isEmpty();
		
	}
	
	
	// *** ReadById ***
	
	//if ID exists
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testReadByIdValidId_shouldReturnEntity() {
		Matiere matin = new Matiere(1, "bob", null);
		Matiere matout = service.readById(1);

		assertEquals(matin.getNom(), matout.getNom());

	}
	
	// if !exists
	@Test
	@Override
	public void testReadByIdInvalidId_shouldReturnNull() {
		
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
	@Override
	public void testExistsByIdValidId_ShouldReturnTrue() {
		
		assertTrue(service.existsById(1));
		
		
	}	
	
	@Override
	public void testExistsByIdInValidId_ShouldReturnFalse() {		

		assertFalse(service.existsById(5));
		
	}
	
	// *** DeleteById ***
	
	// Existing ID
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testDeletingValidId_shouldReturnTrue() {
		
		assertTrue(service.deleteById(2));	
		
	}
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testDeletingInvalidId_shouldReturnFalse() {


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
	
	// matiere NULL 
	
		@Test
		@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void testUpdateWithMatiereNull_ShouldReturnFalse() {
						
			assertFalse(service.update(null));
			
		}
		
		@Test
		@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values(1, 1, 1, 'bob@email.com' , 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values(2, 2, 2, 'bob2@email.com' , 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from etudiant_matiere_list",executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Sql(statements = "delete from Etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void testUpdateWithListOfValidAndInvalidEtudiant_shouldReturnDiminishedList() {
			
			Etudiant e1 = new Etudiant();
			e1.setId(1);
			e1.setCni(1);
			e1.setCp(1);
			e1.setEmail("bob@email.com");
			e1.setNum(1);
			
			Etudiant e2 = new Etudiant();
			e2.setId(2);
			e2.setCni(2);
			e2.setCp(2);
			e2.setEmail("bob2@email.com");
			e2.setNum(2);
			
			Etudiant e3 = new Etudiant();
			e3.setId(3);
			e3.setCni(3);
			e3.setCp(3);
			e3.setEmail("sqbdk@dbqjh.com");
			e3.setNum(3);
			
			List<Etudiant> etudiantList = new ArrayList<>();
			etudiantList.add(e1);
			etudiantList.add(e2);
			etudiantList.add(e3);
			
			Matiere mat = new Matiere(1, "bob", etudiantList);
			
			assertTrue(service.update(mat));
			
		}
		
	
	// *** readExamenByNomMatiere ***
	
	// valide
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (1, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (2, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (3, 2, '2000-01-01', 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadExamenByValidNomMat_shouldReturnList() {
		
		List<Examen> exams = service.readExamenByNomMatiere("bob");
		
		assertTrue(exams.size() == 2);
		assertEquals("bob", exams.get(0).getMatiere().getNom());
		
	}
	
	// mauvais nom
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (1, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (2, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (3, 2, '2000-01-01', 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadExamenByBadNomMat_shouldReturnEmpty() {
		
		List<Examen> exams = service.readExamenByNomMatiere("Fred");
		
		assertThat(exams).isEmpty();
		
	}
	
	// nom null
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (1, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (2, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (3, 2, '2000-01-01', 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadExamenByNullNomMat_shouldReturnNull() {
		
		List<Examen> exams = service.readExamenByNomMatiere(null);
		
		assertThat(exams).isEmpty();
		
	}
	
	// pas d'examens

	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadEmptyExamenByNomMat_shouldReturnEmptyList() {
		
		List<Examen> exams = service.readExamenByNomMatiere("bob");
		
		assertThat(exams).isEmpty();
		
	}


	
}
