package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.dto.EtudiantCreateDto;
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

	public static List<Etudiant> ListEtudiantCreateDtoToEtudiant(List<EtudiantCreateDto> etudtolist) {

		List<Etudiant> etulist = new ArrayList<Etudiant>();

		for (EtudiantCreateDto etudto : etudtolist) {

			etulist.add(EtudiantCreateDtoToEtudiant(etudto));

		}
		return etulist;
	}
	

	public static List<EtudiantCreateDto> ListEtudiantToEtudiantCreateDto(List<Etudiant> etulist) {

		List<EtudiantCreateDto> etudtolist = new ArrayList<EtudiantCreateDto>();

		for (Etudiant e : etulist) {

			etudtolist.add(EtudiantToEtudiantCreateDto(e));
		}

		return etudtolist;
	}
	
	
}
