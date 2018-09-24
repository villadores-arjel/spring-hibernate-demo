package com.sample.springhibernate.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.springhibernate.model.Patient;

@Repository
@Transactional
public class PatientRepositoryImpl implements PatientRepository 
{
	@Autowired
    private SessionFactory sessionFactory;

	@Override
	public void createPatient(Patient patient) 
	{
		sessionFactory.getCurrentSession().save(patient);

	}

	@Override
	public Patient findPatient(int id) 
	{
		return (Patient) sessionFactory.getCurrentSession().get(Patient.class, id);
	}

	@Override
	public void updatePatient(Patient patient) 
	{
		sessionFactory.getCurrentSession().update(patient);

	}

	@Override
	public void deletePatient(Patient patient) 
	{
		sessionFactory.getCurrentSession().delete(patient);

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Patient> findAllPatients() 
	{
		return sessionFactory.getCurrentSession().createQuery("from Patient").list();
	}

}
