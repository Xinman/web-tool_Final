package com.neu.project.model;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;



public class Doctor {

	private int docId;
	private int role_id;
	
	@Size(min=3,message="At Least 3 characters")
	private String docUsername;
	
	@Size(min=6,message="At Least 6 characters")
	private String docPassword;
	
	@Email(message="Invalid Email")
	private String email;
	
	private String type;
    private String description;
    
    @Size(min=6,message="At Least 6 characters")
    private String docCode;
    
   

  
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getDocUsername() {
		return docUsername;
	}
	public void setDocUsername(String docUsername) {
		this.docUsername = docUsername;
	}
	public String getDocPassword() {
		return docPassword;
	}
	public void setDocPassword(String docPassword) {
		this.docPassword = docPassword;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDocCode() {
		return docCode;
	}
	public void setDocCode(String docCode) {
		this.docCode = docCode;
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


    
    
}
