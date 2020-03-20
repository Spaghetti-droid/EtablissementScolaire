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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.constant.WebMappingConstant;
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ExamenUpdateDto;
import com.fr.adaming.dto.NoteUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.enumeration.Sexe;

@SpringBootTest
@AutoConfigureMockMvc
public class EtudiantControllerTests implements IControllerTest {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEtudiantWithExistingEmail_shouldNotWork() {
		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantCreateDto requestDto = new EtudiantCreateDto();

			requestDto.setSurname("Tony");
			requestDto.setName("Stark");
			requestDto.setAdress("Malibu Point 10880");
			requestDto.setPostalCode(90265);
			requestDto.setCity("Malibu");
			requestDto.setS(Sexe.M);
			requestDto.setIdentity(52);
			requestDto.setPhone(0221546435);
			requestDto.setMail("martinez@lucie.com");
			requestDto.setClasse(dtoclass);

			String dtoAsJson = mapper.writeValueAsString(requestDto);

			String responseAsString = mockMvc
					.perform(post("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(dtoResponse.getBody());

			EtudiantCreateDto respEtu = mapper.readValue(respBodyString, EtudiantCreateDto.class);

			assertThat(dtoResponse).isNotNull();
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_CREATE);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (2, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEtudiantWithExistingCni_shouldReturnNull() {

		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantCreateDto requestDto = new EtudiantCreateDto();

			requestDto.setSurname("Tony");
			requestDto.setName("Stark");
			requestDto.setAdress("Malibu Point 10880");
			requestDto.setPostalCode(90265);
			requestDto.setCity("Malibu");
			requestDto.setS(Sexe.M);
			requestDto.setIdentity(36);
			requestDto.setPhone(0221546435);
			requestDto.setMail("ironMan@marvel.com");
			requestDto.setClasse(dtoclass);

			String dtoAsJson = mapper.writeValueAsString(requestDto);

			String responseAsString = mockMvc
					.perform(post("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			ResponseDto dtoResponse = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(dtoResponse.getBody());

			EtudiantCreateDto respEtu = mapper.readValue(respBodyString, EtudiantCreateDto.class);

			assertThat(dtoResponse).isNotNull();
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_CREATE);
			assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (2, 54, 69500, 'martinez@lucie.fr', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithExistingMail_shouldReturnFalse() {

		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantUpdateDto dtoRequest = new EtudiantUpdateDto();

			dtoRequest.setIdentifiant(1);
			dtoRequest.setSurname("Tony");
			dtoRequest.setName("Stark");
			dtoRequest.setAdress("14 Avenue des Heros");
			dtoRequest.setPostalCode(69500);
			dtoRequest.setCity("Malibu");
			dtoRequest.setS(Sexe.M);
			dtoRequest.setIdentity(36);
			dtoRequest.setPhone(0656321425);
			dtoRequest.setMail("martinez@lucie.fr");
			dtoRequest.setClasse(dtoclass);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
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

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (2, 54, 69500, 'martinez@lucie.fr', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithExistingCni_shouldReturnFalse() {

		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantUpdateDto dtoRequest = new EtudiantUpdateDto();

			dtoRequest.setIdentifiant(1);
			dtoRequest.setSurname("Tony");
			dtoRequest.setName("Stark");
			dtoRequest.setAdress("14 Avenue des Heros");
			dtoRequest.setPostalCode(69500);
			dtoRequest.setCity("Malibu");
			dtoRequest.setS(Sexe.M);
			dtoRequest.setIdentity(54);
			dtoRequest.setPhone(0656321425);
			dtoRequest.setMail("martinez@lucie.com");
			dtoRequest.setClasse(dtoclass);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
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

	// Positif
	
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateEtudiantWithNegativeId_shouldReturnFalse() {

		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantUpdateDto dtoRequest = new EtudiantUpdateDto();

			dtoRequest.setIdentifiant(-1);
			dtoRequest.setSurname("Tony");
			dtoRequest.setName("Stark");
			dtoRequest.setAdress("14 Avenue des Heros");
			dtoRequest.setPostalCode(69500);
			dtoRequest.setCity("Malibu");
			dtoRequest.setS(Sexe.M);
			dtoRequest.setIdentity(36);
			dtoRequest.setPhone(0656321425);
			dtoRequest.setMail("martinez@lucie.com");
			dtoRequest.setClasse(dtoclass);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			assertThat(responseAsString).isEmpty();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateEtudiantWithNegativeCni_shouldReturnFalse() {

		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantUpdateDto dtoRequest = new EtudiantUpdateDto();

			dtoRequest.setIdentifiant(1);
			dtoRequest.setSurname("Tony");
			dtoRequest.setName("Stark");
			dtoRequest.setAdress("14 Avenue des Heros");
			dtoRequest.setPostalCode(69500);
			dtoRequest.setCity("Malibu");
			dtoRequest.setS(Sexe.M);
			dtoRequest.setIdentity(-36);
			dtoRequest.setPhone(0656321425);
			dtoRequest.setMail("martinez@lucie.com");
			dtoRequest.setClasse(dtoclass);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			assertThat(responseAsString).isEmpty();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Null
	
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testUpdateEtudiantWithNullEmail_shouldReturnFalse() {

		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantUpdateDto dtoRequest = new EtudiantUpdateDto();

			dtoRequest.setIdentifiant(1);
			dtoRequest.setSurname("Tony");
			dtoRequest.setName("Stark");
			dtoRequest.setAdress("14 Avenue des Heros");
			dtoRequest.setPostalCode(69500);
			dtoRequest.setCity("Malibu");
			dtoRequest.setS(Sexe.M);
			dtoRequest.setIdentity(36);
			dtoRequest.setPhone(0656321425);
			dtoRequest.setMail(null);
			dtoRequest.setClasse(dtoclass);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			assertThat(responseAsString).isEmpty();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithNullCni_shouldReturnFalse() {

		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantUpdateDto dtoRequest = new EtudiantUpdateDto();

			dtoRequest.setIdentifiant(1);
			dtoRequest.setSurname("Tony");
			dtoRequest.setName("Stark");
			dtoRequest.setAdress("14 Avenue des Heros");
			dtoRequest.setPostalCode(69500);
			dtoRequest.setCity("Malibu");
			dtoRequest.setS(Sexe.M);
			dtoRequest.setIdentity(0);
			dtoRequest.setPhone(0656321425);
			dtoRequest.setMail("martinez@lucie.com");
			dtoRequest.setClasse(dtoclass);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			assertThat(responseAsString).isEmpty();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithValidBody_shouldReturnStatusOk() {
		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantCreateDto requestDto = new EtudiantCreateDto();

			requestDto.setSurname("Tony");
			requestDto.setName("Stark");
			requestDto.setAdress("Malibu Point 10880");
			requestDto.setPostalCode(90265);
			requestDto.setCity("Malibu");
			requestDto.setS(Sexe.M);
			requestDto.setIdentity(52);
			requestDto.setPhone(0221546435);
			requestDto.setMail("ironMan@marvel.com");
			requestDto.setClasse(dtoclass);

			String dtoAsJson = mapper.writeValueAsString(requestDto);

			String responseAsString = mockMvc
					.perform(post("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			ResponseDto responseDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(responseDto.getBody());

			EtudiantCreateDto respAbsence = mapper.readValue(respBodyString, EtudiantCreateDto.class);

			assertThat(responseDto).isNotNull();
			assertEquals(requestDto, respAbsence);
			assertThat(responseDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_CREATE);
			assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEntityWithInvalidBody_shouldReturnBadStatus() {

		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantCreateDto requestDto = new EtudiantCreateDto();

			requestDto.setSurname("Tony");
			requestDto.setName("Stark");
			requestDto.setAdress("Malibu Point 10880");
			requestDto.setPostalCode(90265);
			requestDto.setCity("Malibu");
			requestDto.setS(Sexe.M);
			requestDto.setIdentity(52);
			requestDto.setPhone(0221546435);
			requestDto.setMail(null);
			requestDto.setClasse(dtoclass);

			String dtoAsJson = mapper.writeValueAsString(requestDto);

			String responseAsString = mockMvc
					.perform(post("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			assertThat(responseAsString).isEmpty();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingEntityWithValidId_shouldReturnStatusOk() {
		try {
			String responseAsString = mockMvc.perform(delete("/etudiant").param("id", "1")).andExpect(status().isOk())
					.andReturn().getResponse().getContentAsString();

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

	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testDeletingEntityWithInvalidId_shouldReturnBadStatus() {
		try {
			String responseAsString = mockMvc.perform(delete("/etudiant").param("id", "2"))
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

	@Test
	public void testDeletingEntityWithNegativeId_shouldReturnBadStatus() {
		try {
			String responseAsString = mockMvc.perform(delete("/etudiant").param("id", "-1"))
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

	// Update

	// Read by id

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithValidId_shouldReturnStatusOk() {

		try {
			String responseAsString = mockMvc
					.perform(get("/etudiant/one?id=1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			EtudiantUpdateDto respEtu = mapper.readValue(respBodyString, EtudiantUpdateDto.class);

			EtudiantUpdateDto expectedEtu = new EtudiantUpdateDto(1, null, null, null, 69500, null, null, 36, 656321425,
					"martinez@lucie.com", null);

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_READ_BY_ID);
			assertEquals(expectedEtu, respEtu);
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
	public void testReadingEntityWithInvalidId_shouldReturnBadStatus() {

		try {
			String responseAsString = mockMvc
					.perform(get("/etudiant/one?id=15").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			EtudiantUpdateDto respEtu = mapper.readValue(respBodyString, EtudiantUpdateDto.class);

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_READ_BY_ID);
			assertNull(respEtu);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingEntityWithNegativeId_shouldReturnBadStatus() {

		try {
			String responseAsString = mockMvc
					.perform(get("/etudiant/one?id=-1").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			EtudiantUpdateDto respEtu = mapper.readValue(respBodyString, EtudiantUpdateDto.class);

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.FAIL_READ_BY_ID);
			assertNull(respEtu);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Read all

	@Test
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadingAllEntity_shouldReturnStatusOk() {

		try {
			String responseAsString = mockMvc
					.perform(get("/etudiant/all").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());

			List<EtudiantUpdateDto> respObjList = mapper.readValue(respBodyString, ArrayList.class);
			List<EtudiantUpdateDto> respEtuList = new ArrayList<EtudiantUpdateDto>();

			for (Object e : respObjList) {

				String eAsString = mapper.writeValueAsString(e);
				EtudiantUpdateDto etu = mapper.readValue(eAsString, EtudiantUpdateDto.class);
				respEtuList.add(etu);

			}

			List<EtudiantUpdateDto> expectedList = new ArrayList<EtudiantUpdateDto>();

			EtudiantUpdateDto etu1 = new EtudiantUpdateDto(1, null, null, null, 69500, null, null, 36, 656321425,
					"martinez@lucie.com", null);

			expectedList.add(etu1);

			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_READ_ALL);
			assertEquals(expectedList, respEtuList);
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
	public void testUpdatingEntityWithValidId_shouldReturnStatusOk() {
		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantUpdateDto dtoRequest = new EtudiantUpdateDto();

			dtoRequest.setIdentifiant(1);
			dtoRequest.setSurname("Tony");
			dtoRequest.setName("Stark");
			dtoRequest.setAdress("14 Avenue des Heros");
			dtoRequest.setPostalCode(69800);
			dtoRequest.setCity("Malibu");
			dtoRequest.setS(Sexe.M);
			dtoRequest.setIdentity(36);
			dtoRequest.setPhone(0656321425);
			dtoRequest.setMail("martinez@lucie.com");
			dtoRequest.setClasse(dtoclass);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
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
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (2, 54, 69500, 'martinez@lucie.fr', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdatingEntityWithInvalidId_shouldReturnBadStatus() {

		try {
			ClasseUpdateDto dtoclass = new ClasseUpdateDto();
			dtoclass.setId(1);
			dtoclass.setName("Terminal");

			EtudiantUpdateDto dtoRequest = new EtudiantUpdateDto();

			dtoRequest.setIdentifiant(2);
			dtoRequest.setSurname("Tony");
			dtoRequest.setName("Stark");
			dtoRequest.setAdress("14 Avenue des Heros");
			dtoRequest.setPostalCode(69800);
			dtoRequest.setCity("Malibu");
			dtoRequest.setS(Sexe.M);
			dtoRequest.setIdentity(36);
			dtoRequest.setPhone(0656321425);
			dtoRequest.setMail("martinez@lucie.com");
			dtoRequest.setClasse(dtoclass);

			// Convertir le dto en JSON
			String dtoAsJson = mapper.writeValueAsString(dtoRequest);

			// Execution de la requete
			String responseAsString = mockMvc
					.perform(put("/etudiant").contentType(MediaType.APPLICATION_JSON_VALUE).content(dtoAsJson))
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

	
	@Sql(statements = "insert into etudiant (id, cni, cp, email, nom, num, prenom) values(8, 545684842, 69500, 'ironman@marvel.fr','Stark', 235645611,'Tony')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen (id, coef, date) values (1, 2, '2020-05-21') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into examen (id, coef, date) values (2, 2, '2020-05-21') ", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, valeur, etudiant_id, examen_id) values (1, 15, 8, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into Note (id, valeur, etudiant_id, examen_id) values (2, 18, 8, 2)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from Note", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from examen", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Test
	public void testReadNoteWithValidEmail_shouldReturnList() {

		try {
			EtudiantUpdateDto dtoEtu = new EtudiantUpdateDto();
			dtoEtu.setIdentifiant(8);
			dtoEtu.setSurname("Tony");
			dtoEtu.setName("Stark");
			dtoEtu.setPostalCode(69500);
			dtoEtu.setS(Sexe.M);
			dtoEtu.setIdentity(545684842);
			dtoEtu.setPhone(235645611);
			dtoEtu.setMail("ironman@marvel.fr");
			
			ExamenUpdateDto dtoExam1 = new ExamenUpdateDto();
			dtoExam1.setIdExam(1);
			dtoExam1.setCoefExamen(2);
			dtoExam1.setDateExamen("2020-05-21");
			
			ExamenUpdateDto dtoExam2 = new ExamenUpdateDto();
			dtoExam2.setIdExam(2);
			dtoExam2.setCoefExamen(2);
			dtoExam2.setDateExamen("2020-05-21");
			
			NoteUpdateDto dtoNote1 = new NoteUpdateDto();
			dtoNote1.setId(1);
			dtoNote1.setValue(15);
			dtoNote1.setEtudiant(dtoEtu);
			dtoNote1.setExamen(dtoExam1);
			
			NoteUpdateDto dtoNote2 = new NoteUpdateDto();
			dtoNote2.setId(1);
			dtoNote2.setValue(15);
			dtoNote2.setEtudiant(dtoEtu);
			dtoNote2.setExamen(dtoExam1);
			
			List<NoteUpdateDto> expectedDtoList = new ArrayList<>();
			expectedDtoList.add(dtoNote1);
			expectedDtoList.add(dtoNote2);
			
			String responseAsString = mockMvc
					.perform(get("/etudiant/note?mail=ironman@marvel.fr").contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

			String respBodyString = mapper.writeValueAsString(respDto.getBody());
			
			List<NoteUpdateDto> responseList = mapper.readValue(responseAsString, ArrayList.class);
			
			List <NoteUpdateDto> noteList = new ArrayList<>();
			
			for (Object e : responseList) {
				
				String esString = mapper.writeValueAsString(e);
				NoteUpdateDto note = mapper.readValue(esString, NoteUpdateDto.class);
				noteList.add(note);
				
			}
			
			assertThat(respDto).isNotNull();
			assertThat(respDto).hasFieldOrPropertyWithValue("message", WebMappingConstant.SUCCESS_NOTE_ETUDIANT);
			assertEquals(expectedDtoList, noteList);
			assertThat(respDto).hasFieldOrPropertyWithValue("isError", false);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
