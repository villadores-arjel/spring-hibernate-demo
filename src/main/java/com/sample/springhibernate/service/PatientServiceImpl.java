package com.sample.springhibernate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.springhibernate.model.Doctor;
import com.sample.springhibernate.model.Patient;
import com.sample.springhibernate.repository.PatientRepository;

@Service("patientService")
@Transactional
public class PatientServiceImpl implements PatientService 
{
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void createPatient(Patient patient) 
	{		
		patientRepository.createPatient(patient);
	}
	
	@Override
	public Patient findPatient(int id)
	{
		return patientRepository.findPatient(id);
	}

	@Override
	public void updatePatient(Patient patient) 
	{
		patientRepository.updatePatient(patient);
//		patient.setFirstName("Jel0");
	}

	@Override
	public void deletePatient(Patient patient) 
	{
		patientRepository.deletePatient(patient);
		
	}

	@Override
	public List<Patient> findAllPatients() 
	{
		return patientRepository.findAllPatients();
	}

	@Override
	public List<Patient> findPatientByName(String name) 
	{
		return patientRepository.findPatientByName(name);
	}

	@Override
	public List<Doctor> findDoctorByPatient(int id) 
	{
//		List<Patient> patients = findPatientByName(name);
		Patient patient = findPatient(id);
		List<Doctor> doctors = new ArrayList<Doctor>();
		doctors.addAll(patient.getDoctors());
//		for(Patient patient:patients)
//		{
//			doctors.addAll(patient.getDoctors());
//		}
		return doctors;
	}

}
