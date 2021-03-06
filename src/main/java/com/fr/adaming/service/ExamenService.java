package com.fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.entity.Examen;

/**
 * <p>Couche Service des Examen qui étend la classe Abstract Service </p>
 * @author Grégoire 
 * @author Léa
 *
 */
@Service
public class ExamenService extends AbstractService<Examen> {
	
	
	
	@Autowired
	private IMatiereDao daoMatiere;
	
	@Override
	public Examen create(Examen exam) {
		
			if(exam == null || exam.getDate()==null||dao.existsById(exam.getId())||exam.getMatiere()==null) {
				return null;
			}
			if(!daoMatiere.existsById(exam.getMatiere().getId())) {
				return null;
			}
			
			return dao.save(exam);
		
	}


	@Override
	public boolean update(Examen exam) {
		
			if (exam == null 
					|| !dao.existsById(exam.getId())
					|| exam.getDate()==null) {
					return false;
			}
			if(!daoMatiere.existsById(exam.getMatiere().getId())) {
				return false;
			}
			dao.save(exam);
			return true;
		
	}

}
