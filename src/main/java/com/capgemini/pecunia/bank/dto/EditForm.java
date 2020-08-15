package com.capgemini.pecunia.bank.dto;

/********************************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is a DTO(Data Transfer Object) class of AccountForm that is used to send
 *                                      the data from Spring Boot to Angular and vice versa.
 *          @version         1.0
 *          Created Date     09-AUG-2020
 ********************************************************************************************************/

public class EditForm {

	private String accountId;

	private String customerName;
	
    private String customerContact;
	
	private String customerAddress;
	
	private String customerCity;
	
	private String customerState;
	
	private String customerCountry;
	
	private String customerZipCode;
	
	public String getAccountId() {
			return accountId;
		}

	 public void setAccountId(String accountId) {
			this.accountId = accountId;
		}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerCity() {
		return customerCity;
	}

	public void setCustomerCity(String customerCity) {
		this.customerCity = customerCity;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public String getCustomerCountry() {
		return customerCountry;
	}

	public void setCustomerCountry(String customerCountry) {
		this.customerCountry = customerCountry;
	}

	public String getCustomerZipCode() {
		return customerZipCode;
	}

	public void setCustomerZipCode(String customerZipCode) {
		this.customerZipCode = customerZipCode;
	}

}
