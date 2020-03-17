package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Sexe;
import com.fr.adaming.enumeration.Type;

@SpringBootTest
public class EtudiantConverterTests {

	@Autowired
	private EtudiantConverter converter;
	

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
		dto.setIdentity(1);
		dto.setPhone(0221546435);
		dto.setMail("ironMan@marvel.fr");
		
		dto.setClasse(new ClasseUpdateDto(1,"Terminal"));
		
		List<MatiereUpdateDto> matieres = new ArrayList();
		dto.setMatiere(new matieres {"anglais", "français"});
		
		
//		matieres.add("Maths");
//		matieres.add("Français");
//		matieres.add("SVT");
//		matieres.add("Chimie");
		dto.setMatiere(matieres);
		
		// Invoquer l'appli
		Etudiant returnEtudiant = converter.convertCreateDtoToEntity(dto);

		// Vérifier le résultat
		// User doit être différent de null
		assertNotNull(returnEtudiant);

	}
	
	@Test
	public void testConvertingNullEtudiantCreateDto_shouldReturnNullMatiere() {
		assertNull(converter.convertCreateDtoToEntity(null));
	}
	
	@Test
	public void testConvertingEtudiantUpdateDtoToEtudiant() {
		// Préparer les inputs
		EtudiantUpdateDto dto = new EtudiantUpdateDto();
		
		
		dto.setIdentifiant(1);
		dto.setSurname("Tony");
		dto.setName("Stark");
		dto.setAdress("Malibu Point 10880");
		dto.setPostalCode(90265);
		dto.setCity("Malibu");
		dto.setS(Sexe.M);
		dto.setIdentity(000000002000);
		dto.setPhone(0221546435);
		dto.setMail("ironMan@marvel.fr");
		
		
		dto.setClasse(new ClasseUpdateDto(1,"Terminal"));
		
		ArrayList matieres = new ArrayList();
		matieres.add("Maths");
		matieres.add("Français");
		matieres.add("SVT");
		matieres.add("Chimie");
		dto.setMatiere(matieres);
		

		// Invoquer l'appli
		Etudiant returnEtudiant = converter.convertUpdateDtoToEntity(dto);

		// Vérifier le résultat
		// User doit être différent de null
		assertNotNull(returnEtudiant);

	}
	@Test
	public void testConvertingNullEtudiantUpdateDto_shouldReturnNull() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}
	
	@Test
	public void testConvertingEtudiantToEtudiantCreateDto() {
		Etudiant etudiant = new Etudiant();

		
		etudiant.setNom("Stark");
		etudiant.setPrenom("Tony");
		etudiant.setAdresse("Avenue Des Heros");
		etudiant.setCp(69500);
		etudiant.setVille("Malibu");
		etudiant.setSexe(Sexe.M);
		etudiant.setCni(23);
		etudiant.setNum(06322121);
		etudiant.setEmail("ironMan@marvel.fr");
		etudiant.setClasse(new Classe(2, "français"));
		
		ArrayList matieres = new ArrayList();
		matieres.add("Maths");
		matieres.add("Français");
		matieres.add("SVT");
		matieres.add("Chimie");
		etudiant.setMatiereList(matieres);
		
		
		
		EtudiantCreateDto returnedDto = converter.convertEntityToCreateDto(etudiant);
	
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullEtudiant_shouldReturnNull() {
		assertNull(converter.convertEntityToCreateDto(null));
	}
	
	@Test
	public void testConvertingEtudiantToEtudiantUpdateDto() {
		Etudiant etudiant = new Etudiant();

		
		etudiant.setNom("Stark");
		etudiant.setPrenom("Tony");
		etudiant.setAdresse("Avenue Des Heros");
		etudiant.setCp(69500);
		etudiant.setVille("Malibu");
		etudiant.setSexe(Sexe.M);
		etudiant.setCni(23);
		etudiant.setNum(06322121);
		etudiant.setEmail("ironMan@marvel.fr");
		etudiant.setClasse(new Classe(2, "français"));
		
		ArrayList matieres = new ArrayList();
		matieres.add("Maths");
		matieres.add("Français");
		matieres.add("SVT");
		matieres.add("Chimie");
		etudiant.setMatiereList(matieres);
		
		
		
		EtudiantUpdateDto returnedDto = converter.convertEntityToUpdateDto(etudiant);
	
		assertNotNull(returnedDto);
	}
	
	@Test
	public void testConvertingNullEtudiant_shouldReturnNullEtudiantnUpdateDto() {
		assertNull(converter.convertEntityToUpdateDto(null));
	}
	
	@Test
	public void testConvertingListEtudiantToListEtudiantUpdateDto () {
//		Examen examen1 = new Examen(1,LocalDate.parse("2019-03-23"),Type.CC,2d,new Matiere(1, "maths", null));
//		Examen examen2 = new Examen(2,LocalDate.parse("2019-03-23"),Type.CC,2d,new Matiere(2, "francais", null));
//		
		List<Etudiant> liste = new ArrayList<>();
		liste.add();
		liste.add();
		
		List<EtudiantUpdateDto> listeUpdateDto = converter.convertListEntityToUpdateDto(liste);
		
		assertNotNull(listeUpdateDto);
		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto());
		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto());
	}
	
	@Test
	public void testConvertingNullListEtudiant_shouldReturnNullListEtudiantUpdateDto() {
		assertNull(converter.convertListEntityToUpdateDto(null));
	}
	
	@Test
	public void testConvertingListEtudiantUpdateDtoToListEtudiant () {
		ExamenUpdateDto dto1 = new ExamenUpdateDto();
		ExamenUpdateDto dto2 = new ExamenUpdateDto();
		
		List<ExamenUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Etudiant> liste = converter.convertListUpdateDtoToEntity(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto2));
	}
	
	@Test
	public void testConvertingNullListEtudiantnUpdateDto_shouldReturnNullListEtudiant() {
		assertNull(converter.convertListUpdateDtoToEntity(null));
	}
	
	@Test
	public void testConvertingListEtudiantToListEtudiantCreateDto () {
		//hnlkn
	}
	
	@Test
	public void testConvertingNullListEtudiant_shouldReturnNullListEtudiantCreateDto() {
		assertNull(converter.convertListEntityToCreateDto(null));
	}
	
	@Test
	public void testConvertingListEtudiantCreateDtoToListEtudiant () {
		//jklkl
	}
	
	@Test
	public void testConvertingNullListEtudiantCreateDto_shouldReturnNullListEtudiant() {
		assertNull(converter.convertListCreateDtoToEntity(null));
	}
	
	
}
