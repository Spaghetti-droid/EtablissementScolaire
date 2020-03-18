package com.fr.adaming.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.fr.adaming.dto.ClasseUpdateDto;
import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.ResponseDto;
import com.fr.adaming.enumeration.Sexe;

@SpringBootTest
@AutoConfigureMockMvc
public class EtudiantControllerTests {

	@Autowired
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	// Create

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEtudiant_shouldWork() throws Exception {

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
		assertThat(responseDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(responseDto).hasFieldOrPropertyWithValue("isError", false);

	}
	
	
	//Null
	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEtudiantWithNullEmail_shouldReturnNull() throws Exception { 

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
		

	}


	// existing
	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEtudiantWithExistingEmail_shouldNotWork() throws Exception {
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
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
	}

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (2, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testCreatingEtudiantWithExistingCni_shouldReturnNull() throws Exception {
		
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
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
	}

	// Read by id

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByExistingId_ShouldReturnTrue() throws Exception{
		
		String responseAsString = mockMvc.perform(get("/etudiant/one?id=1")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

		String respBodyString = mapper.writeValueAsString(respDto.getBody());

		EtudiantUpdateDto respEtu = mapper.readValue(respBodyString, EtudiantUpdateDto.class);


		EtudiantUpdateDto expectedEtu = new EtudiantUpdateDto(1, null, null, null, 69500, null, null, 36, 0656321425, "martinez@lucie.com", null);

		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertEquals(expectedEtu, respEtu);
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", false);

	}

	@Test
	public void testReadByNotExistingId_ShouldReturnFalse() throws Exception {

		String responseAsString = mockMvc
				.perform(get("/etudiant/one?id=15")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

		String respBodyString = mapper.writeValueAsString(respDto.getBody());

		EtudiantUpdateDto respEtu = mapper.readValue(respBodyString, EtudiantUpdateDto.class);

		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", "FAIL");
		assertNull(respEtu);
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);

	}

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadByNegativeId_shouldReturnFalse() throws Exception {
		
		String responseAsString = mockMvc
				.perform(get("/etudiant/one?id=-1")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();

		ResponseDto respDto = mapper.readValue(responseAsString, ResponseDto.class);

		String respBodyString = mapper.writeValueAsString(respDto.getBody());

		EtudiantUpdateDto respEtu = mapper.readValue(respBodyString, EtudiantUpdateDto.class);


		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", "FAIL");
		assertNull(respEtu);
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", true);

	}
		

	// Read all

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (2, 54, 69500, 'martinez@lucie.fr', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testReadAll_ShouldReturnListEtudiant() throws Exception {
		
		String responseAsString = mockMvc.perform(get("/etudiant/all")
				.contentType(MediaType.APPLICATION_JSON_VALUE))
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

		ClasseUpdateDto classe = new ClasseUpdateDto(1,  "Terminal");
		
		List<EtudiantUpdateDto> expectedList = new ArrayList<EtudiantUpdateDto>();

		EtudiantUpdateDto etu1 = new EtudiantUpdateDto(1, "martinez", "Lucie", "25 fdd", 69500, "Bron", Sexe.F, 36, 0656321425, "martinez@lucie.com", classe);
		EtudiantUpdateDto etu2 = new EtudiantUpdateDto(2, "martinez", "Lucie", "25 fdd", 69500, "Bron", Sexe.F, 54, 0656321425, "martinez@lucie.fr", classe);

		expectedList.add(etu1);
		expectedList.add(etu2);

		assertThat(respDto).isNotNull();
		assertThat(respDto).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertEquals(expectedList, respEtuList);
		assertThat(respDto).hasFieldOrPropertyWithValue("isError", false);

	}
		

	// Update

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num, classe_id) values (1, 36, 69500, 'martinez@lucie.com', 0656321425, 1)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiant_shouldWork() throws Exception {
		
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
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "SUCCESS");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", false);
	}
		

	// Unicité
	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (2, 54, 69500, 'martinez@lucie.fr', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithExistingId_shouldReturnFalse() throws Exception {
		
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
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
	}
		

	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (2, 54, 69500, 'martinez@lucie.fr', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithExistingMail_shouldReturnFalse() throws Exception {
		
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
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
	}
	
	
	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (2, 54, 69500, 'martinez@lucie.fr', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithExistingCni_shouldReturnFalse() throws Exception {
		
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
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("message", "FAIL");
		assertThat(dtoResponse).hasFieldOrPropertyWithValue("isError", true);
	}


	// Positif
	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithNegativeId_shouldReturnFalse() throws Exception {
		
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
	}


	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithNegativeCni_shouldReturnFalse() throws Exception {
		
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
	}
		

	// Null
	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithNullEmail_shouldReturnFalse() throws Exception {
		
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
	}
	
	@Test
	@Sql(statements = "insert into classe (id, nom) values (1, 'Terminal')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "insert into etudiant (id, cni, cp, email, num) values (1, 36, 69500, 'martinez@lucie.com', 0656321425)", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from etudiant", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "delete from classe", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testUpdateEtudiantWithNullCni_shouldReturnFalse() throws Exception {
		
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
	}
}
