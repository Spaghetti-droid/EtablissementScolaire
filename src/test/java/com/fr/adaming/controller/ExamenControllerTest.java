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
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.enumeration.Type;

@SpringBootTest
@AutoConfigureMockMvc
public class ExamenControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	
	
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingExamen_shouldWork() throws Exception {

		MatiereUpdateDto dtoMat = new MatiereUpdateDto();
		dtoMat.setIdMatiere(1);
		dtoMat.setNomMatiere("maths");
		
		ExamenCreateDto dtoRequest = new ExamenCreateDto();		
		dtoRequest.setCoefExamen(2);
		dtoRequest.setDateExamen("2019-04-26");
		dtoRequest.setMatiereExamen(dtoMat);
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
	public void testCreatingExamWithNullDate_shouldReturnEmpty() throws Exception {

		// Execution de la requete
		String responseAsString = mockMvc
			.perform(post("/examen").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'dateExamen':,'typeExamen':'CC','coefExamen':2,'matiereExamen':}"))
			.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Verifier si c'est un success
		assertThat(responseAsString).isEmpty();
		
	}
	
	
	
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingExamenWithNoType_shouldWork() throws Exception {

		MatiereUpdateDto dtoMat = new MatiereUpdateDto();
		dtoMat.setIdMatiere(1);
		dtoMat.setNomMatiere("maths");
		
		ExamenCreateDto dtoRequest = new ExamenCreateDto();		
		dtoRequest.setCoefExamen(2);
		dtoRequest.setDateExamen("2019-04-26");
		dtoRequest.setMatiereExamen(dtoMat);

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
	
	

}

