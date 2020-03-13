package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.entity.Etudiant;

public class EtudiantConverter {

	public static EtudiantCreateDto convertEtudiantToEtudiantCreateDto(Etudiant etu) {
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

	public static Etudiant convertEtudiantCreateDtoToEtudiant(EtudiantCreateDto etuDto) {
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

	
	public static EtudiantUpdateDto convertEtudiantToEtudiantUpdateDto(Etudiant etu) {
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
			etuDto.setClassroom(etu.getClasse());
			etuDto.setListMatiere(etu.getMatiereList());
			return etuDto;

		} else {
			return null;
		}
	}

	public static Etudiant convertEtudiantUpdateDtoToEtudiant(EtudiantUpdateDto etuDto) {
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
			etudiant.setClasse(etuDto.getClassroom());
			etudiant.setMatiereList(etuDto.getListMatiere());

			return etudiant;

		} else {
			return null;
		}
	}

	public static List<Etudiant> listEtudiantUpdateDtoToEtudiant(List<EtudiantUpdateDto> etudtolist) {

		List<Etudiant> etulist = new ArrayList<Etudiant>();

		for (EtudiantUpdateDto etudto : etudtolist) {

			etulist.add(convertEtudiantUpdateDtoToEtudiant(etudto));

		}
		return etulist;
	}

	public static List<EtudiantUpdateDto> listEtudiantToEtudiantUpdateDto(List<Etudiant> etulist) {

		List<EtudiantUpdateDto> etudtolist = new ArrayList<EtudiantUpdateDto>();

		for (Etudiant e : etulist) {

			etudtolist.add(convertEtudiantToEtudiantUpdateDto(e));
		}

		return etudtolist;
	}

}
