package com.fr.adaming.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Absence;
import com.fr.adaming.entity.Etudiant;
import com.fr.adaming.entity.Note;

@Repository
public interface IEtudiantDao extends JpaRepository<Etudiant, Integer> {
	
	public boolean existsByEmail(String email);
	
	public Etudiant findByEmail(String email);
	
	@Query(value = "select * from note where id_etudiant in (select id from etudiant where email = :email)", nativeQuery = true)
	public List<Note> findNoteByEtudiantEmail(@Param(value = "email") String email);
	
	@Query(value = "select * from absence where id_etudiant in (select id from etudiant where email = :email)", nativeQuery = true)
	public List<Absence> findAbsenceByEtudiantEmail(@Param(value = "email") String email);

}
