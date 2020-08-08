package com.capgemini.pecunia.bank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.Transfer;
import com.capgemini.pecunia.bank.dto.TxnForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Customer;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.TransactionException;
import com.capgemini.pecunia.bank.service.TransactionService;
import com.capgemini.pecunia.bank.util.walletConstants;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService service;

	@PostMapping("/creditBySlip")
	public String creditUsingSlip(@RequestBody TxnForm txnform) throws AccountNotFoundException {

		service.creditUsingSlip(txnform);
		return walletConstants.TRANSACTION_SUCCESS;
	}

	@PostMapping("/debitBySlip")
	public String debitUsingSlip(@RequestBody TxnForm txnform) throws AccountNotFoundException, TransactionException {

		service.debitUsingSlip(txnform);
		return walletConstants.TRANSACTION_SUCCESS;
	}

	@PostMapping("/creditByCheque")
	public String creditByCheque(@RequestBody Transfer transfer) throws AccountNotFoundException, TransactionException {

		service.creditUsingCheque(transfer);
		return walletConstants.TRANSACTION_SUCCESS;
	}

	@PostMapping("/debitByCheque")
	public String debitByCheque(@RequestBody TxnForm txnform) throws AccountNotFoundException, TransactionException {

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