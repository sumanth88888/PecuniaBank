package com.capgemini.pecunia.bank.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.bank.dao.BankDao;
import com.capgemini.pecunia.bank.dto.ReportForm;
import com.capgemini.pecunia.bank.dto.TxnForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.TxnsNumberException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;
import com.capgemini.pecunia.bank.util.BankConstants;
import com.capgemini.pecunia.bank.util.WalletConstants;

@Service
@Transactional
public class PassBookServiceImpl implements PassBookService {

	@Autowired
	private BankDao bankDao;


	/**********************************************************************************
	 * 
	 * @throws AccountNotFoundException 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : passbookUpdate
	 * Description   : getting transactions of given user's UserId 
	 * Return Type   : List(List of Transactions)
	 * Parameter     : String USerId
	 * @throws       : ValidateException,PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> userTransactions(ReportForm rform)
			throws  PbankTXNNotFouException, AccountNotFoundException {
		Account userAcc = bankDao.getAccount(rform.getUserId());
		if (userAcc == null)
			throw new AccountNotFoundException(BankConstants.INVALID_ACCOUNT);
		List<Transaction> txnList =  bankDao.passbookUpdate(rform.getUserId());
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException(BankConstants.NO_TXN_AVAILABLE);
		txnList = txnList.stream().sorted((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate())).collect(Collectors.toList());
		return txnList;
	}

	
	/**********************************************************************************
	 * 
	 * @throws AccountNotFoundException 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : accountSummary
	 * Description   : getting transactions of given user's UserId between given dates
	 * Return Type   : List(List of Transactions)
	 * Parameters    : String UserId,LocalDate fromDt, LocalDate toDate
	 * @throws       : PbankTXNNotFouException,ValidateException,DateException
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> transactionsDtRange(ReportForm rform)
			throws PbankTXNNotFouException,DateException, AccountNotFoundException{
		Account userAcc = bankDao.getAccount(rform.getUserId());
		if (userAcc == null)
			throw new AccountNotFoundException(BankConstants.INVALID_ACCOUNT);
		if (rform.getToDate().compareTo(rform.getFromDt()) < 0)
			throw new DateException(BankConstants.TODATE_GETERTHEN_FROMDATE);
		List<Transaction> txnList =  bankDao.accountSummary(rform.getUserId(), rform.getFromDt(), rform.getToDate());
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException(BankConstants.NO_TXN_AVAILABLE);
		txnList.sort((t1, t2)->t2.getTransactionDate().compareTo(t1.getTransactionDate()));
		return txnList;
		
	}

	
	/**********************************************************************************
	 * 
	 * @throws AccountNotFoundException 
	 * @throws TxnsNumberException 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : getBankTransactions
	 * Description   : getting transactions of given user's UserId with limited transactions
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String UserId,int txns
	 * @throws       : ValidateException, PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> limitedTransactions(ReportForm rform) throws PbankTXNNotFouException, AccountNotFoundException, TxnsNumberException {
		Account userAcc = bankDao.getAccount(rform.getUserId());
		if (userAcc == null)
			throw new AccountNotFoundException(BankConstants.INVALID_ACCOUNT);
		if(rform.getTxns()<1)
			throw new TxnsNumberException(BankConstants.NUMBER_GRETERTHEN_ONE);
		List<Transaction> txnList =  bankDao.getBankTransactions(rform.getUserId(), rform.getTxns());
		
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException(BankConstants.NO_TXN_AVAILABLE);
		return txnList;
	}
	
	/**********************************************************************************
	 * 
	 * @throws AccountNotFoundException 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : lastPassbookUpdate
	 * Description   : getting transactions of given user's UserId from Last Updated Date
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String UserId,LocalDate fromDt
	 * @throws       : ValidateException, PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> lastPassbookUpdate(ReportForm rform)throws  PbankTXNNotFouException, AccountNotFoundException {
		Account userAcc = bankDao.getAccount(rform.getUserId());
		if (userAcc == null)
			throw new AccountNotFoundException(BankConstants.INVALID_ACCOUNT);
		List<Transaction> txnList =  bankDao.accountSummary(rform.getUserId(), rform.getFromDt(), LocalDate.now());
		if(txnList.isEmpty())
			throw new PbankTXNNotFouException(BankConstants.NO_TXN_AVAILABLE);
		return txnList;
	}

	
}