package com.capgemini.pecunia.bank.service;

import java.util.List;

import com.capgemini.pecunia.bank.dto.LoanDisbursalForm;
import com.capgemini.pecunia.bank.dto.LoanRequestForm;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;

public interface LoanService {

public boolean disburseLoanRequest(LoanDisbursalForm loanDisbursalForm) throws AccountNotFoundException;
	
	public List<LoanRequest> retrievedisbursedLoanRequest(String status) throws LoanException;
	
	public String createLoanRequest(LoanRequestForm req) throws AccountNotFoundException;
}
