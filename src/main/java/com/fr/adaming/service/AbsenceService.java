package com.fr.adaming.service;

import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Absence;

/**
 * <b>Couche Service des absences qui étend la classe Abstract Service </b>
 * @author Grégoire Brebner
 *
 */
@Service
public class AbsenceService extends AbstractService<Absence> {


	@Override
	public Absence create(Absence absence) {
		if (absence != null) {
			if (!dao.existsById(absence.getId()) && absence.getDateDebut() != null) {
				return dao.save(absence);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}


	@Override
	public boolean update(Absence absence) {
		if (absence == null || !dao.existsById(absence.getId())) {
			return false;
		} else {
			dao.save(absence);
			return true;
		}
	}

}
