package com.capgemini.pecunia.bank.exceptions;

/****************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is a customized Exception class of Customer.
 *          @version         1.0
 *          Created Date     07-AUG-2020
*****************************************************************************************/

public class InvalidCustomerException extends Exception{

	public InvalidCustomerException() {
		super();
	}
	
	public InvalidCustomerException(String message) {
		super(message);
	}
	
	
}
