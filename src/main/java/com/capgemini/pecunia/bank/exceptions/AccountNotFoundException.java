package com.capgemini.pecunia.bank.exceptions;

public class AccountNotFoundException extends Exception{

	public AccountNotFoundException() {
		super();
		
	}

	public AccountNotFoundException(String message) {
		super(message);
		
	}
}
