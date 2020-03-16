package com.fr.adaming.converter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.enumeration.Sexe;

@SpringBootTest
public class EtudiantConverterTest {

	@BeforeAll
	public static void beforeAllMethodTest() {
		System.out.println("*********Before all method*********");
	}

	@AfterAll
	public static void afterAllMethodTest() {
		System.out.println("*********After all method*********");
	}

	@Test
	public void testConvertingEtudiantCreateDtoToEtudiant() {
		// Préparer les inputs
		EtudiantCreateDto dto = new EtudiantCreateDto();
		dto.setSurname("Tony");
		dto.setName("Stark");
		dto.setAdress("Malibu Point 10880");
		dto.setPostalCode(90265);
		dto.setCity("Malibu");
		dto.setS(Sexe.M);
		dto.setIdentity(000000002000);
		dto.setPhone(0221546435);
		dto.setMail("ironMan@marvel.fr");

		// Invoquer l'appli
		Etudiant returnEtudiant = EtudiantConverter.convertEtudiantCreateDtoToEtudiant(dto);

		// Vérifier le résultat
		// User doit être différent de null
		assertNotNull(returnEtudiant);

	}
	
	@Test
	public void testConvertingEtudiantUpdateDtoToEtudiant() {
		// Préparer les inputs
		EtudiantUpdateDto dto = new EtudiantUpdateDto();
		dto.setSurname("Tony");
		dto.setName("Stark");
		dto.setAdress("Malibu Point 10880");
		dto.setPostalCode(90265);
		dto.setCity("Malibu");
		dto.setS(Sexe.M);
		dto.setIdentity(000000002000);
		dto.setPhone(0221546435);
		dto.setMail("ironMan@marvel.fr");
//		dto.setIdClassroom(2);
//		dto.setIdMatiere(3);
//		A voir si c'est possible

		// Invoquer l'appli
		Etudiant returnEtudiant = EtudiantConverter.convertEtudiantUpdateDtoToEtudiant(dto);

		// Vérifier le résultat
		// User doit être différent de null
		assertNotNull(returnEtudiant);

	}
	
	//Dois-je faire les tests dans les deux sens ?

}
