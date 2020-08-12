package com.capgemini.pecunia.bank.dao;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;

public interface LoanRequestDao {
	public boolean addLoanDetails(LoanRequest loanRequest);
	
	public Account getAccount(String accountId);
	
	public long countLoansForCustomer(String custID);
	
}
