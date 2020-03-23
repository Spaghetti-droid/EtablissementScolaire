package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Note;


/**
 * Interface repository pour la class Note implements JpaRepository avec parametres Absence et Integer
 * @author Gregoire Brebner
 * 
 */
@Repository
public interface INoteDao extends JpaRepository<Note, Integer> {

}
