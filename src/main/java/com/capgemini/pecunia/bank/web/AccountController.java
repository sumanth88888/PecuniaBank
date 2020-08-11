package com.capgemini.pecunia.bank.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.AccountMessage;
import com.capgemini.pecunia.bank.dto.BankForm;
import com.capgemini.pecunia.bank.dto.EditForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.exceptions.InvalidAccountException;
import com.capgemini.pecunia.bank.exceptions.InvalidCustomerException;
import com.capgemini.pecunia.bank.service.BankService;
import com.capgemini.pecunia.bank.util.AccountConstants;

@RestController
public class AccountController {

	@Autowired
	public BankService service;
	
	@CrossOrigin
	@PostMapping("/addaccount")
	public AccountMessage addAccount(@RequestBody BankForm bankForm, HttpServletRequest request) throws InvalidAccountException, InvalidCustomerException{
		
		try {
			String accId = service.addAccount(bankForm);
			return new AccountMessage(
					AccountConstants.ACCOUNT_CREATED + AccountConstants.GENERATED_ACCOUNT + accId);
		
	} catch (DataIntegrityViolationException ex) {
		throw new InvalidAccountException(AccountConstants.ACCOUNT_ALREADY_EXISTS);
	}
	}
	
    
	@CrossOrigin
	@PutMapping("/editaccount")
	public boolean editAccount(@RequestBody EditForm edtFrm, HttpServletRequest request) throws InvalidAccountException {
		
		return service.editAccount(edtFrm);
		
	}
	


	@CrossOrigin
	@DeleteMapping("/viewaccountbyaccid/{accId}")
	public Account viewAccount(@PathVariable("accId") String accId, HttpServletRequest request)
			throws InvalidAccountException {
	      
			Account account = service.viewAccount(accId);
			return account;
		 }
	
	
	@CrossOrigin
	@PutMapping("/deleteaccount/{accId}")
	public AccountMessage deleteAccount(@PathVariable("accId") String accId, HttpServletRequest request) throws InvalidAccountException {
		
		service.deleteAccount(accId);
		return new AccountMessage(AccountConstants.ACCOUNT_REMOVED);
		
		}
}
