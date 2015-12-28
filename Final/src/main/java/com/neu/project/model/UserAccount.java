package com.neu.project.model;

public class UserAccount {
	
	private int uaID;
	private String username;
	private String password;
	private int role_id;
	private String email;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUaID() {
		return uaID;
	}
	public void setUaID(int uaID) {
		this.uaID = uaID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	

}
