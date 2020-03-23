package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fr.adaming.entity.Absence;


/**
 * Interface repository pour la class Absence implements JpaRepository<Absence, Integer>
 * @author Gregoire Brebner
 * 
 */
@Repository
public interface IAbsenceDao extends JpaRepository<Absence, Integer> {

}
