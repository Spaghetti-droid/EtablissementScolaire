package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.enumeration.Type;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamenControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingExamenWithController_shouldWork() throws Exception {

		ExamenCreateDto dtoRequest = new ExamenCreateDto();
		dtoRequest.setCoefExamen(2);
		dtoRequest.setDateExamen(LocalDate.parse("2019-04-26"));
		dtoRequest.setMatiereExamen("maths");
		dtoRequest.setTypeExamen(Type.CC);

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);

		// Execution de la requete
		String responseAsString = mockMvc
			.perform(post("/examen").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		
		// Verifier si c'est un success
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCES");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("body", dtoResponse.getBody());
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
	}
	
	@Test
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingBadClasseWithController_shouldNotWork() throws Exception {

		// Execution de la requete
		String responseAsString = mockMvc
			.perform(post("/examen").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'dateExamen':,'typeExamen':'CC','coefExamen':2,'matiereExamen':}"))
			.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Verifier si c'est un success
		assertThat(responseAsString).isEmpty();
		
	}

}

