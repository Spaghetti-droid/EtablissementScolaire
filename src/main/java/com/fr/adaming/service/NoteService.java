package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.INoteDao;
import com.fr.adaming.entity.Note;

@Service
public class NoteService implements INoteService {

	@Autowired
	private INoteDao dao;

	@Override
	public Note create(Note note) {
		
		if (note == null) {
			return null;
		}
	
		return dao.save(note);
	}

	@Override
	public boolean update(Note note) {
		if (note == null) {
			return false;
		}
		dao.save(note);
		return true;
	}

	@Override
	public List<Note> readAll() {
		return dao.findAll();
	}

	@Override
	public Note readById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Integer id) {
		return dao.existsById(id);
	}

	@Override
	public boolean deleteById(Integer id) {
		dao.deleteById(id);
		return true;
	}

}
