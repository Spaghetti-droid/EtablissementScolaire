package com.fr.adaming.config;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.batch.item.ItemProcessor;

import com.fr.adaming.entity.Etudiant;

public class EtudiantItemProcessor implements ItemProcessor<Etudiant, Etudiant> {

	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	@Override
	public Etudiant process(Etudiant item) throws Exception {
		item.setPwd(generatePassword(10));
		
		return item;
	}
	
	private String generatePassword(int lenght) {
		StringBuilder returnValue = new StringBuilder(lenght);
		for(int i = 0; i<lenght;i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
		
	}

}
