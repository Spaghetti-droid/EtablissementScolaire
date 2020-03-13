package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Etudiant;

@Repository
public interface IEtudiantDao extends JpaRepository<Etudiant, Integer> {

}
