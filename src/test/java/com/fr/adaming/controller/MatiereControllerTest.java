package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class MatiereControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingMatiereWithController_shouldWork() throws Exception {

		// Préparer le dto
		MatiereCreateDto dtoRequest = new MatiereCreateDto();
		dtoRequest.setNomMatiere("maths");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);

		// Execution de la requete
		
		String responseAsString = mockMvc
			.perform(post("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		
		String respBodyString = mapper.writeValueAsString(dtoResponse.getBody());
		
		EtudiantCreateDto respAbsence = mapper.readValue(respBodyString, EtudiantCreateDto.class);
		
				
		assertThat(dtoResponse).isNotNull();
		assertEquals(dtoRequest, respAbsence);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCES");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
	}
	
	@Test
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingBadClasseWithController_shouldNotWork() throws Exception {

		// Execution de la requete
		String responseAsString = mockMvc
			.perform(post("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'nomMatiere':}"))
			.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Verifier si c'est un success
		assertThat(responseAsString).isEmpty();
		
	}

}


