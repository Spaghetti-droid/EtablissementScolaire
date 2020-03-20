package com.fr.adaming.service;

import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Note;

@Service
public class NoteService extends AbstractService<Note> {


	@Override
	public Note create(Note note) {
		
		if (note == null
				|| note.getValeur()< 0
				|| note.getEtudiant()==null
				||note.getExamen()==null) {
			return null;
		}
	
		return dao.save(note);
	}

	@Override
	public boolean update(Note note) {
		if (note == null ||!existsById(note.getId())
				|| note.getEtudiant()==null || note.getExamen()==null) {
			return false;
		}
		dao.save(note);
		return true;
	}

	

}
