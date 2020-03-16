package com.fr.adaming.service;

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
	public void testFindByIdWithValidId() {
		Matiere matin = new Matiere(1, "bob", null);
		Matiere matout = service.readById(1);

		assertEquals(matin.getNom(), matout.getNom());

	}
	
	// if !exists
	
	// *** ReadByNom ***
	
	// nom valid
	// nom null
	// nom abscent
	
	// *** ExistsById ***
	
	// *** DeleteById ***
	
	// *** Update ***
	
	// valide
	// mauvais id
	// nom null 
	
	// *** readExamenByNomMatiere ***
	
	// valide
	// mauvais nom
	// nom null
	// pas d'examens

}
