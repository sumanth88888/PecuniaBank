package com.capgemini.pecunia.bank.exceptions;

public class LoanException extends Exception{

	public LoanException() {
		super();
	}
	
	public LoanException(String message) {
		super(message);
	}
}
