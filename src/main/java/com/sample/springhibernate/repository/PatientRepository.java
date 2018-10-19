package com.sample.springhibernate.repository;

import java.util.List;

import com.sample.springhibernate.model.Patient;

public interface PatientRepository 
{
	void createPatient(Patient patient);
    Patient findPatient(int id);
    void updatePatient(Patient patient);
    void deletePatient(Patient patient);
    List<Patient> findAllPatients();
    List<Patient> findPatientByName(String name);
    List<Object[]> findAllPatiensAndDoctors();
}
