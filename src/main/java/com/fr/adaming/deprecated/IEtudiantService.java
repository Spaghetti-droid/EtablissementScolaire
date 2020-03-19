package com.fr.adaming.deprecated;

import java.util.List;

import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Note;
import com.fr.adaming.service.IService;

public interface IEtudiantService extends IService<Etudiant>{

		public Etudiant readByEmail(String email);

		public List<Note> readNoteByEtudiantEmail(String email);
		
		public List<Absence> readAbsenceByEtudiantEmail(String email);
	}

