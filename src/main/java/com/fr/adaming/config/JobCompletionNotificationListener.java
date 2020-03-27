package com.fr.adaming.config;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

//	@Autowired
//	public JavaMailSender emailSender;

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("job terminé " + new Date());

		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			System.out.println("job success");

			if (!EtudiantItemProcessor.emailsRejetes.isEmpty()) {
				
				String emailList = "\n";

				for (String email : EtudiantItemProcessor.emailsRejetes) {

					emailList += email + "\n";
					
				}

//				SimpleMailMessage message = new SimpleMailMessage();
//				message.setTo("intiformintilyon2020@gmail.com"); // admin
//				message.setSubject("Etudiants rejetés");
//				message.setText("Bonjour, \nLes étudiants suivants existent déjà dans la base de données. Ils n'ont donc pas été ajoutés." + emailList
//						+ "  \n\nCordialement, \nLa Machine");
//				emailSender.send(message);

			}

		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			System.out.println("job echec");
			
//			SimpleMailMessage message = new SimpleMailMessage();
//			message.setTo("intiformintilyon2020@gmail.com"); // admin
//			message.setSubject("Echec pendant l'enregistrement de nouveaux étudiants");
//			message.setText("Bonjour, \nUne erreur est survenue pendant l'enregistrement des nouveaux étudiants dans la base de données. \n\nCordialement, \nLa Machine");
//			emailSender.send(message);
			
		}
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("job debute" + new Date());
	}

}
