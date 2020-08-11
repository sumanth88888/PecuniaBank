package com.capgemini.pecunia.bank.exceptions;

/****************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is a customized Exception class of AccountException.
 *          @version         1.0
 *          Created Date     07-AUG-2020
*****************************************************************************************/

public class InvalidAccountException extends Exception{

	public InvalidAccountException() {
		super();
		
	}
	
	public InvalidAccountException(String message) {
		super(message);
		
	}

	
}
