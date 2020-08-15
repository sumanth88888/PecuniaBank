package com.capgemini.pecunia.bank.service;

import java.util.List;

import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;

public interface LoanDisbursalService {

	
	public boolean disburseLoanRequest(String loanRequestId, String status) throws AccountNotFoundException;
	
	public List<LoanRequest> retrievedisbursedLoanRequest(String status) throws LoanException;
	
	
}
