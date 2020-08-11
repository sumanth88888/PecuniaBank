package com.capgemini.pecunia.bank.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/****************************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is an Entity class of Customer that is mapped with the Oracle DataBase
 *                                       of table bank_customer.
 *          @version         1.0
 *          Created Date     05-AUG-2020
 ****************************************************************************************************/

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="bank_customer")
public class Customer {
	
	@Id
	@Column(name = "aadhar_number", length =16)
	private String aadharNumber;
	
	@Column(name = "cust_name", length = 25)
	private String cname;
	
	@Column(name = "cust_contact", length = 10)
	private String contact;
	
	@Column(name = "cust_pan_card", length = 10)
	private String PAN;
	
	@Column(name = "cust_dob")
	private LocalDate DOB;
	
	@Column(name="cust_address", length=100)
	private String address;
	
	@Column(name = "cust_state", length = 25)
	private String state;

	@Column(name = "cust_city", length = 25)
	private String city;
	
	@Column(name = "cust_country", length = 25)
	private String country;
	
	@Column(name = "cust_zipcode")
	private long zipcode;
	
	@Column(name = "cust_gender", length = 10)
	private String gender;
	
	public String getAadharNumber() {
		return aadharNumber;
	}
	
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	
	public String getCname() {
		return cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getPAN() {
		return PAN;
	}
	
	public void setPAN(String pAN) {
		PAN = pAN;
	}
	
	public LocalDate getDOB() {
		return DOB;
	}
	
	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public long getZipcode() {
		return zipcode;
	}
	
	public void setZipcode(long zipcode) {
		this.zipcode = zipcode;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	

}
