package com.fr.adaming.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fr.adaming.dto.EtudiantCreateDto;
import com.fr.adaming.dto.EtudiantUpdateDto;
import com.fr.adaming.entity.Classe;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Matiere;

@Component
public class EtudiantConverter implements IConverter<EtudiantCreateDto, EtudiantUpdateDto, Etudiant> {

	@Autowired
	private MatiereConverter matConverter;
	
	@Autowired
	private ClasseConverter classConverter;
	
	
	@Override
	public Etudiant convertCreateDtoToEntity(EtudiantCreateDto createDto) {
		if (createDto != null) {
			Etudiant etudiant = new Etudiant();

			etudiant.setNom(createDto.getName());
			etudiant.setPrenom(createDto.getSurname());
			etudiant.setAdresse(createDto.getAdress());
			etudiant.setCp(createDto.getPostalCode());
			etudiant.setVille(createDto.getCity());
			etudiant.setSexe(createDto.getS());
			etudiant.setCni(createDto.getIdentity());
			etudiant.setNum(createDto.getPhone());
			etudiant.setEmail(createDto.getMail());

			Classe classe = classConverter.convertUpdateDtoToEntity(createDto.getClasse());
			etudiant.setClasse(classe);
			
			return etudiant;

		} else {
			return null;
		}
	}

	@Override
	public EtudiantCreateDto convertEntityToCreateDto(Etudiant entity) {
		if (entity != null) {
			EtudiantCreateDto etuDto = new EtudiantCreateDto();

			etuDto.setName(entity.getNom());
			etuDto.setSurname(entity.getPrenom());
			etuDto.setAdress(entity.getAdresse());
			etuDto.setPostalCode(entity.getCp());
			etuDto.setCity(entity.getVille());
			etuDto.setS(entity.getSexe());
			etuDto.setIdentity(entity.getCni());
			etuDto.setPhone(entity.getNum());
			etuDto.setMail(entity.getEmail());
			
			etuDto.setClasse(classConverter.convertEntityToUpdateDto(entity.getClasse()));
	
			return etuDto;

		} else {
			return null;
		}
	}

	@Override
	public Etudiant convertUpdateDtoToEntity(EtudiantUpdateDto updateDto) {
		if (updateDto != null) {
			Etudiant etudiant = new Etudiant();

			etudiant.setId(updateDto.getIdentifiant());
			etudiant.setNom(updateDto.getName());
			etudiant.setPrenom(updateDto.getSurname());
			etudiant.setAdresse(updateDto.getAdress());
			etudiant.setCp(updateDto.getPostalCode());
			etudiant.setVille(updateDto.getCity());
			etudiant.setSexe(updateDto.getS());
			etudiant.setCni(updateDto.getIdentity());
			etudiant.setNum(updateDto.getPhone());
			etudiant.setEmail(updateDto.getMail());

			Classe classe = classConverter.convertUpdateDtoToEntity(updateDto.getClasse());
			etudiant.setClasse(classe);
			
			return etudiant;

		} else {
			return null;
		}
	}

	@Override
	public EtudiantUpdateDto convertEntityToUpdateDto(Etudiant entity) {
		if (entity != null) {
			EtudiantUpdateDto etuDto = new EtudiantUpdateDto();

			etuDto.setIdentifiant(entity.getId());
			etuDto.setName(entity.getNom());
			etuDto.setSurname(entity.getPrenom());
			etuDto.setAdress(entity.getAdresse());
			etuDto.setPostalCode(entity.getCp());
			etuDto.setCity(entity.getVille());
			etuDto.setS(entity.getSexe());
			etuDto.setIdentity(entity.getCni());
			etuDto.setPhone(entity.getNum());
			etuDto.setMail(entity.getEmail());
			
			etuDto.setClasse(classConverter.convertEntityToUpdateDto(entity.getClasse()));
	
			return etuDto;

		} else {
			return null;
		}
	}


	@Override
	public List<Etudiant> convertListUpdateDtoToEntity(List<EtudiantUpdateDto> listeUpdateDto) {
		if (listeUpdateDto != null) {
			List<Etudiant> etulist = new ArrayList<Etudiant>();
		
			for (EtudiantUpdateDto etudto : listeUpdateDto) {
				etulist.add(convertUpdateDtoToEntity(etudto));
			}
			return etulist;
		} else {
			return null;
		}
		
	}

	@Override
	public List<EtudiantUpdateDto> convertListEntityToUpdateDto(List<Etudiant> listeEntity) {
		if (listeEntity != null) {
			List<EtudiantUpdateDto> etudtolist = new ArrayList<EtudiantUpdateDto>();
		
			for (Etudiant e : listeEntity) {

				etudtolist.add(convertEntityToUpdateDto(e));
			}
		return etudtolist;
		} else {
			return null;
		}
	}

	@Override
	public List<Etudiant> convertListCreateDtoToEntity(List<EtudiantCreateDto> listeCreateDto) {
		if (listeCreateDto != null) {
			List<Etudiant> etulist = new ArrayList<Etudiant>();
		
			for (EtudiantCreateDto etudto : listeCreateDto) {
				etulist.add(convertCreateDtoToEntity(etudto));
			}
			return etulist;
		} else {
			return null;
		}
	}

	@Override
	public List<EtudiantCreateDto> convertListEntityToCreateDto(List<Etudiant> listeEntity) {
		if (listeEntity != null) {
			List<EtudiantCreateDto> etudtolist = new ArrayList<EtudiantCreateDto>();
		
			for (Etudiant e : listeEntity) {

				etudtolist.add(convertEntityToCreateDto(e));
			}
		return etudtolist;
		} else {
			return null;
		}
	}

}
