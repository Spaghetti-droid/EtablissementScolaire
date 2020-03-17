package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Sexe;

@SpringBootTest
public class EtudiantConverterTests {

	@Autowired
	private EtudiantConverter converter;

	@Test
	public void testConvertingEtudiantCreateDtoToEtudiant() {
		// Préparer les inputs
		EtudiantCreateDto dto = new EtudiantCreateDto();

		dto.setName("Stark");
		dto.setSurname("Tony");
		dto.setAdress("Malibu Point 10880");
		dto.setPostalCode(90265);
		dto.setCity("Malibu");
		dto.setS(Sexe.M);
		dto.setIdentity(1);
		dto.setPhone(0221546435);
		dto.setMail("ironMan@marvel.fr");

		dto.setClasse(new ClasseUpdateDto(1, "Terminal"));

		List<MatiereUpdateDto> matieres = new ArrayList<MatiereUpdateDto>();
		matieres.add(new MatiereUpdateDto(1, "Math", null));
		matieres.add(new MatiereUpdateDto(2, "Anglais", null));
		dto.setMatiere(matieres);

		// Invoquer l'appli
		Etudiant returnEtudiant = converter.convertCreateDtoToEntity(dto);

		// Vérifier le résultat
		// User doit être différent de null
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("nom", dto.getName());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("prenom", dto.getSurname());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("adresse", dto.getAdress());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("cp", dto.getPostalCode());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("ville", dto.getCity());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("sexe", dto.getS());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("cni", dto.getIdentity());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("num", dto.getPhone());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("email", dto.getMail());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("classe", new Classe(1, "Terminal"));

		List<Matiere> preparedList = new ArrayList<Matiere>();
		preparedList.add(new Matiere(1, "Math", null));
		preparedList.add(new Matiere(2, "Anglais", null));

		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("matiereList", preparedList);

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
		dto.setIdentity(52);
		dto.setPhone(0221546435);
		dto.setMail("ironMan@marvel.fr");

		dto.setClasse(new ClasseUpdateDto(1, "Terminal"));

		List<MatiereUpdateDto> matieres = new ArrayList<MatiereUpdateDto>();
		matieres.add(new MatiereUpdateDto(1, "Math", null));
		matieres.add(new MatiereUpdateDto(2, "Anglais", null));
		dto.setMatiere(matieres);

		// Invoquer l'appli
		Etudiant returnEtudiant = converter.convertUpdateDtoToEntity(dto);

		// Vérifier le résultat
		// User doit être différent de null
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("nom", dto.getName());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("prenom", dto.getSurname());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("adresse", dto.getAdress());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("cp", dto.getPostalCode());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("ville", dto.getCity());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("sexe", dto.getS());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("cni", dto.getIdentity());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("num", dto.getPhone());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("email", dto.getMail());
		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("classe", new Classe(1, "Terminal"));

		List<Matiere> preparedList = new ArrayList<Matiere>();
		preparedList.add(new Matiere(1, "Math", null));
		preparedList.add(new Matiere(2, "Anglais", null));

		assertThat(returnEtudiant).hasFieldOrPropertyWithValue("matiereList", preparedList);

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
		etudiant.setClasse(new Classe(2, "Terminal"));

		List<Matiere> matieres = new ArrayList<Matiere>();
		matieres.add(new Matiere(1, "Math", null));
		matieres.add(new Matiere(2, "Anglais", null));
		etudiant.setMatiereList(matieres);

		EtudiantUpdateDto returnedDto = converter.convertEntityToUpdateDto(etudiant);

		assertThat(returnedDto).hasFieldOrPropertyWithValue("name", etudiant.getNom());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("surname", etudiant.getPrenom());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("adress", etudiant.getAdresse());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("postalCode", etudiant.getCp());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("city", etudiant.getVille());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("s", etudiant.getSexe());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("identity", etudiant.getCni());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("phone", etudiant.getNum());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("mail", etudiant.getEmail());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("classe", new ClasseUpdateDto(2, "Terminal"));
		
		
		List<MatiereUpdateDto> preparedList = new ArrayList<MatiereUpdateDto>();
		preparedList.add(new MatiereUpdateDto(1, "Math", null));
		preparedList.add(new MatiereUpdateDto(2, "Anglais", null));
		assertThat(returnedDto).hasFieldOrPropertyWithValue("matiere", preparedList);

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
		etudiant.setClasse(new Classe(2, "Terminal"));

		List<Matiere> matieres = new ArrayList<Matiere>();
		matieres.add(new Matiere(1, "Math", null));
		matieres.add(new Matiere(2, "Anglais", null));
		etudiant.setMatiereList(matieres);

		EtudiantUpdateDto returnedDto = converter.convertEntityToUpdateDto(etudiant);

		assertThat(returnedDto).hasFieldOrPropertyWithValue("name", etudiant.getNom());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("surname", etudiant.getPrenom());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("adress", etudiant.getAdresse());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("postalCode", etudiant.getCp());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("city", etudiant.getVille());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("s", etudiant.getSexe());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("identity", etudiant.getCni());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("phone", etudiant.getNum());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("mail", etudiant.getEmail());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("classe", new ClasseUpdateDto(2, "Terminal"));
		
		
		List<MatiereUpdateDto> preparedList = new ArrayList<MatiereUpdateDto>();
		preparedList.add(new MatiereUpdateDto(1, "Math", null));
		preparedList.add(new MatiereUpdateDto(2, "Anglais", null));
		assertThat(returnedDto).hasFieldOrPropertyWithValue("matiere", preparedList);

	}

	@Test
	public void testConvertingNullEtudiant_shouldReturnNullEtudiantnUpdateDto() {
		assertNull(converter.convertEntityToUpdateDto(null));
	}

	@Test
	public void testConvertingListEtudiantToListEtudiantUpdateDto() {

		Etudiant etu1 = new Etudiant(1, "Stark", "Tony", 25, "ironMan@marvel.fr");
		Etudiant etu2 = new Etudiant(2, "Rodgers", "Steve", 42, "steveRodgers@marvel.fr");

		List<Etudiant> liste = new ArrayList<>();
		liste.add(etu1);
		liste.add(etu2);

		List<EtudiantUpdateDto> listeUpdateDto = converter.convertListEntityToUpdateDto(liste);

		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto(etu1));
		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto(etu2));
	}

	@Test
	public void testConvertingNullListEtudiant_shouldReturnNullListEtudiantUpdateDto() {
		assertNull(converter.convertListEntityToUpdateDto(null));
	}

	@Test
	public void testConvertingListEtudiantUpdateDtoToListEtudiant() {
		EtudiantUpdateDto dto1 = new EtudiantUpdateDto();
		EtudiantUpdateDto dto2 = new EtudiantUpdateDto();

		List<EtudiantUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		List<Etudiant> liste = converter.convertListUpdateDtoToEntity(listeDto);

		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto2));
	}

	@Test
	public void testConvertingNullListEtudiantnUpdateDto_shouldReturnNullListEtudiant() {
		assertNull(converter.convertListUpdateDtoToEntity(null));
	}

	@Test
	public void testConvertingListEtudiantToListEtudiantCreateDto() {
		Etudiant etu1 = new Etudiant(1, "Stark", "Tony", 25, "ironMan@marvel.fr");
		Etudiant etu2 = new Etudiant(2, "Rodgers", "Steve", 42, "steveRodgers@marvel.fr");

		List<Etudiant> liste = new ArrayList<>();
		liste.add(etu1);
		liste.add(etu2);

		List<EtudiantCreateDto> listeUpdateDto = converter.convertListEntityToCreateDto(liste);

		assertThat(listeUpdateDto).contains(converter.convertEntityToCreateDto(etu1));
		assertThat(listeUpdateDto).contains(converter.convertEntityToCreateDto(etu2));
	}

	@Test
	public void testConvertingNullListEtudiant_shouldReturnNullListEtudiantCreateDto() {
		assertNull(converter.convertListEntityToCreateDto(null));
	}

	@Test
	public void testConvertingListEtudiantCreateDtoToListEtudiant() {
		EtudiantCreateDto dto1 = new EtudiantCreateDto();
		EtudiantCreateDto dto2 = new EtudiantCreateDto();

		List<EtudiantCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);

		List<Etudiant> liste = converter.convertListCreateDtoToEntity(listeDto);

		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto2));
	}

	@Test
	public void testConvertingNullListEtudiantCreateDto_shouldReturnNullListEtudiant() {
		assertNull(converter.convertListCreateDtoToEntity(null));
	}

}
