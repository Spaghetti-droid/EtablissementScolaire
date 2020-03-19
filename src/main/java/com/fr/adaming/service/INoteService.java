package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Note;

public interface INoteService {
	
	public Note create(Note entity);
	
	public boolean update(Note matiere);

	public List<Note> readAll();

	public Note readById(Integer id);

	public boolean existsById(Integer id);

	public boolean deleteById(Integer id);

	

}
