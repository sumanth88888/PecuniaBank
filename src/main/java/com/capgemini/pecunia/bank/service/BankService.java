package com.capgemini.pecunia.bank.service;

import com.capgemini.pecunia.bank.dto.BankForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.exceptions.InvalidAccountException;
import com.capgemini.pecunia.bank.exceptions.InvalidCustomerException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;


/********************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is a service interface that contains method declarations
 *                                      which are implemented in BankServiceImpl.java
 *          @version         1.0
 *          Created Date     07-AUG-2020
 ********************************************************************************************/

public interface BankService {

	public String editAccount(Account account);
	
	public boolean deleteAccount(String accId) throws InvalidAccountException;

	public Account viewAccount(String accId) throws InvalidAccountException;
	
	public String addAccount(BankForm bankForm) throws InvalidCustomerException, ValidateException;

}