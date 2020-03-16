package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.enumeration.Sexe;

@SpringBootTest
@AutoConfigureMockMvc
public class EtudiantControllerTests {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testCreatingEtudiantWithController_shouldWork() throws Exception {

		EtudiantCreateDto requestDto = new EtudiantCreateDto();
		requestDto.setSurname("Tony");
		requestDto.setName("Stark");
		requestDto.setAdress("Malibu Point 10880");
		requestDto.setPostalCode(90265);
		requestDto.setCity("Malibu");
		requestDto.setS(Sexe.M);
		requestDto.setIdentity(000000002000);
		requestDto.setPhone(0221546435);
		requestDto.setMail("ironMan@marvel.fr");
		requestDto.setNomClassroom("anglais");
		ArrayList matieres = new ArrayList();
		matieres.add("Maths");
		matieres.add("Français");
		matieres.add("SVT");
		matieres.add("Chimie");
		requestDto.setNomMatiere(matieres);

		String dtoAsJson = mapper.writeValueAsString(requestDto);

		String responseAsString = mockMvc
				.perform(post("/etudiant/create")
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(responseDto).isNotNull().hasFieldOrPropertyWithValue("message", "SUCCESS");

		assertThat(responseDto).hasFieldOrPropertyWithValue("body", responseDto.getBody());
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);

	}
	
	//Faire les test pour update et delete aussi ??
	
	

}
