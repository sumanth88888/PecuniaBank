package com.capgemini.pecunia.bank.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.ReportForm;
import com.capgemini.pecunia.bank.entity.Transaction;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.TxnsNumberException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;
import com.capgemini.pecunia.bank.service.PassBookService;

/**********************************************************************************
 * 
 * @Author Name  : venkata sai kumar
 * Description   : Dispatcherservlet maps the request to handler method for viewing passbook
 * 
 **********************************************************************************/

@RestController
public class PassBookController {

	@Autowired
	private PassBookService service;

	/**********************************************************************************
	 * 
	 * @throws AccountNotFoundException 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : userTransactions
	 * Description   : getting transactions of given user's UserId 
	 * Return Type   : List(List of Transactions)
	 * Parameter     : String USerId
	 * @throws       : ValidateException, PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@CrossOrigin
	@PostMapping("/getBankTransactions")
	public List<Transaction> userTransactions(@RequestBody ReportForm rform)
			throws ValidateException, PbankTXNNotFouException, AccountNotFoundException {
		return service.userTransactions(rform);
	}

	
	/**********************************************************************************
	 * 
	 * @throws AccountNotFoundException 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : transactionsDtRang
	 * Description   : getting transactions of given user's UserId between given dates
	 * Return Type   : List(List of Transactions)
	 * Parameter     : String UserId,LocalDate fromDt, LocalDate toDate
	 * @throws       : PbankTXNNotFouException, ValidateException, DateException
	 * 
	 **********************************************************************************/
	@CrossOrigin
	@PostMapping("/getBankTxnsForDateRange")
	public List<Transaction> transactionsDtRang(@RequestBody ReportForm rform) throws PbankTXNNotFouException, ValidateException, DateException, AccountNotFoundException {

		return service.transactionsDtRange(rform);
	}

	/**********************************************************************************
	 * 
	 * @throws AccountNotFoundException 
	 * @throws TxnsNumberException 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : limitedTransactions
	 * Description   : getting transactions of given user's UserId with limited transactions
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String UserId,int txns
	 * @throws       : ValidateException, PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@CrossOrigin
	@PostMapping("/getBankTxnsLimit")
	public List<Transaction> limitedTransactions(@RequestBody ReportForm rform)  throws ValidateException, PbankTXNNotFouException, AccountNotFoundException, TxnsNumberException {

		return service.limitedTransactions(rform);
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
	@CrossOrigin
	@PostMapping("/getBankTxnsFormLastUpdate")
	public List<Transaction> lastPassbookUpdate(@RequestBody ReportForm rform) throws PbankTXNNotFouException, ValidateException, DateException, AccountNotFoundException {

		return service.lastPassbookUpdate(rform);
	}
	
}