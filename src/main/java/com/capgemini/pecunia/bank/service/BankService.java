package com.capgemini.pecunia.bank.service;

import com.capgemini.pecunia.bank.dto.BankForm;
import com.capgemini.pecunia.bank.dto.EditForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.exceptions.InvalidAccountException;
import com.capgemini.pecunia.bank.exceptions.InvalidCustomerException;

public interface BankService {

	public boolean editAccount(EditForm edtfrm) throws InvalidAccountException;
	
	public boolean deleteAccount(String accountId) throws InvalidAccountException;

	public Account viewAccount(String accId) throws InvalidAccountException;
	
	public String addAccount(BankForm bankForm) throws InvalidCustomerException;

}
