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
import com.fr.adaming.dto.AbsenceCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class AbsenceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
	@Sql(statements = "insert into etudiant (id, cni, email, cp, num) values(1, 0 ,'email1@email.com', 69800, 0631313131)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from Etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingAbsenceWithController_shouldWork() throws Exception {

		EtudiantUpdateDto etu = new EtudiantUpdateDto();
		etu.setIdentifiant(1);
		etu.setIdentity(0);
		etu.setMail("email1@email.com");
		etu.setPostalCode(69800);
		etu.setPhone(0631313131);
		
		AbsenceCreateDto dtoRequest = new AbsenceCreateDto();
		String date = "2019-03-23";
		dtoRequest.setDateStart(date);
		dtoRequest.setDateEnd(date);
		dtoRequest.setJustif("justif");
		dtoRequest.setDescript("descript");
		dtoRequest.setEtudiant(etu);
		
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
		
		String responseAsString = mockMvc
			.perform(post("/absence").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		
		String respBodyString = mapper.writeValueAsString(dtoResponse.getBody());
		
		AbsenceCreateDto respAbsence = mapper.readValue(respBodyString, AbsenceCreateDto.class);
		
		// Expected
		
		AbsenceCreateDto expectedAbsence = new AbsenceCreateDto(date, date, "justif", "descript", etu);
				
		assertThat(dtoResponse).isNotNull();
		assertEquals(expectedAbsence, respAbsence);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
	}
	
	@Test
	@Sql(statements = "delete from Absence", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingBadAbsenceWithController_shouldNotWork() throws Exception {

		String responseAsString = mockMvc
			.perform(post("/absence").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'date_debut':}"))
			.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		assertThat(responseAsString).isEmpty();
	}

}
