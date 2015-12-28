package com.neu.project.model;

public class Drug {
	
	private int drugID;
	private String drugname;
    private String period;
    private String code;
    private String produceTime;
    private String brand;
    private float price;
    private int availablity;
    private String drugDescription;
 
    
    
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
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProduceTime() {
		return produceTime;
	}
	public void setProduceTime(String produceTime) {
		this.produceTime = produceTime;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getAvailablity() {
		return availablity;
	}
	public void setAvailablity(int availablity) {
		this.availablity = availablity;
	}
	public String getDrugDescription() {
		return drugDescription;
	}
	public void setDrugDescription(String drugDescription) {
		this.drugDescription = drugDescription;
	}
    
    

}
