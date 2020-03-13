package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.entity.Etudiant;

public class EtudiantConverter {

	public static EtudiantCreateDto EtudiantToEtudiantCreateDto(Etudiant etu) {
		if (etu != null) {
			EtudiantCreateDto etuDto = new EtudiantCreateDto();

			etuDto.setName(etu.getNom());
			etuDto.setSurname(etu.getPrenom());
			etuDto.setAdress(etu.getAdresse());
			etuDto.setPostalCode(etu.getCp());
			etuDto.setCity(etu.getVille());
			etuDto.setS(etu.getSexe());
			etuDto.setIdentity(etu.getCni());
			etuDto.setPhone(etu.getNum());
			etuDto.setMail(etu.getEmail());
			return etuDto;

		} else {
			return null;
		}
	}

	public static Etudiant EtudiantCreateDtoToEtudiant(EtudiantCreateDto etuDto) {
		if (etuDto != null) {
			Etudiant etudiant = new Etudiant();

			etudiant.setEmail(etuDto.getName());
			etudiant.setNom(etuDto.getSurname());
			etudiant.setPrenom(etuDto.getAdress());
			etudiant.setCp(etuDto.getPostalCode());
			etudiant.setVille(etuDto.getCity());
			etudiant.setSexe(etuDto.getS());
			etudiant.setCni(etuDto.getIdentity());
			etudiant.setNum(etuDto.getPhone());
			etudiant.setEmail(etuDto.getMail());

			return etudiant;

		} else {
			return null;
		}
	}

	
	public static EtudiantUpdateDto EtudiantToEtudiantUpdateDto(Etudiant etu) {
		if (etu != null) {
			EtudiantUpdateDto etuDto = new EtudiantUpdateDto();

			etuDto.setIdentifiant(etu.getId());
			etuDto.setName(etu.getNom());
			etuDto.setSurname(etu.getPrenom());
			etuDto.setAdress(etu.getAdresse());
			etuDto.setPostalCode(etu.getCp());
			etuDto.setCity(etu.getVille());
			etuDto.setS(etu.getSexe());
			etuDto.setIdentity(etu.getCni());
			etuDto.setPhone(etu.getNum());
			etuDto.setMail(etu.getEmail());
			return etuDto;

		} else {
			return null;
		}
	}

	public static Etudiant EtudiantUpdateDtoToEtudiant(EtudiantUpdateDto etuDto) {
		if (etuDto != null) {
			Etudiant etudiant = new Etudiant();

			etudiant.setId(etuDto.getIdentifiant());
			etudiant.setEmail(etuDto.getName());
			etudiant.setNom(etuDto.getSurname());
			etudiant.setPrenom(etuDto.getAdress());
			etudiant.setCp(etuDto.getPostalCode());
			etudiant.setVille(etuDto.getCity());
			etudiant.setSexe(etuDto.getS());
			etudiant.setCni(etuDto.getIdentity());
			etudiant.setNum(etuDto.getPhone());
			etudiant.setEmail(etuDto.getMail());

			return etudiant;

		} else {
			return null;
		}
	}

	public static List<Etudiant> ListEtudiantUpdateDtoToEtudiant(List<EtudiantUpdateDto> etudtolist) {

		List<Etudiant> etulist = new ArrayList<Etudiant>();

		for (EtudiantUpdateDto etudto : etudtolist) {

			etulist.add(EtudiantUpdateDtoToEtudiant(etudto));

		}
		return etulist;
	}

	public static List<EtudiantUpdateDto> ListEtudiantToEtudiantUpdateDto(List<Etudiant> etulist) {

		List<EtudiantUpdateDto> etudtolist = new ArrayList<EtudiantUpdateDto>();

		for (Etudiant e : etulist) {

			etudtolist.add(EtudiantToEtudiantUpdateDto(e));
		}

		return etudtolist;
	}

}
