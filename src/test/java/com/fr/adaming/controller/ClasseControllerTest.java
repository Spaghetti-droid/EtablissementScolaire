package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class ClasseControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	// METHODE CREATE
	
	@Test
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingClasseWithController_shouldWork() throws Exception {

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
		
		// Verifier si c'est un success
		
	}
	
	@Test
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingBadClasseWithController_shouldNotWork() throws Exception {

		// Execution de la requete
		String responseAsString = mockMvc
			.perform(post("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'name':}"))
			.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Verifier si c'est un success
		assertThat(responseAsString).isEmpty();
		
	}
	
	// METHODE DELETE BY ID
	
	@Sql(statements = "insert into classe (id, nom) values(1,'5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingClasseByIdWithController_shouldWork() throws Exception {
		
		String responseAsString = mockMvc
				.perform(MockMvcRequestBuilders.delete("/classe").param("id", "1"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCESS");
		
	}
	

}
