package com.capgemini.pecunia.bank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.BankMessage;
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
	public BankMessage creditUsingSlip(@RequestBody TxnForm txnform) throws AccountNotFoundException {

		service.creditUsingSlip(txnform);
		BankMessage bankmessage=new BankMessage(walletConstants.TRANSACTION_SUCCESS);
		return bankmessage;
	}

	@PostMapping("/debitBySlip")
	public BankMessage debitUsingSlip(@RequestBody TxnForm txnform) throws AccountNotFoundException, TransactionException {

		service.debitUsingSlip(txnform);
		BankMessage bankmessage=new BankMessage(walletConstants.TRANSACTION_SUCCESS);

		return bankmessage;
	}

	@PostMapping("/creditByCheque")
	public BankMessage creditByCheque(@RequestBody Transfer transfer) throws AccountNotFoundException, TransactionException {

		service.creditUsingCheque(transfer);
		BankMessage bankmessage=new BankMessage(walletConstants.TRANSACTION_SUCCESS);

		return bankmessage;
	}

	@PostMapping("/debitByCheque")
	public BankMessage debitByCheque(@RequestBody TxnForm txnform) throws AccountNotFoundException, TransactionException {

		service.debitUsingCheque(txnform);
		BankMessage bankmessage=new BankMessage(walletConstants.TRANSACTION_SUCCESS);

		return bankmessage;
	}

	@PostMapping("/add_account")
	public BankMessage addAccount(@RequestBody Account account) {
		service.addAccount(account);
		BankMessage bankmessage=new BankMessage(walletConstants.ACCOUNT_CREATED);

		return bankmessage;
	}

	@PostMapping("/add_customer")
	public BankMessage addCustomer(@RequestBody Customer customer) {
		service.addCust(customer);
		BankMessage bankmessage=new BankMessage(walletConstants.ACCOUNT_CREATED);

		return bankmessage;
	}

}