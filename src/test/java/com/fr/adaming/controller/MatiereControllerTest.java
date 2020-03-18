package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
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
import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class MatiereControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	// METHODE CREATE | POST
	
	@Test
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingMatiereWithValidBody_shouldReturnStatusOK() throws Exception {

		// Préparer le dto
		MatiereCreateDto dtoRequest = new MatiereCreateDto();
		dtoRequest.setNomMatiere("maths");
		List<EtudiantUpdateDto> listeEtudiant = new ArrayList<>();
		listeEtudiant.add(new EtudiantUpdateDto());
		dtoRequest.setListeEtudiant(listeEtudiant);

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);

		// Execution de la requete
		
		String responseAsString = mockMvc
			.perform(post("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
		MatiereCreateDto responseBody = mapper.readValue(responseBodyAsString, MatiereCreateDto.class);
		
		// Verifier si affirmations sont vraies	
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		MatiereCreateDto expectedBody = new MatiereCreateDto("maths", listeEtudiant);
		assertEquals(responseBody, expectedBody);
		
	}
	
	@Test
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingMatiereWithInvalidBody_shouldNotWork() throws Exception {

		// Execution de la requete
		String responseAsString = mockMvc
			.perform(post("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'nomMatiere':}"))
			.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Verifier si c'est un success
		assertThat(responseAsString).isEmpty();
		
	}
	
	// METHODE DELETE BY ID | DELETE
	
	@Sql(statements = "insert into matiere (id, nom) values (1,'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingMatiereWithValidId_shouldReturnStatusOk() throws Exception {
			
		String responseAsString = mockMvc
				.perform(delete("/matiere").param("id", "1"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
			
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCESS");
			
	}
	
	@Sql(statements = "insert into matiere (id, nom) values (1,'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingMatiereWithInvalidId_shouldReturnBadStatus() throws Exception {
		
		String responseAsString = mockMvc
				.perform(delete("/matiere").param("id", "2"))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "FAIL");
		
	}
		
	
	// METHODE UPDATE | PUT
	
	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingMatiereWithValidId_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		// Préparer le dto
		MatiereUpdateDto dtoRequest = new MatiereUpdateDto();
		dtoRequest.setIdMatiere(1);
		dtoRequest.setNomMatiere("geographie");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
			
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
							
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
			
	}
		
	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingMatiereWithInvalidId_shouldReturnStatusBad() throws UnsupportedEncodingException, Exception {

		// Préparer le dto
		MatiereUpdateDto dtoRequest = new MatiereUpdateDto();
		dtoRequest.setIdMatiere(2);
		dtoRequest.setNomMatiere("geographie");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
			
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
							
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
			

	}
		
	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingMatiereWithNameNull_shouldReturnStatusBad() throws UnsupportedEncodingException, Exception {

		// Préparer le dto
		MatiereUpdateDto dtoRequest = new MatiereUpdateDto();
		dtoRequest.setIdMatiere(1);
		dtoRequest.setNomMatiere(null);

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
			
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Verifier si c'est un succes
		assertThat(responseAsString).isEmpty();
			

	}
	
	// METHODE READ BY ID | GET
	
	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingMatiereWithValidId_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/matiere/one?id=1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
		MatiereUpdateDto responseBody = mapper.readValue(responseBodyAsString, MatiereUpdateDto.class);
					
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		List<EtudiantUpdateDto> listeEtudiant = new ArrayList<EtudiantUpdateDto>();
		MatiereUpdateDto expectedBody = new MatiereUpdateDto(1, "maths", listeEtudiant);
		assertEquals(expectedBody, responseBody);

	}
		
	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingMatiereWithInvalidId_shouldReturnBadStatus() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/matiere/one?id=2").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		
					
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
		
	}
		
	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingMatiereWithNegativeId_shouldNotWork() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/matiere/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		
					
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
		
	}
		
	// METHODE READ ALL | GET
		
	@Sql(statements = {"insert into matiere (id, nom) values (1, 'maths')", "insert into matiere (id, nom) values (2, 'geographie')"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from matiere where id=1","delete from matiere where id=2"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingAllMatiere_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/matiere/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
					
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(responseDto.getBody()).asList().hasSize(2);
		
	}
			
	@Test
	public void testReadingAllMatiereEmpty_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/matiere/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
					
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
			
	}
		
}


