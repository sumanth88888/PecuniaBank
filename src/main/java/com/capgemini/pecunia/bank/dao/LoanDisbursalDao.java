package com.capgemini.pecunia.bank.dao;

import java.util.List;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;

public interface LoanDisbursalDao {
	
	public LoanRequest getLoanRequest(String loanRequestId);
	
	public boolean editAccount(Account account);
	
	public boolean editLoanRequest(LoanRequest loanRequest);
	
	public List<LoanRequest> retrieveDisbursedLoanRequest(String status);
}
