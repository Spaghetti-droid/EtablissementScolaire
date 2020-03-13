package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Examen;

public interface IExamenDao extends JpaRepository<Examen, Integer> {

}
