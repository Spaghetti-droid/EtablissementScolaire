package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Note;

@Repository
public interface INoteDao extends JpaRepository<Note, Integer> {

}
