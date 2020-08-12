package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.bank.dao.BankDao;
import com.capgemini.pecunia.bank.dto.BankForm;
import com.capgemini.pecunia.bank.dto.EditForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Customer;
import com.capgemini.pecunia.bank.exceptions.InvalidAccountException;
import com.capgemini.pecunia.bank.exceptions.InvalidCustomerException;
import com.capgemini.pecunia.bank.util.AccountConstants;

/************************************************************************************
 *          @author           Rakesh
 *          Description       It is a service class that interacts with 
 *                                     BankService Interface.
 *          @version          1.0
 *          Created Date      07-AUG-2020
************************************************************************************/

@Service
@Transactional
public class BankServiceImpl implements BankService{

	@Autowired
	private BankDao dao;
	
	@Override
	public String editAccount(EditForm edtFrm) throws InvalidAccountException {
		
        Account acc = dao.viewAccount(edtFrm.getAccountId());
		  
		  if(acc == null)
				throw new InvalidAccountException(AccountConstants.INVALID_ACCOUNT);
		  
		  Customer cust = new Customer();
		  cust.setAadharNumber(acc.getCust().getAadharNumber());
		  cust.setCname(edtFrm.getCustomerName());
		  cust.setDOB(acc.getCust().getDOB());
		  cust.setPAN(acc.getCust().getPAN());
		  cust.setContact(edtFrm.getCustomerContact());
		  cust.setAddress(edtFrm.getCustomerAddress());
		  cust.setCity(edtFrm.getCustomerCity());
		  cust.setState(edtFrm.getCustomerState());
		  cust.setCountry(edtFrm.getCustomerCountry());
		  cust.setZipcode(edtFrm.getCustomerZipCode());
		  cust.setGender(acc.getCust().getGender());
		  
		  dao.editCustomer(cust);
		  return AccountConstants.ACCOUNT_EDITED;
		  
		
	}

	@Override
	public boolean deleteAccount(String accountId) throws InvalidAccountException {
		
		  Account acc = dao.viewAccount(accountId);
		  
		  if(acc == null)
				throw new InvalidAccountException(AccountConstants.INVALID_ACCOUNT);
		  
		  Customer cust = acc.getCust();
		  dao.deleteAccount(acc);
		  return dao.deleteCustomer(cust);   
	
	}

	@Override
	public Account viewAccount(String accId) throws InvalidAccountException {
		Account acc = dao.viewAccount(accId);
		
		if(acc == null)
			throw new InvalidAccountException(AccountConstants.INVALID_ACCOUNT);
		return dao.viewAccount(accId);
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public String addAccount(BankForm bankForm) throws InvalidCustomerException {
		
		Customer cust = new Customer();
		cust.setAadharNumber(bankForm.getCustomerAadhar());
		cust.setCname(bankForm.getCustomerName());
		cust.setDOB(bankForm.getCustomerDob());
		cust.setContact(bankForm.getCustomerContact());
		cust.setPAN(bankForm.getCustomerPan());
		cust.setGender(bankForm.getCustomerGender());
		cust.setAddress(bankForm.getCustomerAddress());
		cust.setCity(bankForm.getCustomerCity());
		cust.setState(bankForm.getCustomerState());
		cust.setCountry(bankForm.getCustomerCountry());
		cust.setZipcode(bankForm.getCustomerZipCode());
		     dao.addCustomer(cust);
			
		Account account = new Account();
		Customer customer = dao.viewCustomer(bankForm.getCustomerAadhar());
			if (customer == null)
				throw new InvalidCustomerException(AccountConstants.CUSTOMER_NOT_FOUND);
			
			String id = LocalDate.now().toString().substring(2,4)
						+LocalDate.now().toString().substring(5,7)
						+LocalDate.now().toString().substring(8)
						+bankForm.getCustomerAadhar().substring(10);
			
			account.setAccountId(id);
			account.setCust(customer);
			account.setBalance(bankForm.getAcc_bal());
			account.setIfsc(bankForm.getIfscCode());
			account.setBranch_id(bankForm.getBranchCode());
			dao.addAccount(account);
		    return id;
	}

	

}
