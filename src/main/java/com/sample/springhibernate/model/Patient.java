package com.sample.springhibernate.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PATIENTS")
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
 
	@Column(name="FIRST_NAME", nullable = false)
	private String firstName;
	
	@Column(name="LAST_NAME", nullable = false)
	private String lastName;
	
	@Column(name="MIDDLE_NAME")
	private String midName;
	
	@ManyToMany(cascade = CascadeType.ALL, mappedBy="patients",  fetch = FetchType.LAZY)
	private Collection<Doctor> doctors = new ArrayList<Doctor>();
	
//	@OneToOne(cascade=CascadeType.ALL)
//	@JoinColumn(name="P_RECORD_ID")
//	private PatientRecord record;
//	
//	public PatientRecord getRecord() {
//		return record;
//	}
//
//	public void setRecord(PatientRecord record) {
//		this.record = record;
//	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy="patient",  fetch = FetchType.LAZY)
//	@JoinTable(name="PATIENT_RECORD", joinColumns=@JoinColumn(name="PATIENT_ID"), inverseJoinColumns=@JoinColumn(name="RECORD_ID"))
	private Collection<PatientRecord> records = new ArrayList<PatientRecord>();

	public Collection<PatientRecord> getRecords() {
		return records;
	}

	public void setRecords(Collection<PatientRecord> records) {
		this.records = records;
	}
	
	public Collection<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(Collection<Doctor> doctors) {
		this.doctors = doctors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

}
