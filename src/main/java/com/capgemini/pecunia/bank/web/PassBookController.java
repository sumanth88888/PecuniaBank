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
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
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
	 * @Author Name  : venkata sai kumar
	 * Method Name   : passbookUpdate
	 * Description   : getting transactions of given user's UserId 
	 * Return Type   : List(List of Transactions)
	 * Parameter     : String USerId
	 * @throws       : ValidateException, PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@CrossOrigin
	@GetMapping("/getBankTransactions/{userId}")
	public List<Transaction> passbookUpdate(@PathVariable("userId") String userId)
			throws ValidateException, PbankTXNNotFouException {
		return service.passbookUpdate(userId);
	}

	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : accountSummary
	 * Description   : getting transactions of given user's UserId between given dates
	 * Return Type   : List(List of Transactions)
	 * Parameter     : String UserId,LocalDate fromDt, LocalDate toDate
	 * @throws       : PbankTXNNotFouException, ValidateException, DateException
	 * 
	 **********************************************************************************/
	@CrossOrigin
	@PostMapping("/getBankTxnsForDateRange")
	public List<Transaction> accountSummary(@RequestBody ReportForm form) throws PbankTXNNotFouException, ValidateException, DateException {

		return service.accountSummary(form.getUserId(), form.getFromDt(), form.getToDate());
	}

	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : getBankTransactions
	 * Description   : getting transactions of given user's UserId with limited transactions
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String UserId,int txns
	 * @throws       : ValidateException, PbankTXNNotFouException
	 * 
	 **********************************************************************************/
	@CrossOrigin
	@GetMapping("/getBankTxnsLimit/{userId}/{txns}")
	public List<Transaction> getBankTransaction(@PathVariable("userId") String userId,@PathVariable("txns")int txns ) throws ValidateException, PbankTXNNotFouException {

		return service.getBankTransactions(userId, txns);
	}
	
	
	/**********************************************************************************
	 * 
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
	public List<Transaction> lastPassbookUpdate(@RequestBody ReportForm form) throws PbankTXNNotFouException, ValidateException, DateException {

		return service.lastPassbookUpdate(form.getUserId(), form.getFromDt());
	}
	
}
