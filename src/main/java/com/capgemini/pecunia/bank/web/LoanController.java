package com.capgemini.pecunia.bank.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.BankMessage;
import com.capgemini.pecunia.bank.dto.LoanDisbursalForm;
import com.capgemini.pecunia.bank.dto.LoanRequestForm;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;
import com.capgemini.pecunia.bank.service.LoanService;
import com.capgemini.pecunia.bank.util.LoanConstants;

@CrossOrigin
@RestController
public class LoanController {

	@Autowired
	private LoanService loanService;
	
	@GetMapping("/viewloanrequest/{status}")
	public List<LoanRequest> retrieveDisbursedLoanRequest(@PathVariable("status") String status) throws LoanException {
		return loanService.retrievedisbursedLoanRequest(status);
	}
	
	@PostMapping("/disburseloanrequest")
	public BankMessage disburseLoanRequest(@RequestBody LoanDisbursalForm loanDisbursalForm) throws AccountNotFoundException {
		loanService.disburseLoanRequest(loanDisbursalForm);
		System.out.println("LoanForm"+loanDisbursalForm.getLoanRequestId());
		BankMessage loanMessage = new BankMessage(LoanConstants.LOAN_PROCESSED);
		return loanMessage;
	}
	
	@PostMapping("/loanrequest")
	public BankMessage loanRequest(@RequestBody LoanRequestForm loanRequestForm) throws AccountNotFoundException {
		
		String loanRequestId = loanService.createLoanRequest(loanRequestForm);
		return new BankMessage(
				LoanConstants.LOAN_REQ_CREATED + LoanConstants.GENERATED_LOAN_REQ + loanRequestId);
	}
}
