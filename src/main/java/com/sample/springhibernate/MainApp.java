package com.sample.springhibernate;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.sample.springhibernate.config.AppConfig;
import com.sample.springhibernate.model.Patient;
import com.sample.springhibernate.service.PatientService;

public class MainApp 
{
	
	public static void main(String[] args) 
	{
		@SuppressWarnings("resource")
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class);
		PatientService patientService = (PatientService) context.getBean("patientService");
		
		Patient samplePatient = new Patient();
		samplePatient.setFirstName("Jelo");
		samplePatient.setMidName("V");
		samplePatient.setLastName("Villadores");
		
		patientService.createPatient(samplePatient);
		
		Patient getPatient = patientService.findPatient(2);
//		System.out.println(getPatient.getFirstName());
//		
		getPatient.setFirstName("Jell");
		patientService.updatePatient(getPatient);
//		System.out.println(getPatient.getFirstName());
		
//		patientService.deletePatient(getPatient);
		
		List<Patient> patients= patientService.findAllPatients();
		for(Patient patient:patients)
		{
			System.out.println(patient.getFirstName());
		}

	}

}
