package com.sample.springhibernate.service;

import java.util.List;

import com.sample.springhibernate.model.Doctor;
import com.sample.springhibernate.model.Patient;

public interface PatientService 
{
	void createPatient(Patient patient);
	Patient findPatient(int id);
    void updatePatient(Patient patient);
    void deletePatient(Patient patient);
    List<Patient> findAllPatients();
    List<Patient> findPatientByName(String name);
    List<Doctor> findDoctorByPatient(int id);
    List<Object[]> findAllPatiensAndDoctors();
}
