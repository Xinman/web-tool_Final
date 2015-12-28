package com.neu.project.model;

import java.io.Serializable;

public class Diagnosis implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int diagnosisID;
	private String paname;
	private int appointID;
	private float respiratoryRate;
	private float heartRate;
	private float sysBloodPressure;
	private float weight;
	private String diseasename;
	private String result;
	private String diagdate;
	
	
	public String getPaname() {
		return paname;
	}
	public void setPaname(String paname) {
		this.paname = paname;
	}
	public int getAppointID() {
		return appointID;
	}
	public void setAppointID(int appointID) {
		this.appointID = appointID;
	}
	public String getDiagdate() {
		return diagdate;
	}
	public void setDiagdate(String diagdate) {
		this.diagdate = diagdate;
	}
	public void setDiagnosisID(int diagnosisID) {
		this.diagnosisID = diagnosisID;
	}
	public int getDiagnosisID() {
		return diagnosisID;
	}
	public void setDiagnoseID(int diagnoseID) {
		this.diagnosisID = diagnoseID;
	}
	public float getRespiratoryRate() {
		return respiratoryRate;
	}
	public void setRespiratoryRate(float respiratoryRate) {
		this.respiratoryRate = respiratoryRate;
	}
	public float getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(float heartRate) {
		this.heartRate = heartRate;
	}
	public float getSysBloodPressure() {
		return sysBloodPressure;
	}
	public void setSysBloodPressure(float sysBloodPressure) {
		this.sysBloodPressure = sysBloodPressure;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getDiseasename() {
		return diseasename;
	}
	public void setDiseasename(String diseasename) {
		this.diseasename = diseasename;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
	

}
