package com.sample.springhibernate;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.sample.springhibernate.config.AppConfig;
import com.sample.springhibernate.model.Doctor;
import com.sample.springhibernate.model.Patient;
import com.sample.springhibernate.model.PatientRecord;
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
		samplePatient.setFirstName("Jel");
		samplePatient.setMidName("V");
		samplePatient.setLastName("Villadores");
		
		PatientRecord record1 = new PatientRecord();
		record1.setRecordType("Blood Type");
		record1.setRecordDescription("Blood extraction record");
		record1.setPatient(samplePatient);
		
		PatientRecord record2 = new PatientRecord();
		record2.setRecordType("Urine Type");
		record2.setRecordDescription("Urine extraction record");
		record2.setPatient(samplePatient);
		
		Doctor doctor1 = new Doctor();
		doctor1.setFirstName("Doc 1");
		doctor1.setMidName("Doc 1");
		doctor1.setLastName("LN Doc1");
		doctor1.getPatients().add(samplePatient);
		
		Doctor doctor2 = new Doctor();
		doctor2.setFirstName("Doc 2");
		doctor2.setMidName("Doc 2");
		doctor2.setLastName("LN Doc2");
		doctor2.getPatients().add(samplePatient);
		
//		samplePatient.setRecord(record);
		samplePatient.getRecords().add(record1);
		samplePatient.getRecords().add(record2);
		
		samplePatient.getDoctors().add(doctor1);
		samplePatient.getDoctors().add(doctor2);
		
		patientService.createPatient(samplePatient);
//		
//		Patient getPatient = patientService.findPatient(2);
//		System.out.println(getPatient.getFirstName());
//		
//		getPatient.setLastName("Villa");
//		patientService.updatePatient(getPatient);
//		System.out.println(getPatient.getFirstName());
		
//		patientService.deletePatient(getPatient);
		
//		List<Patient> patients = patientService.findPatientByName("Villadores");
//		for(Patient patient:patients)
//		{
//			List<Doctor> doctors = (List<Doctor>) patient.getDoctors();
//			for(Doctor doctor:doctors)
//			{
//				System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
//			}
//		}
		
		List<Patient> patients = patientService.findPatientByName("Villadores");
		for(Patient patient:patients)
		{
			List<Doctor> doctors = patientService.findDoctorByPatient(patient.getId());
			for(Doctor doctor:doctors)
			{
				System.out.println(patient.getFirstName()+" | "+doctor.getFirstName()+" "+doctor.getLastName());
			}
		}
		
//		List<Doctor> doctors = patientService.findDoctorByPatient("Villadores");
//		for(Doctor doctor:doctors)
//		{
//			System.out.println(doctor.getFirstName()+" "+doctor.getLastName());
//		}

	}

}
