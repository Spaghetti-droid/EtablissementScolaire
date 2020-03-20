package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.AbsenceUpdateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class AbsenceControllerTest implements IControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	
		
	// METHODE CREATE
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(1, 0, 'email1@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testCreatingEntityWithValidBody_shouldReturnStatusOk() {

		try {
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
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_CREATE);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	@Sql(statements = "delete from Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturnBadStatus() {
		
		try {
			String responseAsString = mockMvc
				.perform(post("/absence").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'date_debut':}"))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			assertThat(responseAsString).isEmpty();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	
	// METHODE DELETE
	
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithValidId_shouldReturnStatusOk() {

		try {
			String responseAsString = mockMvc
					.perform(delete("/absence").param("id", "1"))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
					
			ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
					
			assertThat(dtoResponse).isNotNull();
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_DELEDETE_BY_ID);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("body", null);
					
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithInvalidId_shouldReturnBadStatus() {
		
		try {
			String responseAsString = mockMvc
					.perform(delete("/absence").param("id", "2"))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
				
			ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
				
			assertThat(dtoResponse).isNotNull();
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message",WebMappingConstant.FAIL_DELEDETE);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("body", null);
				
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithNegativeId_shouldReturnBadStatus() {

		try {
			String responseAsString = mockMvc
					.perform(delete("/absence").param("id", "-1"))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
				
			ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
				
			assertThat(dtoResponse).isNotNull();
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_DELEDETE);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("body", null);
				
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	
	// METHODE UPDATE

	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithValidId_shouldReturnStatusOk() {
		
		try {
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
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_UPDATE);
			assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
				
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturnBadStatus() {
		
		try {
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
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_UPDATE);
			assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
				
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingAbsenceWithDateNull_shouldReturnStatusBad() {

		try {
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
		
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// METHODE READ BY

	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithValidId_shouldReturnStatusOk() {

		try {
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
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_READ_BY_ID);
			AbsenceUpdateDto expectedBody = new AbsenceUpdateDto(1,"2019-03-23","2019-03-26","justification","description", null);
			assertEquals(expectedBody, responseBody);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithInvalidId_shouldReturnBadStatus() {

		try {
			String responseAsString = mockMvc
				.perform(get("/absence/one?id=2").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
				
			// Verifier si c'est un success
			assertThat(responseDto).isNotNull();
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_READ_BY_ID);
			assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
				
		
		} catch (JsonProcessingException e) {
		e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		}
				
	}
	
	@Sql(statements = "insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithNegativeId_shouldReturnBadStatus() {

		try {
			String responseAsString = mockMvc
					.perform(get("/absence/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
				
							
			// Verifier si c'est un success
			assertThat(responseDto).isNotNull();
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_READ_BY_ID);
			assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
				
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	// METHODE READ ALL

	@Sql(statements = {"insert into absence (id, date_debut, date_fin, justification, description) values (1, '2019-03-23', '2019-03-26', 'justification', 'description' )","insert into absence (id, date_debut, date_fin, justification, description) values (2, '2019-05-23', '2019-05-26', 'justification', 'description' )"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingAllEntity_shouldReturnStatusOk() {
		
		try {
			String responseAsString = mockMvc
					.perform(get("/absence/all").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
							
			// Verifier si c'est un success
			assertThat(responseDto).isNotNull();
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_READ_ALL);
			assertThat(responseDto.getBody()).asList().hasSize(2);
				
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
