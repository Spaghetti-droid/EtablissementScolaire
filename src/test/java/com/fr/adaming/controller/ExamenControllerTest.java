package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.enumeration.Type;

/**
 * Classe ExamenControllerTest, implementant l'interface IControllerTest et visant à tester les methodes de la classe ExamenController
 * @author Gregoire
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ExamenControllerTest implements IControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Methode visant à tester la methode Create de la classe ExamenController, dans le cas particulier où la date est nul
	 * @throws Exception
	 */
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

	/**
	 * Methode visant à tester la methode Create de la classe ExamenController, dans le cas particulier où le type est nul
	 * @throws Exception
	 */
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testCreatingExamenWithNoType_shouldWork() throws Exception {

		try {
			MatiereUpdateDto dtoMat = new MatiereUpdateDto();
			List<EtudiantUpdateDto> dtoEtu = new ArrayList<>();
			dtoMat.setIdMatiere(1);
			dtoMat.setNomMatiere("maths");
			dtoMat.setListeEtudiant(dtoEtu);

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

			// Verifier si c'est un success
			assertThat(dtoResponse).isNotNull();
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_CREATE);
			assertEquals(dtoRequest, respExam);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// date null

	/**
	 * Methode visant à tester la methode Update de la classe ExamenController, dans le cas particulier où la date est nulle
	 * @throws Exception
	 */
	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingExamenWithNullDate_shouldReturnEmpty() throws Exception {

		try {
			MatiereUpdateDto dtoMat = new MatiereUpdateDto();
			dtoMat.setIdMatiere(1);
			dtoMat.setNomMatiere("maths");

			ExamenUpdateDto dtoRequest = new ExamenUpdateDto();
			dtoRequest.setIdExam(1);
			dtoRequest.setCoefExamen(3);
			dtoRequest.setDateExamen(null);
			dtoRequest.setMatiereExamen(dtoMat);
			dtoRequest.setTypeExamen(Type.CC);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/examen").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			assertThat(responseAsString).isEmpty();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testCreatingEntityWithValidBody_shouldReturnStatusOk() {
		MatiereUpdateDto dtoMat = new MatiereUpdateDto();
		List<EtudiantUpdateDto> dtoEtuList = new ArrayList<>();
		dtoMat.setIdMatiere(1);
		dtoMat.setNomMatiere("bob");
		dtoMat.setListeEtudiant(dtoEtuList);

		ExamenCreateDto dtoRequest = new ExamenCreateDto();
		dtoRequest.setCoefExamen(2);
		dtoRequest.setDateExamen("2019-04-26");
		dtoRequest.setMatiereExamen(dtoMat);
		dtoRequest.setTypeExamen(Type.CC);
		try {
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

			// Verifier si c'est un success
			assertThat(dtoResponse).isNotNull();
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_CREATE);
			assertEquals(dtoRequest, respExam);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturnBadStatus() {

		// Response
		try {
			String responseAsString = mockMvc.perform(post("/examen").param("id", "1"))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			ExamenUpdateDto respExam = mapper.readValue(respBodyString, ExamenUpdateDto.class);

			// Assertions

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_CREATE);
			assertNull(respExam);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithValidId_shouldReturnStatusOk() {

		try {
			String responseAsString = mockMvc.perform(delete("/examen").param("id", "1")).andExpect(status().isOk())
					.andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			ExamenUpdateDto respExam = mapper.readValue(respBodyString, ExamenUpdateDto.class);

			// Assertions

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_DELEDETE_BY_ID);
			assertNull(respExam);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", false);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	@Override
	public void testDeletingEntityWithInvalidId_shouldReturnBadStatus() {
		// Response
		try {
			String responseAsString = mockMvc.perform(delete("/examen").param("id", "1"))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			ExamenUpdateDto respExam = mapper.readValue(respBodyString, ExamenUpdateDto.class);

			// Assertions

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_DELEDETE);
			assertNull(respExam);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithNegativeId_shouldReturnBadStatus() {
		// Response
		try {
			String responseAsString = mockMvc
					.perform(delete("/examen").param("id", "-1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			ExamenUpdateDto respExam = mapper.readValue(respBodyString, ExamenUpdateDto.class);

			// Assertions

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_DELEDETE);
			assertNull(respExam);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithValidId_shouldReturnStatusOk() {
		try {

			MatiereUpdateDto dtoMat = new MatiereUpdateDto();
			dtoMat.setIdMatiere(1);
			dtoMat.setNomMatiere("maths");

			ExamenUpdateDto dtoRequest = new ExamenUpdateDto();
			dtoRequest.setIdExam(1);
			dtoRequest.setCoefExamen(3);
			dtoRequest.setDateExamen("2019-04-27");
			dtoRequest.setMatiereExamen(dtoMat);
			dtoRequest.setTypeExamen(Type.CC);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/examen").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);

			// Verifier si c'est un success
			assertThat(dtoResponse).isNotNull();
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_UPDATE);
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
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturnBadStatus() {

		try {

			MatiereUpdateDto dtoMat = new MatiereUpdateDto();
			dtoMat.setIdMatiere(1);
			dtoMat.setNomMatiere("maths");

			ExamenUpdateDto dtoRequest = new ExamenUpdateDto();
			dtoRequest.setIdExam(5);
			dtoRequest.setCoefExamen(3);
			dtoRequest.setDateExamen("2019-04-27");
			dtoRequest.setMatiereExamen(dtoMat);
			dtoRequest.setTypeExamen(Type.CC);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/examen").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);

			// Verifier si c'est un success
			assertThat(dtoResponse).isNotNull();
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_UPDATE);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithValidId_shouldReturnStatusOk() {
		// Response
		try {
			String responseAsString = mockMvc
					.perform(get("/examen/one?id=1").contentType(MediaType.APPLICATION_JSON_VALUE))
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
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_READ_BY_ID);
			assertEquals(expectedExam, respExam);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", false);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	@Override
	public void testReadingEntityWithInvalidId_shouldReturnBadStatus() {
		// Response
		try {
			String responseAsString = mockMvc
					.perform(get("/examen/one?id=15").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			ExamenUpdateDto respExam = mapper.readValue(respBodyString, ExamenUpdateDto.class);

			// Assertions

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_READ_BY_ID);
			assertNull(respExam);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Sql(statements = { "insert into Matiere (id, nom) values (1, 'bob')",
			"insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1)" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithNegativeId_shouldReturnBadStatus() {
		// Response
		try {
			String responseAsString = mockMvc
					.perform(get("/examen/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			ExamenUpdateDto respExam = mapper.readValue(respBodyString, ExamenUpdateDto.class);

			// Assertions

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_READ_BY_ID);
			assertNull(respExam);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen (id, coef, date, type, matiere_id) values (1, 2, '2000-01-01', 1, 1),(2,2, '2000-01-01', 1, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingAllEntity_shouldReturnStatusOk() {
		try {
			String responseAsString = mockMvc.perform(get("/examen/all").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			List<ExamenUpdateDto> respObjList = mapper.readValue(respBodyString, ArrayList.class);
			List<ExamenUpdateDto> respExamList = new ArrayList<ExamenUpdateDto>();

			for (Object e : respObjList) {

				String eAsString = mapper.writeValueAsString(e);
				ExamenUpdateDto exam = mapper.readValue(eAsString, ExamenUpdateDto.class);
				respExamList.add(exam);

			}
			MatiereUpdateDto mat = new MatiereUpdateDto(1, "bob", new ArrayList<EtudiantUpdateDto>());

			List<ExamenUpdateDto> expectedList = new ArrayList<ExamenUpdateDto>();

			ExamenUpdateDto ex1 = new ExamenUpdateDto(1, "2000-01-01", Type.DS, 2d, mat);
			ExamenUpdateDto ex2 = new ExamenUpdateDto(2, "2000-01-01", Type.DS, 2d, mat);

			expectedList.add(ex1);
			expectedList.add(ex2);

			// Assertions

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_READ_ALL);
			assertEquals(expectedList, respExamList);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", false);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
