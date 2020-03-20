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
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class MatiereControllerTest implements IControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();
	
		
	
	// METHODE CREATE | POST
	
	@Test
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithValidBody_shouldReturnStatusOk() {
		
		// Préparer le dto
		MatiereCreateDto dtoRequest = new MatiereCreateDto();
		dtoRequest.setNomMatiere("maths");
		List<EtudiantUpdateDto> listeEtudiant = new ArrayList<>();
		dtoRequest.setListeEtudiant(listeEtudiant);
		
		try {
			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			
			String responseAsString = mockMvc
				.perform(post("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
			String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
			MatiereCreateDto responseBody = mapper.readValue(responseBodyAsString, MatiereCreateDto.class);
			
			// Verifier si affirmations sont vraies	
			assertThat(responseDto).isNotNull();
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_CREATE);
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
			MatiereCreateDto expectedBody = new MatiereCreateDto("maths", listeEtudiant);
			assertEquals(responseBody, expectedBody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	@Test
	@Sql(statements = "delete from matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithInvalidBody_shouldReturnBadStatus() {
		
		try {
			// Execution de la requete
			String responseAsString = mockMvc
				.perform(post("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'nomMatiere':}"))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			// Verifier si c'est un success
			assertThat(responseAsString).isEmpty();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	// METHODE DELETE BY ID | DELETE
	
	@Sql(statements = "insert into matiere (id, nom) values (1,'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithValidId_shouldReturnStatusOk() {
		
		try {
		String responseAsString = mockMvc
			.perform(delete("/matiere").param("id", "1"))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
				
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_DELEDETE_BY_ID);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	
	@Sql(statements = "insert into matiere (id, nom) values (1,'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithInvalidId_shouldReturnBadStatus() {
		
		try {
			String responseAsString = mockMvc
				.perform(delete("/matiere").param("id", "2"))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_DELEDETE);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}
	
	@Sql(statements = "insert into matiere (id, nom) values (1,'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithNegativeId_shouldReturnBadStatus() {
		
		try {
			String responseAsString = mockMvc
				.perform(delete("/matiere").param("id", "-1"))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
		
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);
		
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_DELEDETE);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// METHODE UPDATE | POST

	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithValidId_shouldReturnStatusOk() {
		
		try  {
			// Préparer le dto
			MatiereUpdateDto dtoRequest = new MatiereUpdateDto();
			dtoRequest.setIdMatiere(1);
			dtoRequest.setNomMatiere("geographie");

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);
				
			// Executer la requete
			String responseAsString = mockMvc
					.perform(put("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
								
			// Verifier si c'est un success
			assertThat(responseDto).isNotNull();
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_UPDATE);
				
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturnBadStatus() {
		
		try {
			// Préparer le dto
			MatiereUpdateDto dtoRequest = new MatiereUpdateDto();
			dtoRequest.setIdMatiere(2);
			dtoRequest.setNomMatiere("geographie");

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);
				
			// Executer la requete
			String responseAsString = mockMvc
					.perform(put("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
								
			// Verifier si c'est un success
			assertThat(responseDto).isNotNull();
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_UPDATE);
				
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
			
			
	}
	

	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdatingMatiereWithNameNull_shouldReturnBadStatus() {

		// Préparer le dto
		MatiereUpdateDto dtoRequest = new MatiereUpdateDto();
		dtoRequest.setIdMatiere(1);
		dtoRequest.setNomMatiere(null);

		try {
		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);
			
		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/matiere").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
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

	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithValidId_shouldReturnStatusOk() {
	
		try {
			String responseAsString = mockMvc
					.perform(get("/matiere/one?id=1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
			String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
			MatiereUpdateDto responseBody = mapper.readValue(responseBodyAsString, MatiereUpdateDto.class);
						
			// Verifier si c'est un success
			assertThat(responseDto).isNotNull();
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_READ_BY_ID);
			List<EtudiantUpdateDto> listeEtudiant = new ArrayList<EtudiantUpdateDto>();
			MatiereUpdateDto expectedBody = new MatiereUpdateDto(1, "maths", listeEtudiant);
			assertEquals(expectedBody, responseBody);

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}		
	
	
	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithInvalidId_shouldReturnBadStatus() {
		
		try {
		String responseAsString = mockMvc
				.perform(get("/matiere/one?id=2").contentType(MediaType.APPLICATION_JSON_VALUE))
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
	
	@Sql(statements = "insert into matiere (id, nom) values (1, 'maths')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from matiere where id = 1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithNegativeId_shouldReturnBadStatus() {

		try {		
		String responseAsString = mockMvc
				.perform(get("/matiere/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
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

	@Sql(statements = {"insert into matiere (id, nom) values (1, 'maths')", "insert into matiere (id, nom) values (2, 'geographie')"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = {"delete from matiere where id=1","delete from matiere where id=2"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingAllEntity_shouldReturnStatusOk() {
		
		try {
		String responseAsString = mockMvc
				.perform(get("/matiere/all").contentType(MediaType.APPLICATION_JSON_VALUE))
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
	
	@Test
	@Sql(statements = "insert into Matiere (id, nom) values (1, 'bob')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Matiere (id, nom) values (2, 'fish')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (1, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (2, 2, '2000-01-01', 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Examen (id, coef, date, matiere_id) values (3, 2, '2000-01-01', 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from Matiere", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadExamenByValidNomMat_shouldReturnList() throws UnsupportedEncodingException, Exception {
		
		List<EtudiantUpdateDto> etuVide = new ArrayList<>();
		MatiereUpdateDto mat = new MatiereUpdateDto(1, "bob");
		mat.setListeEtudiant(etuVide);

		
		ExamenUpdateDto e1 = new ExamenUpdateDto();
		e1.setIdExam(1);
		e1.setCoefExamen(2);
		e1.setDateExamen("2000-01-01");
		e1.setMatiereExamen(mat);
		
		ExamenUpdateDto e2 = new ExamenUpdateDto();
		e2.setIdExam(2);
		e2.setCoefExamen(2);
		e2.setDateExamen("2000-01-01");
		e2.setMatiereExamen(mat);
		
		List<ExamenUpdateDto> expectedDtoList = new ArrayList<>();
		expectedDtoList.add(e1);
		expectedDtoList.add(e2);
		
		String responseAsString = mockMvc.perform(get("/matiere/examens").param("nom", "bob"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

		String respBodyString = mapper.writeValueAsString(respDto.getBody());

		List<ExamenUpdateDto> responseList = mapper.readValue(respBodyString, ArrayList.class);

		List<ExamenUpdateDto> examList = new ArrayList<>();

		for (Object e : responseList) {

			String esString = mapper.writeValueAsString(e);
			ExamenUpdateDto exam = mapper.readValue(esString, ExamenUpdateDto.class);
			examList.add(exam);

		}

		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_EXAM_MATIERE);
		assertEquals(expectedDtoList, examList);
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", false);


	}
	
	public void testReadExamenByInvalidNomMat_shouldFail() throws UnsupportedEncodingException, Exception {
		
		String responseAsString = mockMvc.perform(get("/matiere/examens").param("nom", "blargh"))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_EXAM_MATIERE);
		assertNull(respDto.getBody());
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);

	}

}

