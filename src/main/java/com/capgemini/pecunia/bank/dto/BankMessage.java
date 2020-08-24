package com.capgemini.pecunia.bank.dto;


/**********************************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is a DTO(Data Transfer Object) class of AccountMessage that is used to send
 *                                      the data from Spring Boot to Angular and vice versa.
 *          @version         1.0
 *          Created Date     09-AUG-2020
 **********************************************************************************************************/

public class BankMessage {

	private String message;

	public BankMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
