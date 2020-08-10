package com.capgemini.pecunia.bank.dao;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;

public interface LoanRequestDao {
	public String addLoanDetails(LoanRequest loanRequest);
	
	public Account getAccount(String accountId);
	
	public long countLoansForCustomer(String custID);
	
}
