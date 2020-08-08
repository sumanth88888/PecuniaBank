
package com.capgemini.pecunia.bank.service;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.bank.dao.TransactionDao;
import com.capgemini.pecunia.bank.dto.Transfer;
import com.capgemini.pecunia.bank.dto.TxnForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Cheque;
import com.capgemini.pecunia.bank.entity.Customer;
import com.capgemini.pecunia.bank.entity.Slip;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.TransactionException;


@javax.transaction.Transactional
@Service("TransactionSer")
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao dao;

	@Override
	public Boolean creditUsingSlip(TxnForm txnform) throws AccountNotFoundException {
		Account toAcc = dao.getAccount(txnform.getAccId());
		if (toAcc == null)
			throw new AccountNotFoundException(walletConstants.INVALID_ACCOUNT);
		toAcc.setBalance(txnform.getAmount() + toAcc.getBalance());

		// CALLING TRANSACTION METHOD
		TxnWithSlip(1547436L, walletConstants.CREDIT, txnform.getAmount(), LocalDate.of(2020, 04, 15), walletConstants.transcation_status, toAcc, 1645473L);

		return true;

	}

	@Override
	public Boolean debitUsingSlip(TxnForm txnform) throws AccountNotFoundException, TransactionException {
		Account fromAcc = dao.getAccount(txnform.getAccId());
		if (fromAcc == null)
			throw new AccountNotFoundException(walletConstants.INVALID_ACCOUNT);

		if (fromAcc.getBalance() < txnform.getAmount())
			throw new TransactionException(walletConstants.INSUFFICIENT_BALANCE);
		fromAcc.setBalance(fromAcc.getBalance() - txnform.getAmount());

		// CALLING TRANSACTION METHOD
		TxnWithSlip(12L,walletConstants.DEBIT, txnform.getAmount(), LocalDate.of(2020, 04, 15),walletConstants.transcation_status, fromAcc, 2L);

		return true;
	}

	@Override
	public Boolean creditUsingCheque(Transfer transfer) throws AccountNotFoundException, TransactionException {
		// creating fromAcc instance
		Account fromAcc = dao.getAccount(transfer.getFromAccId());
		if (fromAcc == null)
			throw new AccountNotFoundException(walletConstants.INVALID_ACCOUNT);
		if (fromAcc.getBalance() < transfer.getAmount())
			throw new TransactionException(walletConstants.INSUFFICIENT_BALANCE);
		fromAcc.setBalance(fromAcc.getBalance() - transfer.getAmount());

		TxnWithCheque(84363L, walletConstants.DEBIT, transfer.getAmount(), LocalDate.of(2020, 04, 12), walletConstants.transcation_status, fromAcc, 9844L,
				"SBI72825", LocalDate.of(2020, 03, 20), transfer.getFromAccId());

		// creating toAcc instance
		Account toAcc = dao.getAccount(transfer.getToAccId());
		if (toAcc == null)
			throw new AccountNotFoundException(walletConstants.INVALID_ACCOUNT);
		toAcc.setBalance(toAcc.getBalance() + transfer.getAmount());

		TxnWithCheque(5543L, walletConstants.CREDIT, transfer.getAmount(), LocalDate.of(2020, 04, 18), walletConstants.transcation_status, fromAcc, 4647L,
				"SBI72825", LocalDate.of(2020, 03, 14), transfer.getToAccId());
		return true;
	}

	@Override
	public Boolean debitUsingCheque(TxnForm txnform) throws AccountNotFoundException, TransactionException {
		Account fromAcc = dao.getAccount(txnform.getAccId());
		if (fromAcc == null)
			throw new AccountNotFoundException(walletConstants.INVALID_ACCOUNT);
		if (fromAcc.getBalance() < txnform.getAmount())
			throw new TransactionException(walletConstants.INSUFFICIENT_BALANCE);

		fromAcc.setBalance(fromAcc.getBalance() - txnform.getAmount());

		TxnWithCheque(20L, walletConstants.DEBIT, txnform.getAmount(), LocalDate.of(2020, 04, 16), walletConstants.transcation_status, fromAcc, 14561L,
				"SBI72827", LocalDate.of(2020, 03, 26), txnform.getAccId());

		return true;
	}

	public Boolean TxnWithSlip(Long transactionId, String transactionType, double transactionAmount,
			LocalDate transactionDate, String transactionStatus, Account account, Long slipId) {
		Slip slip = new Slip();
		slip.setTransactionId(transactionId);
		slip.setTransactionType(transactionType);
		slip.setTransactionAmount(transactionAmount);
		slip.setTransactionDate(LocalDate.now());
		slip.setTransactionStatus(transactionStatus);
		slip.setAccount(account);
		slip.setSlipId(slipId);

		dao.addSlip(slip);
		return true;
	}

	public Boolean TxnWithCheque(Long transactionId, String transactionType, double transactionAmount,
			LocalDate transactionDate, String transactionStatus, Account account, Long chequeId, String ifsc,
			LocalDate chequeIssueDate, String beneficiaryAccountNumber) {
		Cheque cheque = new Cheque();
		cheque.setTransactionId(transactionId);
		cheque.setTransactionType(transactionType);
		cheque.setTransactionAmount(transactionAmount);
		cheque.setTransactionDate(transactionDate);
		cheque.setTransactionStatus(transactionStatus);
		cheque.setAccount(account);
		cheque.setChequeId(chequeId);
		cheque.setIfsc(ifsc);
		cheque.setChequeIssueDate(chequeIssueDate);
		cheque.setBeneficiaryAccountNumber(beneficiaryAccountNumber);

		dao.addCheque(cheque);

		return true;
	}

	public boolean addCust(Customer cust) {
		dao.addCustomer(cust);
		return true;
	}

	public boolean addCheque(Cheque cheque) {
		dao.addCheque(cheque);
		return true;
	}

	@Override
	public Boolean addSlip(Slip slip) {
		dao.addSlip(slip);
		return true;
	}

	@Override
	public boolean addAccount(Account account) {
		dao.addAccount(account);
		return true;
	}

}
