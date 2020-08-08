package com.capgemini.pecunia.bank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bank.dto.Transfer;
import com.cg.bank.dto.TxnForm;
import com.cg.bank.entity.Account;
import com.cg.bank.entity.Customer;
import com.cg.bank.exceptions.AccountNotFoundException;
import com.cg.bank.exceptions.TransactionException;
import com.cg.bank.service.TransactionService;
import com.cg.bank.util.walletConstants;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService service;

	@PostMapping("/creditBySlip")
	public String creditUsingSlip(@RequestBody TxnForm txnform) throws AccountNotFoundException{

		service.creditUsingSlip(txnform);
		return walletConstants.TRANSACTION_SUCCESS;
	}

	@PostMapping("/debitBySlip")
	public String debitUsingSlip(@RequestBody TxnForm txnform) throws AccountNotFoundException,TransactionException{

		service.debitUsingSlip(txnform);
		return walletConstants.TRANSACTION_SUCCESS;
	}

	@PostMapping("/creditByCheque")
	public String creditByCheque(@RequestBody Transfer transfer) throws AccountNotFoundException,TransactionException{

		service.creditUsingCheque(transfer);
		return walletConstants.TRANSACTION_SUCCESS;
	}
	
	@PostMapping("/debitByCheque")
	public String debitByCheque(@RequestBody TxnForm txnform) throws AccountNotFoundException,TransactionException{

		service.debitUsingCheque(txnform);
		return walletConstants.TRANSACTION_SUCCESS;
	}
	
	@PostMapping("/add_account")
	public String addAccount(@RequestBody Account account) {
		service.addAccount(account);		
		return walletConstants.ACCOUNT_CREATED;
	}
	
	@PostMapping("/add_customer")
	public String addCustomer(@RequestBody Customer customer) {
		service.addCust(customer);		
		return walletConstants.ACCOUNT_CREATED;
	}
	
}