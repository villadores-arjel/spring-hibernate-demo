package com.sample.springhibernate.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sample.springhibernate.model.Doctor;
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
		//return sessionFactory.getCurrentSession().createQuery("from Patient").list();
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Patient> criteria = builder.createQuery(Patient.class);
		Root<Patient> patientRoot = criteria.from(Patient.class); 
		criteria.select(patientRoot);
		
		return session.createQuery(criteria).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Patient> findPatientByName(String name) 
	{
//		String query = "from Patient where firstName = ?";
//		String query = "from Patient where lastName = :name";
//		Query sessionQuery = sessionFactory.getCurrentSession().createQuery(query);
//		sessionQuery.setString(0, name);
//		sessionQuery.setString("name", name);
//		return sessionQuery.list();
		
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Patient> criteria = builder.createQuery(Patient.class);
		Root<Patient> patientRoot = criteria.from(Patient.class);
		criteria.select(patientRoot);
//		criteria.where(builder.or(
//						builder.equal(patientRoot.get("lastName"), name),
//				 		builder.equal(patientRoot.get("firstName"), name)
//				 		));
		Predicate equalToFirstName = builder.like(patientRoot.get("firstName"), name);
		Predicate equalToLastName = builder.like(patientRoot.get("lastName"), name);
		
		criteria.where(builder.or(equalToFirstName, equalToLastName));
		
		return session.createQuery(criteria).getResultList();
		
	}

	@Override
	public List<Object[]> findAllPatiensAndDoctors() {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteria = builder.createQuery(Object[].class);
//		CriteriaQuery<Patient> criteria = builder.createQuery(Patient.class);
		
		Root<Patient> patientRoot = criteria.from(Patient.class);
//		Root<Doctor> doctorRoot = criteria.from(Doctor.class);
	
//		criteria.multiselect(patientRoot, doctorRoot); //returns a cross join of patients and doctors
//		criteria.where(builder.equal(patientRoot.get("doctors"), doctorRoot.get("id")));

		Join<Patient, Doctor> doctorJoin = patientRoot.join("doctors");
		criteria.multiselect(patientRoot, doctorJoin);
		
//		criteria.distinct(true);
		criteria.orderBy(builder.asc(doctorJoin.get("firstName")));
		
		return session.createQuery(criteria).getResultList();
	}
	
	

}
