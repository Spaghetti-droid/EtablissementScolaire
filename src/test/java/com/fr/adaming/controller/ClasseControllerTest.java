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

import com.fr.adaming.dto.ResponseDto;


@SpringBootTest
@AutoConfigureMockMvc
public class ClasseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	// METHODE CREATE | POST
	
	@Test
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingClasseWithValidBody_shouldReturnStatusOk() throws Exception {

		// Préparer le dto
		ClasseCreateDto dtoRequest = new ClasseCreateDto();
		dtoRequest.setName("5e1");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);

		// Execution de la requete
		String responseAsString = mockMvc
			.perform(post("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
		ClasseCreateDto responseBody = mapper.readValue(responseBodyAsString, ClasseCreateDto.class);
		
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		ClasseCreateDto expectedBody = new ClasseCreateDto("5e1");
		assertEquals(expectedBody, responseBody);
	}
	
	@Test
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingClasseWithInvalidBody_shouldNotWork() throws Exception {

		// Execution de la requete
		String responseAsString = mockMvc
			.perform(post("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'name':}"))
			.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Verifier si c'est un success
		assertThat(responseAsString).isEmpty();
		
	}
	
	// METHODE DELETE BY ID | DELETE
	
	@Sql(statements = "insert into classe (id, nom) values(1,'5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingClasseWithValidId_shouldReturnStatusOk() throws Exception {
		
		String responseAsString = mockMvc
				.perform(delete("/classe").param("id", "1"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCESS");
		
	}
	
	@Sql(statements = "insert into classe (id, nom) values(1,'5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingClasseWithInvalidId_shouldReturnBadStatus() throws Exception {
		
		String responseAsString = mockMvc
				.perform(delete("/classe").param("id", "2"))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "FAIL");
		
	}
	
	// METHODE UPDATE | PUT
	
	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingClasseWithValidId_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		// Préparer le dto
		ClasseUpdateDto dtoRequest = new ClasseUpdateDto();
		dtoRequest.setId(1);
		dtoRequest.setName("4e3");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
		
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
						
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		

	}
	
	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingClasseWithInvalidId_shouldReturnStatusBad() throws UnsupportedEncodingException, Exception {

		// Préparer le dto
		ClasseUpdateDto dtoRequest = new ClasseUpdateDto();
		dtoRequest.setId(2);
		dtoRequest.setName("4e3");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
		
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
						
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
		

	}
	
	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingClasseWithNameNull_shouldReturnStatusBad() throws UnsupportedEncodingException, Exception {

		// Préparer le dto
		ClasseUpdateDto dtoRequest = new ClasseUpdateDto();
		dtoRequest.setId(1);
		dtoRequest.setName(null);

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
		
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Verifier si c'est un succes
		assertThat(responseAsString).isEmpty();
		

	}
	
	
	// METHODE READ BY ID | GET
	
	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingClasseWithValidId_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/classe/one?id=1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
		ClasseUpdateDto responseBody = mapper.readValue(responseBodyAsString, ClasseUpdateDto.class);
				
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		ClasseUpdateDto expectedBody = new ClasseUpdateDto (1,"5e1");
		assertEquals(expectedBody, responseBody);

	}
	
	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingClasseWithInvalidId_shouldReturnBadStatus() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/classe/one?id=2").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
						
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(responseDto).hasFieldOrPropertyWithValue("body", null);
	
	}
	
	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingClasseWithNegativeId_shouldNotWork() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/classe/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
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
	
	@Sql(statements = {"insert into classe (id, nom) values (1, '5e1')", "insert into classe (id, nom) values (2, '4e2')"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from classe where id=1","delete from classe where id=2"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadingAllClasse_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/classe/all").contentType(MediaType.APPLICATION_JSON_VALUE))
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
	public void testReadingAllClasseEmpty_shouldReturnStatusOk() throws UnsupportedEncodingException, Exception {

		String responseAsString = mockMvc
				.perform(get("/classe/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
				
		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		
	}
}
