package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.enumeration.Sexe;

@SpringBootTest
@AutoConfigureMockMvc
public class EtudiantControllerTests {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	// Create

	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'anglais'), (2, 'espagnol')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEtudiant_shouldWork() throws Exception {

		ClasseUpdateDto dtoclass = new ClasseUpdateDto();
		dtoclass.setId(1);
		dtoclass.setName("Terminal");

		List<MatiereUpdateDto> dtomatlist = new ArrayList<MatiereUpdateDto>();
		dtomatlist.add(new MatiereUpdateDto(1, "anglais"));
		dtomatlist.add(new MatiereUpdateDto(2, "espagnol"));

		EtudiantCreateDto requestDto = new EtudiantCreateDto();

		requestDto.setSurname("Tony");
		requestDto.setName("Stark");
		requestDto.setAdress("Malibu Point 10880");
		requestDto.setPostalCode(90265);
		requestDto.setCity("Malibu");
		requestDto.setS(Sexe.M);
		requestDto.setIdentity(45);
		requestDto.setPhone(0221546435);
		requestDto.setMail("ironMan@marvel.fr");
		requestDto.setClasse(dtoclass);
		requestDto.setMatiere(dtomatlist);

		String dtoAsJson = mapper.writeValueAsString(requestDto);

		String responseAsString = mockMvc
				.perform(post("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		String respBodyString = mapper.writeValueAsString(responseDto.getBody());

		EtudiantCreateDto respAbsence = mapper.readValue(respBodyString, EtudiantCreateDto.class);

		assertThat(responseDto).isNotNull();
		assertEquals(requestDto, respAbsence);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);

	}
	//Postif
	@Test
	public void testCreatingEtudiantWithNegativeId_shouldNotWork() {

	}
	
	@Test
	public void testCreatingEtudiantWithNegativeCni_shouldNotWork() {

	}
	
	//Null
	@Test
	public void testCreatingEtudiantWithNullEmail_shouldNotWork() {

	}

	

	// Read by id

	@Test
	public void testReadByExistingId_ShouldReturnTrue() {

	}

	@Test
	public void testReadByNotExistingId_ShouldReturnFalse() {

	}

	@Test
	public void testReadByNegativeId_shouldReturnFalse() {

	}

	// Read all

	@Test
	public void testReadAll_ShouldReturnListEtudiant() {

	}

	// Update

	@Test
	public void testUpdateEtudiant_shouldWork() {

	}
	
	
	//Unicité
	@Test
	public void testUpdateEtudiantWithExistingId_shouldReturnFalse() {

	}
	@Test
	public void testUpdateEtudiantWithExistingMail_shouldReturnFalse() {

	}
	@Test
	public void testUpdateEtudiantWithExistingCni_shouldReturnFalse() {

	}
	
	//Positif
	@Test
	public void testUpdateEtudiantWithNegativeId_shouldReturnFalse() {

	}
	@Test
	public void testUpdateEtudiantWithNegativeCni_shouldReturnFalse() {

	}
	
	//Null
	@Test
	public void testUpdateEtudiantWithNullEmail_shouldReturnFalse() {
		
	}

}
