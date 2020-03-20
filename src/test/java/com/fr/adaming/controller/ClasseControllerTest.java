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
import com.fr.adaming.dto.ClasseCreateDto;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.ResponseDto;

@SpringBootTest
@AutoConfigureMockMvc
public class ClasseControllerTest implements IControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	// METHODE CREATE | POST

	@Test
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Override
	public void testCreatingEntityWithValidBody_shouldReturnStatusOk() {

		// Préparer le dto
		ClasseCreateDto dtoRequest = new ClasseCreateDto();
		dtoRequest.setName("5e1");
		try {
			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(post("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			// Convertir la réponse JSON en dtoResponse
			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
			String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
			ClasseCreateDto responseBody = mapper.readValue(responseBodyAsString, ClasseCreateDto.class);

			// Verifier si c'est un success
			assertThat(responseDto).isNotNull();
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_CREATE);
			ClasseCreateDto expectedBody = new ClasseCreateDto("5e1");
			assertEquals(expectedBody, responseBody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithInvalidBody_shouldReturnBadStatus() {
		try {
			// Execution de la requete
			String responseAsString = mockMvc
					.perform(post("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content("{'name':}"))
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

	@Sql(statements = "insert into classe (id, nom) values(1,'5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithValidId_shouldReturnStatusOk() {
		try {
		String responseAsString = mockMvc
				.perform(delete("/classe").param("id", "1"))
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

	@Sql(statements = "insert into classe (id, nom) values(1,'5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testDeletingEntityWithInvalidId_shouldReturnBadStatus(){

		try {
		String responseAsString = mockMvc.perform(delete("/classe").param("id", "2")).andExpect(status().isBadRequest())
				.andReturn().getResponse().getContentAsString();

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

	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithValidId_shouldReturnStatusOk() {
		try {
		// Préparer le dto
		ClasseUpdateDto dtoRequest = new ClasseUpdateDto();
		dtoRequest.setId(1);
		dtoRequest.setName("4e3");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);

		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
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

	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testUpdatingEntityWithInvalidId_shouldReturnBadStatus() {
		try {
		// Préparer le dto
		ClasseUpdateDto dtoRequest = new ClasseUpdateDto();
		dtoRequest.setId(2);
		dtoRequest.setName("4e3");

		// Convertir le dto en JSON
		String dtoAsJson = mapper.writeValueAsString(dtoRequest);

		// Executer la requete
		String responseAsString = mockMvc
				.perform(put("/classe").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
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

	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithValidId_shouldReturnStatusOk() {

		try {
		String responseAsString = mockMvc.perform(get("/classe/one?id=1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);
		String responseBodyAsString = mapper.writeValueAsString(responseDto.getBody());
		ClasseUpdateDto responseBody = mapper.readValue(responseBodyAsString, ClasseUpdateDto.class);

		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_READ_BY_ID);
		ClasseUpdateDto expectedBody = new ClasseUpdateDto(1, "5e1");
		assertEquals(expectedBody, responseBody);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithInvalidId_shouldReturnBadStatus(){

		try {
		String responseAsString = mockMvc.perform(get("/classe/one?id=2").contentType(MediaType.APPLICATION_JSON_VALUE))
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

	@Sql(statements = "insert into classe (id, nom) values (1, '5e1')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingEntityWithNegativeId_shouldReturnBadStatus()  {

		try {
		String responseAsString = mockMvc
				.perform(get("/classe/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		// Convertir la réponse JSON en dtoResponse
		ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);

		// Verifier si c'est un success
		assertThat(responseDto).isNotNull();
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", true);
		assertThat(responseDto).hasFieldOrPropertyWithValue("message",WebMappingConstant.FAIL_READ_BY_ID);
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

	@Sql(statements = { "insert into classe (id, nom) values (1, '5e1')",
			"insert into classe (id, nom) values (2, '4e2')" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	@Override
	public void testReadingAllEntity_shouldReturnStatusOk(){

		try {
		String responseAsString = mockMvc.perform(get("/classe/all").contentType(MediaType.APPLICATION_JSON_VALUE))
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
	public void testDeletingEntityWithNegativeId_shouldReturnBadStatus() {
		try {
			String responseAsString = mockMvc.perform(delete("/classe").param("id", "-1")).andExpect(status().isBadRequest())
					.andReturn().getResponse().getContentAsString();

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
