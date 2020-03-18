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
import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class AbsenceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	// METHODE CREATE | POST
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(1, 0, 'email1@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingAbsenceWithValidBody_shouldReturnStatusOk() throws Exception {

		// Préparer le dto
		EtudiantUpdateDto etu = new EtudiantUpdateDto();
		etu.setIdentifiant(1);
		etu.setIdentity(0);
		etu.setMail("email1@email.com");
		etu.setPostalCode(69800);
		etu.setPhone(0631313131);
		
		AbsenceCreateDto dtoRequest = new AbsenceCreateDto();
		dtoRequest.setDateStart("2019-03-23");
		dtoRequest.setDateEnd("2019-03-25");
		dtoRequest.setJustif("justification");
		dtoRequest.setDescript("description");
		dtoRequest.setEtudiant(etu);
		
		// Convertir le dto en Json
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
		
		// Execution de la requete
		String responseAsString = mockMvc
			.perform(post("/absence").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la reponse Json en dto
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		String responseBodyAsString = mapper.writeValueAsString(dtoResponse.getBody());
		AbsenceCreateDto responseBody = mapper.readValue(responseBodyAsString, AbsenceCreateDto.class);
		
		// Verifier si affirmations sont vraies
		AbsenceCreateDto expectedBody = new AbsenceCreateDto("2019-03-23", "2019-03-25", "justification", "description", etu);
				
		assertThat(dtoResponse).isNotNull();
		assertEquals(expectedBody, responseBody);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
	}
	
	@Test
	@Sql(statements = "delete from Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingAbsenceWithInvalidBody_shouldNotWork() throws Exception {

		String responseAsString = mockMvc
			.perform(post("/absence").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'date_debut':}"))
			.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		assertThat(responseAsString).isEmpty();
	}
	
	// METHODE DELETE BY ID | DELETE
	
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingAbsenceWithValidId_shouldReturnStatusOk() throws Exception {
				
		String responseAsString = mockMvc
				.perform(delete("/absence").param("id", "1"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
				
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("body", null);
				
	}
		
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingAbsenceWithInvalidId_shouldReturnBadStatus() throws Exception {
			
		String responseAsString = mockMvc
				.perform(delete("/absence").param("id", "2"))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
			
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
			
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("body", null);
			
	}


	// METHODE UPDATE | PUT
	
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingAbsenceWithValidId_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		// Préparer le dto
		AbsenceUpdateDto dtoRequest = new AbsenceUpdateDto();
		dtoRequest.setIdentifiant(1);
		dtoRequest.setDateStart("2019-04-23");
		dtoRequest.setDateEnd("2019-04-26");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
			
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/absence").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
							
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
			
	}
		
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingAbsenceWithInvalidId_shouldReturnStatusBad() throws UnsupportedEncodingException, Exception {

		// Préparer le dto
		AbsenceUpdateDto dtoRequest = new AbsenceUpdateDto();
		dtoRequest.setIdentifiant(2);
		dtoRequest.setDateStart("2019-04-23");
		dtoRequest.setDateEnd("2019-04-26");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
			
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/absence").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
							
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
			

	}
		
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingAbsenceWithDateNull_shouldReturnStatusBad() throws UnsupportedEncodingException, Exception {

		// Préparer le dto
		AbsenceUpdateDto dtoRequest = new AbsenceUpdateDto();
		dtoRequest.setIdentifiant(1);
		dtoRequest.setDateStart(null);
		dtoRequest.setDateEnd(null);

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
			
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/absence").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Verifier si c'est un succes
		assertThat(responseAsString).isEmpty();
			

	}
	
	// METHODE READ BY ID | GET
	
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingAbsenceWithValidId_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/absence/one?id=1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
		AbsenceUpdateDto responseBody = mapper.readValue(responseBodyAsString, AbsenceUpdateDto.class);
						
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		AbsenceUpdateDto expectedBody = new AbsenceUpdateDto(1,"2019-03-23","2019-03-26","justification","description", null);
		assertEquals(expectedBody, responseBody);

	}
			
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingAbsenceWithInvalidId_shouldReturnBadStatus() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
			.perform(get("/absence/one?id=2").contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
			
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
			
	}
			
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingAbsenceWithNegativeId_shouldNotWork() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/absence/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
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
			
	@Sql(statements = {"insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )","insert into absence (id, date_debut, date_fin, justification, description) values (2, '2019-05-23', '2019-05-26', 'justification', 'description' )"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence where id = 1 and id = 2", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingAllAbsence_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/absence/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
						
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(responseDto.getBody()).asList().hasSize(2);
			
	}
	
	@Sql (statements = "delete from absence", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Test
	public void testReadingAllAbsenceEmpty_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/absence/all").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
						
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
				
	}
}
