package com.capgemini.pecunia.bank.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.pecunia.bank.dto.LoanMessage;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;


@RestControllerAdvice
public class LoanExceptionAdvice {
	
	 Logger logger = LoggerFactory.getLogger(AccountNotFoundException.class);
	 
	@ExceptionHandler({AccountNotFoundException.class})
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public LoanMessage handleNotFoundException(AccountNotFoundException exception) {
		logger.error(exception.getMessage());
		return new LoanMessage(exception.getMessage());
		
	}
	
	@ExceptionHandler({LoanException.class})
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public LoanMessage handleBadREquestException(LoanException exception) {
		logger.error(exception.getMessage());
		return new LoanMessage(exception.getMessage());
		
	}


}
