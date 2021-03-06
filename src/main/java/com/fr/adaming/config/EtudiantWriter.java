package com.fr.adaming.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.fr.adaming.dao.IEtudiantDao;
import com.fr.adaming.entity.Etudiant;

public class EtudiantWriter implements ItemWriter<Etudiant> {
	
	
	@Autowired
	private IEtudiantDao dao;

	@Override
	public void write(List<? extends Etudiant> items) throws Exception {
		for(Etudiant etudiant : items) {
			if(etudiant != null) {
				dao.save(etudiant);
			}
		}
			
		
	}

}
