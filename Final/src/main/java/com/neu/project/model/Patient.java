package com.neu.project.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Range;


public class Patient {
	
	private int paId;

    @Size(min=3,message="At Least 3 characters")
	private String paUsername;

    @Size(min=6,message="At Least 6 characters")
	private String papassword;
	private int role_id;


	@Range(min = 1, max = 150,message="Age is  1 ~ 150")
	private int age;
	//private String dob;
	private String gender;

	@Size(min=10,message="At least 10 characters")
	private String ssn;


	@Email(message="Invalid Email")
	private String contactemail;


	@Size(min=10, message="At least 10 numbers")
	@Pattern(regexp="[0-9]+", message="Wrong Phone!")
	private String phone;
	//private Documentation doc;
	//private List<Appointment> appoints;
	//private List<DocFeedback> docFeedbacks;
	//private List<DrugFeedback> drugFeedbacks;
	
	
	public int getPaId() {
		return paId;
	}
	public void setPaId(int paId) {
		this.paId = paId;
	}
	public String getPaUsername() {
		return paUsername;
	}
	public void setPaUsername(String paUsername) {
		this.paUsername = paUsername;
	}
	public String getPapassword() {
		return papassword;
	}
	public void setPapassword(String papassword) {
		this.papassword = papassword;
	}
	
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getContactemail() {
		return contactemail;
	}
	public void setContactemail(String contactemail) {
		this.contactemail = contactemail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/*public Documentation getDoc() {
		return doc;
	}
	public void setDoc(Documentation doc) {
		this.doc = doc;
	}*/
	/*public List<Appointment> getAppoints() {
		return appoints;
	}
	public void setAppoints(List<Appointment> appoints) {
		this.appoints = appoints;
	}
	public List<DocFeedback> getDocFeedbacks() {
		return docFeedbacks;
	}
	public void setDocFeedbacks(List<DocFeedback> docFeedbacks) {
		this.docFeedbacks = docFeedbacks;
	}
	/*public List<DrugFeedback> getDrugFeedbacks() {
		return drugFeedbacks;
	}
	public void setDrugFeedbacks(List<DrugFeedback> drugFeedbacks) {
		this.drugFeedbacks = drugFeedbacks;
	}*/
	

	
	


}
