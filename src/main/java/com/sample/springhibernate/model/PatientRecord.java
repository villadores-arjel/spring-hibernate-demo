package com.sample.springhibernate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PATIENT_RECORDS")
public class PatientRecord 
{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="RECORD_ID")
    private int id;
	
	@Column(name="RECORD_TYPE", nullable = false)
	private String recordType;
	
	@Column(name="RECORD_DESCRIPTION", nullable = false)
	private String recordDescription;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "P_PATIENT_ID", nullable = false)
//	@OneToOne(cascade=CascadeType.ALL)
	private Patient patient;

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public String getRecordDescription() {
		return recordDescription;
	}

	public void setRecordDescription(String recordDescription) {
		this.recordDescription = recordDescription;
	}

}
