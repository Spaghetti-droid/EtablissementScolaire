package com.fr.adaming.converter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.enumeration.Type;

/**
 * @author Jeanne-Marie
 * @author Lea
 * @author Lucie<br>
 * 
 * <b>Description : </b>
 * <p>Classe de test pour le converter de l'entite Examen, implemente IConverterTest</p>
 *
 */
@SpringBootTest
public class ExamenConverterTest implements IConverterTest {

	@Autowired
	private ExamenConverter converter;


	@Override
	@Test
	public void testConvertingCreateDtoToEntity() {
		ExamenCreateDto dto = new ExamenCreateDto();
		List<EtudiantUpdateDto> etudto = new ArrayList<>();
		dto.setCoefExamen(2);
		dto.setDateExamen("2019-03-23");
		dto.setTypeExamen(Type.CC);
		dto.setMatiereExamen(new MatiereUpdateDto(1,"maths", etudto));
		Examen returnedExamen = converter.convertCreateDtoToEntity(dto);
		
		List<Etudiant> etu = new ArrayList<>();
		
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("date", LocalDate.parse(dto.getDateExamen()));
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("type", dto.getTypeExamen());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("coef", dto.getCoefExamen());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("matiere", new Matiere(1, "maths", etu));
		assertNotNull(returnedExamen);
	}

	@Override
	@Test
	public void testConvertingNullCreateDto_shouldReturnNullEntity() {
			assertNull(converter.convertCreateDtoToEntity(null));
		}

	@Override
	@Test
	public void testConvertingUpdateDtoToEntity() {
		ExamenUpdateDto dto = new ExamenUpdateDto();
		List<EtudiantUpdateDto> etudto = new ArrayList<>();
		dto.setCoefExamen(2);
		dto.setDateExamen("2019-03-23");
		dto.setTypeExamen(Type.CC);
		dto.setIdExam(1);
		dto.setMatiereExamen(new MatiereUpdateDto(1,"maths", etudto));
		
		Examen returnedExamen = converter.convertUpdateDtoToEntity(dto);
		List<Etudiant> etu = new ArrayList<>();
		
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("date",LocalDate.parse(dto.getDateExamen()));
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("type", dto.getTypeExamen());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("coef", dto.getCoefExamen());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("id", dto.getIdExam());
		assertThat(returnedExamen).hasFieldOrPropertyWithValue("matiere", new Matiere(1, "maths", etu));
		assertNotNull(returnedExamen);
	}

	@Override
	@Test
	public void testConvertingNullUpdateDto_shouldReturnNullEntity() {
		assertNull(converter.convertUpdateDtoToEntity(null));
	}

	
	@Override
	@Test
	public void testConvertingEntityToCreateDto() {
		Examen examen = new Examen();
		List<Etudiant> etu = new ArrayList<>();
		examen.setCoef(2);
		examen.setDate(LocalDate.parse("2019-03-23"));
		examen.setMatiere(new Matiere(1, "maths", etu));
		examen.setType(Type.CC);
		
		ExamenCreateDto returnedDto = converter.convertEntityToCreateDto(examen);
		
		List<EtudiantUpdateDto> etudto = new ArrayList<>();
		
	
		assertThat(returnedDto).hasFieldOrPropertyWithValue("dateExamen", examen.getDate().toString());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("typeExamen", examen.getType());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("coefExamen", examen.getCoef());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("matiereExamen", new MatiereUpdateDto(1, "maths", etudto));
		assertNotNull(returnedDto);
	}

	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullCreateDto() {
		assertNull(converter.convertEntityToCreateDto(null));
	}

	@Override
	@Test
	public void testConvertingEntityToUpdateDto() {
		Examen examen = new Examen();
		List<Etudiant> etu = new ArrayList<>();
		examen.setCoef(2);
		examen.setDate(LocalDate.parse("2019-03-23"));
		examen.setMatiere(new Matiere(1, "maths", etu));
		examen.setType(Type.CC);
		examen.setId(1);
		
		ExamenUpdateDto returnedDto = converter.convertEntityToUpdateDto(examen);
		
		List<EtudiantUpdateDto> etudto = new ArrayList<>();
	
		assertThat(returnedDto).hasFieldOrPropertyWithValue("dateExamen", examen.getDate().toString());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("typeExamen", examen.getType());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("coefExamen", examen.getCoef());
		assertThat(returnedDto).hasFieldOrPropertyWithValue("idExam", 1);
		assertThat(returnedDto).hasFieldOrPropertyWithValue("matiereExamen", new MatiereUpdateDto(1, "maths", etudto));
		assertNotNull(returnedDto);
	}

	
	@Override
	@Test
	public void testConvertingNullEntity_shouldReturnNullUpdateDto() {
		assertNull(converter.convertEntityToUpdateDto(null));
	}

	
	@Override
	@Test
	public void testConvertingListEntityToCreateDto() {
		Examen examen1 = new Examen(1,LocalDate.parse("2019-03-23"),Type.CC,2d,new Matiere(1, "maths", null));
		Examen examen2 = new Examen(2,LocalDate.parse("2019-03-23"),Type.CC,2d,new Matiere(2, "francais", null));
		List<Examen> liste = new ArrayList<>();
		liste.add(examen1);
		liste.add(examen2);
		
		List<ExamenCreateDto> listeDto = converter.convertListEntityToCreateDto(liste);
		
		assertNotNull(listeDto);
		assertThat(listeDto).contains(converter.convertEntityToCreateDto(examen1));
		assertThat(listeDto).contains(converter.convertEntityToCreateDto(examen2));
	}

	@Override
	@Test
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList() {
		assertThat(converter.convertListEntityToCreateDto(null)).isEmpty();
	}

	@Override
	@Test
	public void testConvertingListCreateDtoToEntity() {
		ExamenCreateDto dto1 = new ExamenCreateDto();
		ExamenCreateDto dto2 = new ExamenCreateDto();
		List<ExamenCreateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Examen> liste = converter.convertListCreateDtoToEntity(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertCreateDtoToEntity(dto2));
	}

	@Override
	@Test
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListCreateDtoToEntity(null)).isEmpty();
	}

	@Override
	@Test
	public void testConvertingListEntityToUpdateDto() {
		Examen examen1 = new Examen(1,LocalDate.parse("2019-03-23"),Type.CC,2d,new Matiere(1, "maths", null));
		Examen examen2 = new Examen(2,LocalDate.parse("2019-03-23"),Type.CC,2d,new Matiere(2, "francais", null));
		List<Examen> liste = new ArrayList<>();
		liste.add(examen1);
		liste.add(examen2);
		
		List<ExamenUpdateDto> listeUpdateDto = converter.convertListEntityToUpdateDto(liste);
		
		assertNotNull(listeUpdateDto);
		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto(examen1));
		assertThat(listeUpdateDto).contains(converter.convertEntityToUpdateDto(examen2));
	}

	@Override
	@Test
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList() {
		assertThat(converter.convertListEntityToUpdateDto(null)).isEmpty();;
	}

	
	@Override
	@Test
	public void testConvertingListUpdateDtoToEntity() {
		ExamenUpdateDto dto1 = new ExamenUpdateDto();
		ExamenUpdateDto dto2 = new ExamenUpdateDto();
		List<ExamenUpdateDto> listeDto = new ArrayList<>();
		listeDto.add(dto1);
		listeDto.add(dto2);
		
		List<Examen> liste = converter.convertListUpdateDtoToEntity(listeDto);
		
		assertNotNull(liste);
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto1));
		assertThat(liste).contains(converter.convertUpdateDtoToEntity(dto2));
	}

	@Override
	@Test
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() {
		assertThat(converter.convertListUpdateDtoToEntity(null)).isEmpty();
	}
	
}
