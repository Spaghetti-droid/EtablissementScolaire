package com.fr.adaming.config;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;


public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("job terminé " + new Date());
		
		if(jobExecution.getStatus()==BatchStatus.COMPLETED) {
			System.out.println("job success");
		} else if (jobExecution.getStatus()==BatchStatus.FAILED) {
			System.out.println("job echec");
		}
	}
	
	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("job debute" + new Date());
	}

}
