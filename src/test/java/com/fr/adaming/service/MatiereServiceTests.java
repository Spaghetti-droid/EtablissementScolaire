package com.fr.adaming.service;

import static org.junit.Assert.assertTrue;

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
	@Sql(statements = {"delete from Etudiant where id=" + IDETU, "delete from Matiere where id = "+ID}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void TestCreatingValidMatiere_shouldReturnMatiere(){
		
		List<Etudiant> etuList= new ArrayList<Etudiant>();
		
		etuList.add(new Etudiant(IDETU, null, null, CNI, EMAIL));
		
		Matiere mat = new Matiere(NOM, etuList);

		// invoq appli

		Matiere matOut = service.create(mat);

		// test res
		assertTrue(matOut.getId() > 0); 
		
	}
	// NomNull
	
	// *** ReadAll ***
	
	// if exists
	
	// if empty
	
	// *** ReadById ***
	
	//if ID exists
	
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
