package com.capgemini.pecunia.bank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.LoanMessage;
import com.capgemini.pecunia.bank.dto.LoanRequestForm;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.service.LoanRequestService;
import com.capgemini.pecunia.bank.util.AccountConstants;

@RestController
public class LoanRequestController {
	
	@Autowired
	private LoanRequestService loanRequestService;
	
	@PostMapping("/loanrequest")
	public LoanMessage loanRequest(@RequestBody LoanRequestForm loanRequestForm) throws AccountNotFoundException {
		
		String loanRequestId = loanRequestService.createLoanRequest(loanRequestForm);
		return new LoanMessage(
				AccountConstants.LOAN_REQ_CREATED + AccountConstants.GENERATED_LOAN_REQ + loanRequestId);
	}
}
