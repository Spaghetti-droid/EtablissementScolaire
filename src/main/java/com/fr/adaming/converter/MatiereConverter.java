package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dto.ExamenCreateDto;
import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;
import com.fr.adaming.entity.Matiere;
import com.fr.adaming.service.IEtudiantService;


public class MatiereConverter implements IConverter<MatiereCreateDto, MatiereUpdateDto, Matiere> {

	@Autowired
	private IEtudiantService service;
	
	@Override
	public Matiere convertCreateDtoToEntity(MatiereCreateDto createDto) {
		Matiere mat = new Matiere();
		mat.setNom(createDto.getNomMatiere());
		
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		for(String mail:createDto.getEmailListMatiere()) {
			etudiants.add(service.readByEmail(mail));
		}
		mat.setEtudiantList(etudiants);
		return mat;
	}

	@Override
	public MatiereCreateDto convertEntityToCreateDto(Matiere entity) {
		MatiereCreateDto mdto = new MatiereCreateDto();
		mdto.setNomMatiere(entity.getNom());
		
		
		List<String> nailEtu = new ArrayList<String>();
		for(Etudiant e : entity.getEtudiantList()) {
			nailEtu.add(e.getEmail());
		}
		mdto.setEmailListMatiere(nailEtu);
		return mdto;
	}

	@Override
	public Matiere convertUpdateDtoToEntity(MatiereUpdateDto updateDto) {
		Matiere mat = new Matiere();
		mat.setNom(updateDto.getNomMatiere());
		mat.setId(updateDto.getIdMatiere());
		
		List<Etudiant> etudiants = new ArrayList<Etudiant>();
		for(String mail:updateDto.getEmailListMatiere()) {
			etudiants.add(service.readByEmail(mail));
		}
		mat.setEtudiantList(etudiants);
		return mat;
	}

	@Override
	public MatiereUpdateDto convertEntityToUpdateDto(Matiere entity) {
		MatiereUpdateDto mdto = new MatiereUpdateDto();
		mdto.setNomMatiere(entity.getNom());
		mdto.setIdMatiere(entity.getId());
		
		List<String> nailEtu = new ArrayList<String>();
		for(Etudiant e : entity.getEtudiantList()) {
			nailEtu.add(e.getEmail());
		}
		mdto.setEmailListMatiere(nailEtu);
		return mdto;
	}

	@Override
	public List<Matiere> convertListCreateDtoToEntity(List<MatiereCreateDto> listeCreateDto) {
		List<Matiere> mats = new ArrayList<Matiere>();
		for(MatiereCreateDto e : listeCreateDto) {
			mats.add(convertCreateDtoToEntity(e));
		}
		return mats;
	}

	@Override
	public List<MatiereCreateDto> convertListEntityToCreateDto(List<Matiere> listeEntity) {
		List<MatiereCreateDto> exams = new ArrayList<MatiereCreateDto>();
		for(Matiere e : listeEntity) {
			exams.add(convertEntityToCreateDto(e));
		}
		return exams;
	}

	@Override
	public List<Matiere> convertListUpdateDtoToEntity(List<MatiereUpdateDto> listeUpdateDto) {
		List<Matiere> mats = new ArrayList<Matiere>();
		for(MatiereUpdateDto e : listeUpdateDto) {
			mats.add(convertUpdateDtoToEntity(e));
		}
		return mats;
	}

	@Override
	public List<MatiereUpdateDto> convertListEntityToUpdateDto(List<Matiere> listeEntity) {
		List<MatiereUpdateDto> exams = new ArrayList<MatiereUpdateDto>();
		for(Matiere e : listeEntity) {
			exams.add(convertEntityToUpdateDto(e));
		}
		return exams;
	}

}
