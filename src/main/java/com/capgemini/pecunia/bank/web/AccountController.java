package com.capgemini.pecunia.bank.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.BankForm;
import com.capgemini.pecunia.bank.dto.BankMessage;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.exceptions.InvalidAccountException;
import com.capgemini.pecunia.bank.exceptions.InvalidCustomerException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;
import com.capgemini.pecunia.bank.service.BankService;
import com.capgemini.pecunia.bank.util.AccountConstants;

/**************************************************************************************
 *          @author           Rakesh Kuruva
 *          Description       It is a Rest Controller class that provides the suitable
 *                                       Account Management methods for the given 
 *                                       matching URL and returns response in different
 *                                       types of data objects.
 *          @version          1.0
 *          Created Date      10-AUG-2020
***************************************************************************************/

@RestController
public class AccountController {

	@Autowired
	public BankService service;
	
	@CrossOrigin
	@PostMapping("/addaccount")
	public BankMessage addAccount(@RequestBody BankForm bankForm, HttpServletRequest request) throws InvalidAccountException, InvalidCustomerException, ValidateException{
		
		try {
			String accId = service.addAccount(bankForm);
			return new BankMessage(
					AccountConstants.ACCOUNT_CREATED + AccountConstants.GENERATED_ACCOUNT + accId);
		
	} catch (DataIntegrityViolationException ex) {
		throw new InvalidAccountException(AccountConstants.ACCOUNT_ALREADY_EXISTS);
	}
	}
	
    
	@CrossOrigin
	@PutMapping("/editaccount")
	public BankMessage editAccount(@RequestBody Account account, HttpServletRequest request) {
		
		service.editAccount(account);
		return new BankMessage(AccountConstants.ACCOUNT_EDITED);
		
	}
	


	@CrossOrigin
	@GetMapping("/viewaccountbyaccid/{accId}")
	public Account viewAccount(@PathVariable("accId") String accId, HttpServletRequest request)
																				throws InvalidAccountException {
	      
			Account account = service.viewAccount(accId);
			return account;
		 }
	
	
	@CrossOrigin
	@PutMapping("/deleteaccount/{accId}")
	public BankMessage deleteAccount(@PathVariable("accId") String accId, HttpServletRequest request) throws InvalidAccountException {
		
		service.deleteAccount(accId);
		return new BankMessage(AccountConstants.ACCOUNT_REMOVED);
		
		}
	
	@CrossOrigin
	@GetMapping("/string")
	public String getStr() {
		return "Rakesh";
	}
	
}