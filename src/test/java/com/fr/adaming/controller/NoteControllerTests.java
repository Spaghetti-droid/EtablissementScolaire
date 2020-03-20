package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.dto.NoteCreateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.dto.ResponseDto;



@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTests implements IControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	@Sql(statements = "insert into examen values (1, 2, '2020-05-21', null, null) ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Etudiant (id, cp, num, sexe, cni, email) values (1,0,0,0, 123456, 'tm.cloarec@gmail.com')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = { "delete from Note", "delete from Etudiant","delete from Examen" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithValidBody_shouldReturnStatusOk() {
		try {
		// Préparer le dto
		ExamenUpdateDto dtoExam = new ExamenUpdateDto();
		dtoExam.setIdExam(1);
		dtoExam.setDateExamen("2020-05-21");
		dtoExam.setCoefExamen(2);
		dtoExam.setMatiereExamen(null);
		dtoExam.setTypeExamen(null);

		EtudiantUpdateDto dtoEtu = new EtudiantUpdateDto();
		dtoEtu.setIdentifiant(1);
		dtoEtu.setName("CLOAREC");
		dtoEtu.setSurname("Thierry");
		dtoEtu.setPostalCode(0);
		dtoEtu.setS(null);
		dtoEtu.setIdentity(123456);
		dtoEtu.setPhone(0);
		dtoEtu.setMail("ironMan@marvel.fr");

		NoteCreateDto dtoRequest = new NoteCreateDto();
		dtoRequest.setValue(15);
		dtoRequest.setEtudiant(dtoEtu);
		dtoRequest.setExamen(dtoExam);

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);

		// Execution de la requete

		String responseAsString = mockMvc
				.perform(post("/note").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);

		// Verifier si c'est un success
		assertThat(dtoResponse).isNotNull();
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_CREATE);
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("body", dtoResponse.getBody());
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	// METHODE DELETE BY ID | DELETE
	
		@Sql(statements = "insert into note (id, valeur) values(1,15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Test
		@Override
		public void testDeletingEntityWithValidId_shouldReturnStatusOk() {
			
			try {
			String responseAsString = mockMvc
					.perform(delete("/note").param("id", "1"))
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
		
		@Sql(statements = "insert into note (id, valeur) values(1,15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Test
		@Override
		public void testDeletingEntityWithInvalidId_shouldReturnBadStatus() {
			
			try {
			String responseAsString = mockMvc
					.perform(delete("/note").param("id", "2"))
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
		
		// METHODE UPDATE | PUT
		
		@Sql(statements = "insert into note (id, valeur) values(1,15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Test
		@Override
		public void testUpdatingEntityWithValidId_shouldReturnStatusOk() {

			try {
			// Préparer le dto
			NoteUpdateDto dtoRequest = new NoteUpdateDto();
			dtoRequest.setId(1);
			dtoRequest.setValue(15);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);
			
			// Executer la requete
			String responseAsString = mockMvc
					.perform(put("/note").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
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
		
		@Sql(statements = "insert into note (id, valeur) values(1,15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Test
		@Override
		public void testUpdatingEntityWithInvalidId_shouldReturnBadStatus() {

			try {
			// Préparer le dto
			NoteUpdateDto dtoRequest = new NoteUpdateDto();
			dtoRequest.setId(2);
			dtoRequest.setValue(14);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);
			
			// Executer la requete
			String responseAsString = mockMvc
					.perform(put("/note").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
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
		
		
		
		// METHODE READ BY ID | GET
		
		@Sql(statements = "insert into note (id, valeur) values(1,15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Test
		@Override
		public void testReadingEntityWithValidId_shouldReturnStatusOk() {

			try {
			String responseAsString = mockMvc
					.perform(get("/note/one?id=1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
			String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
			NoteUpdateDto responseBody = mapper.readValue(responseBodyAsString, NoteUpdateDto.class);
					
			// Verifier si c'est un success
			assertThat(responseDto).isNotNull();
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_READ_BY_ID);
			NoteUpdateDto expectedBody = new NoteUpdateDto();
			expectedBody.setValue(15);
			expectedBody.setId(1);
			assertEquals(expectedBody, responseBody);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
}

		@Sql(statements = "insert into note (id, valeur) values(1,15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Test
		@Override
		public void testReadingEntityWithInvalidId_shouldReturnBadStatus() {

			try {
			String responseAsString = mockMvc
					.perform(get("/note/one?id=2").contentType(MediaType.APPLICATION_JSON_VALUE))
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
		
		@Sql(statements = "insert into note (id, valeur) values(1,15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Test
		@Override
		public void testReadingEntityWithNegativeId_shouldReturnBadStatus() {

			try {
			String responseAsString = mockMvc
					.perform(get("/note/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
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
		
		// METHODE READ ALL | GET
		
		@Sql(statements = "insert into note (id, valeur) values(1,15)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "insert into note (id, valeur) values(2,18)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		@Test
		@Override
		public void testReadingAllEntity_shouldReturnStatusOk() {

			try {
			String responseAsString = mockMvc
					.perform(get("/note/all").contentType(MediaType.APPLICATION_JSON_VALUE))
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

		@Override
		public void testCreatingEntityWithInvalidBody_shouldReturnBadStatus() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void testDeletingEntityWithNegativeId_shouldReturnBadStatus() {
			try {
				String responseAsString = mockMvc
						.perform(delete("/note").param("id", "-2"))
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
	
}