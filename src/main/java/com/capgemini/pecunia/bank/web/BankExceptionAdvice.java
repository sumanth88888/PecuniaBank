package com.capgemini.pecunia.bank.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.pecunia.bank.dto.ErrorMessage;
import com.capgemini.pecunia.bank.exceptions.AccountIdException;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;

@RestControllerAdvice//globel exception handler
public class BankExceptionAdvice {
	@ExceptionHandler({PbankTXNNotFouException.class,AccountIdException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)//http status codes
	public ErrorMessage handleNotFoundException(Exception ex) {
		return new ErrorMessage(ex.getMessage());
		
	}
	
	@ExceptionHandler({DateException.class})
	@ResponseStatus(code = HttpStatus.FORBIDDEN)//http status codes
	public ErrorMessage handleDateNotFoundException(Exception ex) {
		return new ErrorMessage(ex.getMessage());
		
	}
	
	

}
