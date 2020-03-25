package com.fr.adaming.config;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.fr.adaming.dao.IEtudiantDao;
import com.fr.adaming.entity.Etudiant;

public class EtudiantItemProcessor implements ItemProcessor<Etudiant, Etudiant> {

	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	
	@Autowired
	private IEtudiantDao eDao;
	
    @Autowired
    public JavaMailSender emailSender;
	
	@Override
	public Etudiant process(Etudiant etudiant) throws Exception {
		
		if(!eDao.existsByEmail(etudiant.getEmail())) {
			
			etudiant.setPwd(generatePassword(10));
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(etudiant.getEmail());
			message.setSubject("Integration dans la base de données");
			message.setText("Bonjour " + etudiant.getPrenom() + " " + etudiant.getNom() + ", \n Vous êtes maintenant inscit dans notre base de données. \n\n Cordialement, \n La Machine");
			emailSender.send(message);
		
		} else {
			
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo("intiformintilyon2020@gmail.com"); //admin
			message.setSubject("Etudiant rejeté");
			message.setText("Bonjour, \n L'étudiant avec l'email " + etudiant.getEmail() + " existe déjà dans la base de données. Il n'a donc pas été ajouté. \n\n Cordialement, \n La Machine");
			emailSender.send(message);
			
			etudiant = null;
			
		}
		
		return etudiant;
	}
	
	private String generatePassword(int lenght) {
		StringBuilder returnValue = new StringBuilder(lenght);
		for(int i = 0; i<lenght;i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
		
	}

}
