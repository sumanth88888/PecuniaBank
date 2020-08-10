package com.capgemini.pecunia.bank.service;

import com.capgemini.pecunia.bank.dto.LoanRequestForm;

public interface LoanRequestService {

	public String createLoanRequest(LoanRequestForm loanRequestForm);
	
}
