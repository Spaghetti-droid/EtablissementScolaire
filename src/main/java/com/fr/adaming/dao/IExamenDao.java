package com.fr.adaming.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Examen;


/**
 * Interface repository pour la class Examen implements JpaRepository avec parametres Absence et Integer
 * @author Gregoire Brebner
 * 
 */
@Repository
public interface IExamenDao extends JpaRepository<Examen, Integer> {

}
