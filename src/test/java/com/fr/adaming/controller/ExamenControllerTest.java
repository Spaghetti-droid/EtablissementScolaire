package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import java.time.LocalDate;
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
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
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

		String respBodyString = mapper.writeValueAsString(dtoResponse.getBody());

		ExamenCreateDto respExam = mapper.readValue(respBodyString, ExamenCreateDto.class);

		// Expected

		ExamenCreateDto expectedExam = new ExamenCreateDto("2019-04-26", Type.CC, 2, dtoMat);

		// Verifier si c'est un success
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCES");
		assertEquals(expectedExam, respExam);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
	}

	@Test
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingExamWithNullDate_shouldReturnEmpty() throws Exception {

		// Execution de la requete
		String responseAsString = mockMvc
				.perform(post("/examen").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("{'dateExamen':,'typeExamen':'CC','coefExamen':2,'matiereExamen':}"))
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

		String respBodyString = mapper.writeValueAsString(dtoResponse.getBody());

		ExamenCreateDto respExam = mapper.readValue(respBodyString, ExamenCreateDto.class);

		// Expected

		ExamenCreateDto expectedExam = new ExamenCreateDto("2019-04-26", 2, dtoMat);

		// Verifier si c'est un success
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCES");
		assertEquals(expectedExam, respExam);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);

	}

	// *** readById ***

	// existe

	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByValidId_shouldReturnSuccessAndExam() throws UnsupportedEncodingException, Exception {

		// Response

		String responseAsString = mockMvc.perform(get("/examen/one?id=1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

		String respBodyString = mapper.writeValueAsString(respDto.getBody());

		ExamenUpdateDto respExam = mapper.readValue(respBodyString, ExamenUpdateDto.class);

		// Expected

		MatiereUpdateDto expectedMat = new MatiereUpdateDto();
		expectedMat.setIdMatiere(1);
		expectedMat.setNomMatiere("bob");
		List<EtudiantUpdateDto> listeVide = new ArrayList<EtudiantUpdateDto>();
		expectedMat.setListeEtudiant(listeVide);

		ExamenUpdateDto expectedExam = new ExamenUpdateDto(1, "2000-01-01", Type.DS, 2, expectedMat);

		// Assertions

		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", "SUCCES");
		assertEquals(expectedExam, respExam);
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", false);

	}

	// n'existe pas

	@Test
	public void testReadByBadId_shouldReturnFail() throws UnsupportedEncodingException, Exception {

		// Response

		String responseAsString = mockMvc
				.perform(get("/examen/one?id=15").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

		String respBodyString = mapper.writeValueAsString(respDto.getBody());

		ExamenUpdateDto respExam = mapper.readValue(respBodyString, ExamenUpdateDto.class);

		// Assertions

		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", "FAIL");
		assertNull(respExam);
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);

	}

	// non-positif

	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadByNegativeId_shouldReturnFail() throws UnsupportedEncodingException, Exception {

		// Response

		String responseAsString = mockMvc
				.perform(get("/examen/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

		String respBodyString = mapper.writeValueAsString(respDto.getBody());

		ExamenUpdateDto respExam = mapper.readValue(respBodyString, ExamenUpdateDto.class);

		// Assertions

		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", "FAIL");
		assertNull(respExam);
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);

	}

	// *** readAll ***

	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)",
			"insert into examen (id, coef, date, type, matiere_id) values (2, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadAll_shouldReturnList() throws UnsupportedEncodingException, Exception {

		// Response

		String responseAsString = mockMvc
				.perform(get("/examen/all").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

		String respBodyString = mapper.writeValueAsString(respDto.getBody());

		List<ExamenUpdateDto> respObjList = mapper.readValue(respBodyString, ArrayList.class);
		List<ExamenUpdateDto> respExamList = new ArrayList<ExamenUpdateDto>();
		
		for(Object e : respObjList) {
			
			String eAsString = mapper.writeValueAsString(e);
			ExamenUpdateDto exam = mapper.readValue(eAsString, ExamenUpdateDto.class);
			respExamList.add(exam);
						
		}
		
		// Expected
		
		MatiereUpdateDto mat = new MatiereUpdateDto(1, "bob", new ArrayList<EtudiantUpdateDto>());
		
		List<ExamenUpdateDto> expectedList = new ArrayList<ExamenUpdateDto>();
		
		ExamenUpdateDto ex1 = new ExamenUpdateDto(1, "2000-01-01", Type.DS, 2d, mat);
		ExamenUpdateDto ex2 = new ExamenUpdateDto(2, "2000-01-01", Type.DS, 2d, mat);
		
		expectedList.add(ex1);
		expectedList.add(ex2);
		

		// Assertions

		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertEquals(expectedList,respExamList);
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", false);

	}

	// *** deleteById ***

	// *** update ***

	// valide
	// id n'existe pas
	// date null

}
