package com.fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.dao.IExamenDao;
import com.fr.adaming.dao.IMatiereDao;
import com.fr.adaming.entity.Examen;

@Service
public class ExamenService extends AbstractService<Examen> {
	
	@Autowired
	private IExamenDao daoExamen;
	
	@Autowired
	private IMatiereDao daoMatiere;
	
	@Override
	public Examen create(Examen exam) {
		
			if(exam == null || exam.getDate()==null||daoExamen.existsById(exam.getId())||exam.getMatiere()==null) {
				return null;
			}
			if(!daoMatiere.existsById(exam.getMatiere().getId())) {
				return null;
			}
			
			return daoExamen.save(exam);
		
	}


	@Override
	public boolean update(Examen exam) {
		
			if (exam == null 
					|| !daoExamen.existsById(exam.getId())
					|| exam.getDate()==null) {
					return false;
			}
			if(!daoMatiere.existsById(exam.getMatiere().getId())) {
				return false;
			}
			daoExamen.save(exam);
			return true;
		
	}

}
