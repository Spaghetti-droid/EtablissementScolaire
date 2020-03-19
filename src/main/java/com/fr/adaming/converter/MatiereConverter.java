package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.dto.MatiereCreateDto;
import com.fr.adaming.dto.MatiereUpdateDto;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.E;
import com.fr.adaming.service.IEtudiantService;

@Component
public class MatiereConverter implements IConverter<MatiereCreateDto, MatiereUpdateDto, E> {

	@Autowired
	private IEtudiantService service;
	
	@Autowired
	private IConverter<EtudiantCreateDto, EtudiantUpdateDto, Etudiant> converteretudiant;
	
	@Override
	public E convertCreateDtoToEntity(MatiereCreateDto createDto) {
		if (createDto == null) {
			return null;
		}
		E matiere = new E();
		matiere.setNom(createDto.getNomMatiere());
		List<Etudiant> etudiants = converteretudiant.convertListUpdateDtoToEntity(createDto.getListeEtudiant());
		matiere.setEtudiantList(etudiants);
		return matiere;
	}

	@Override
	public MatiereCreateDto convertEntityToCreateDto(E entity) {
		if (entity == null) {
			return null;
		}
		MatiereCreateDto matiereDto = new MatiereCreateDto();
		matiereDto.setNomMatiere(entity.getNom());
		List<EtudiantUpdateDto> etudiants = converteretudiant.convertListEntityToUpdateDto(entity.getEtudiantList());
		matiereDto.setListeEtudiant(etudiants);
		return matiereDto;
	}

	@Override
	public E convertUpdateDtoToEntity(MatiereUpdateDto updateDto) {
		if (updateDto == null) {
			return null;
		}
		E matiere = new E();
		matiere.setNom(updateDto.getNomMatiere());
		matiere.setId(updateDto.getIdMatiere());
		List<Etudiant> etudiants = converteretudiant.convertListUpdateDtoToEntity(updateDto.getListeEtudiant());
		matiere.setEtudiantList(etudiants);
		return matiere;
	}

	@Override
	public MatiereUpdateDto convertEntityToUpdateDto(E entity) {
		if (entity == null) {
			return null;
		}
		MatiereUpdateDto matiereDto = new MatiereUpdateDto();
		matiereDto.setNomMatiere(entity.getNom());
		matiereDto.setIdMatiere(entity.getId());
		List<EtudiantUpdateDto> etudiants = converteretudiant.convertListEntityToUpdateDto(entity.getEtudiantList());
		matiereDto.setListeEtudiant(etudiants);
		return matiereDto;
	}

	@Override
	public List<E> convertListCreateDtoToEntity(List<MatiereCreateDto> listeCreateDto) {
		if (listeCreateDto == null) {
			return null;
		}
		List<E> matieres = new ArrayList<E>();
		for(MatiereCreateDto e : listeCreateDto) {
			matieres.add(convertCreateDtoToEntity(e));
		}
		return matieres;
	}

	@Override
	public List<MatiereCreateDto> convertListEntityToCreateDto(List<E> listeEntity) {
		if (listeEntity == null) {
			return null;
		}
		List<MatiereCreateDto> matieres = new ArrayList<MatiereCreateDto>();
		for(E matiere : listeEntity) {
			matieres.add(convertEntityToCreateDto(matiere));
		}
		return matieres;
	}

	@Override
	public List<E> convertListUpdateDtoToEntity(List<MatiereUpdateDto> listeUpdateDto) {
		if (listeUpdateDto == null) {
			return null;
		}
		List<E> matieres = new ArrayList<E>();
		for(MatiereUpdateDto e : listeUpdateDto) {
			matieres.add(convertUpdateDtoToEntity(e));
		}
		return matieres;
	}

	@Override
	public List<MatiereUpdateDto> convertListEntityToUpdateDto(List<E> listeEntity) {
		if (listeEntity == null) {
			return null;
		}
		List<MatiereUpdateDto> matieres = new ArrayList<MatiereUpdateDto>();
		for(E m : listeEntity) {
			matieres.add(convertEntityToUpdateDto(m));
		}
		return matieres;
	}

}
