package com.fr.adaming.service;

import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Classe;

/**
 * <p>Couche Service des absences qui étend la classe Abstract Service </p>
 * @author Jeanne-Marie
 * @author Léa
 *
 */
@Service
public class ClasseService extends AbstractService<Classe> {


	@Override
	public Classe create(Classe classe) {

		if (classe == null || dao.existsById(classe.getId()) || classe.getNom() == null) {
			return null;
		}
		return dao.save(classe);

	}

	

	@Override
	public boolean update(Classe classe) {

		if (classe == null || !dao.existsById(classe.getId()) || classe.getNom()==null) {
			return false;
		}
		
		dao.save(classe);
		return true;

	}

}
