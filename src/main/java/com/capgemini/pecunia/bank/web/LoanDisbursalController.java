package com.capgemini.pecunia.bank.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.LoanMessage;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;
import com.capgemini.pecunia.bank.service.LoanDisbursalService;
import com.capgemini.pecunia.bank.util.AccountConstants;

@RestController
public class LoanDisbursalController {

	@Autowired
	private LoanDisbursalService loanDisbursalService;
	
	@GetMapping("/viewloanrequest/{status}")
	public List<LoanRequest> retrieveDisbursedLoanRequest(@PathVariable("status") String status) throws LoanException {
		return loanDisbursalService.retrievedisbursedLoanRequest(status);
	}
	
	@PostMapping("/disburseloanrequest/{loanRequestId}")
	public LoanMessage disburseLoanRequest(@PathVariable("loanRequestId") String loanRequestId) throws AccountNotFoundException {
		loanDisbursalService.disburseLoanRequest(loanRequestId);
		LoanMessage loanMessage = new LoanMessage(AccountConstants.LOAN_PROCESSED);
		return loanMessage;
	}
}