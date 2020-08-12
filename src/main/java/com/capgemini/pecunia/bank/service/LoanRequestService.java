package com.capgemini.pecunia.bank.service;

import com.capgemini.pecunia.bank.dto.LoanRequestForm;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;

public interface LoanRequestService {

	public String createLoanRequest(LoanRequestForm req) throws AccountNotFoundException;
}
