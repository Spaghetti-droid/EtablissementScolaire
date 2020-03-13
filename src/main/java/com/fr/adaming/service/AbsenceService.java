package com.fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dao.IAbsenceDao;
import com.fr.adaming.entity.Absence;

public class AbsenceService implements IAbsenceService {
	
	@Autowired
	private IAbsenceDao dao;

	@Override
	public Absence create(Absence absence) {
		if (!dao.existsById(absence.getId())) {
			return dao.save(absence);
		}
		return null;
	}

	@Override
	public List<Absence> readAll() {
		return dao.findAll();
	}

	@Override
	public Absence readById(Integer id) {
		return dao.findById(id).orElse(null);
	}

	@Override
	public boolean existsById(Integer id) {
		if (dao.existsById(id)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {
		try {
			dao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Absence absence) {
		try {
			if (dao.existsById(absence.getId())) {
			dao.delete(absence);
			return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
