package com.neu.project.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Medication implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int medID;
	private String paname;
	private String medDate;
	private int appointID;
	private int drugID;
	private String drugname;
	//private ArrayList<Drug> drugs;
	private float price;
	private int quantity;
	private float total;
	

	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public String getMedDate() {
		return medDate;
	}
	public void setMedDate(String medDate) {
		this.medDate = medDate;
	}
	public int getAppointID() {
		return appointID;
	}
	public void setAppointID(int appointID) {
		this.appointID = appointID;
	}
	public int getMedID() {
		return medID;
	}
	public void setMedID(int medID) {
		this.medID = medID;
	}
	public String getPaname() {
		return paname;
	}
	public void setPaname(String paname) {
		this.paname = paname;
	}
	public int getDrugID() {
		return drugID;
	}
	public void setDrugID(int drugID) {
		this.drugID = drugID;
	}
	public String getDrugname() {
		return drugname;
	}
	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
