package com.capgemini.pecunia.bank.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.pecunia.bank.dto.BankMessage;
import com.capgemini.pecunia.bank.dto.ErrorMessage;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.InvalidAccountException;
import com.capgemini.pecunia.bank.exceptions.InvalidCustomerException;
import com.capgemini.pecunia.bank.exceptions.LoanException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;
import com.capgemini.pecunia.bank.util.AccountConstants;

/******************************************************************************************
 *          @author           Rakesh Kuruva
 *          Description       It is a Rest Controller Advice class that globally Handles 
 *                                       that Exception that raised in the Rest Controller.
 *          @version          1.0
 *          Created Date      10-AUG-2020
*******************************************************************************************/

@RestControllerAdvice
public class BankExceptionAdvice {

		Logger logger = LoggerFactory.getLogger(BankExceptionAdvice.class);

		@ExceptionHandler(value = { InvalidAccountException.class, InvalidCustomerException.class, AccountNotFoundException.class,
										PbankTXNNotFouException.class,ValidateException.class })
		@ResponseStatus(code = HttpStatus.NOT_FOUND)
		public BankMessage handleException2(Exception ex) {
			logger.error(ex.getMessage());
			return new BankMessage(ex.getMessage());
		}

		@ExceptionHandler(value = { DataIntegrityViolationException.class, LoanException.class})
		@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = AccountConstants.ACCOUNT_ALREADY_EXISTS)
		public void handleException6(Exception ex) {
			logger.error(ex.getMessage());

		}
		
		
		@ExceptionHandler({DateException.class})
		@ResponseStatus(code = HttpStatus.FORBIDDEN)//http status codes
		public ErrorMessage handleDateNotFoundException(Exception ex) {
			return new ErrorMessage(ex.getMessage());
		}	

}

