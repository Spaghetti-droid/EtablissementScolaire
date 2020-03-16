package com.fr.adaming.dto;

import javax.persistence.ManyToOne;

import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Examen;

import lombok.Data;

@Data
public class NoteUpdateDto {

	private int id;
	private int value;
	private String nometudiant;

	private int idexamen;

}
