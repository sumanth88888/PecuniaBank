package com.capgemini.pecunia.bank.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.bank.dto.BankMessage;
import com.capgemini.pecunia.bank.dto.Transfer;
import com.capgemini.pecunia.bank.dto.TxnForm;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.TransactionException;
import com.capgemini.pecunia.bank.service.TransactionService;
import com.capgemini.pecunia.bank.util.walletConstants;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService service;

	
	
	
	/**********************************************************************************
     * Method              - DebitUsingSlip
     *Description          - To debit balance using slip
     ** parameter- TxnForm - Txnform object to  debit using slip
	 *@RequestBody -Txnform   -Request body that to debit using slip via txnform
     *Created By            - Kandala Lovaraju                      
 
    *********************************************************************************/

	@PostMapping("/debitBySlip")
	public BankMessage debitUsingSlip(@RequestBody TxnForm txnform) throws AccountNotFoundException, TransactionException {

		service.debitUsingSlip(txnform);
		BankMessage bankmessage=new BankMessage(walletConstants.TRANSACTION_SUCCESS);

		return bankmessage;
	}

	
	

	/**********************************************************************************
     * Method              - debitUsingCheque
     *Description          - To debit balance using Cheque
     ** parameter- TxnForm - Txnform object to  debit using Cheque
	 *@RequestBody -Txnform   -Request body that to debit using Cheque via txnform
     *Created By            - Kandala Lovaraju                     
 
    *********************************************************************************/

	@PostMapping("/debitByCheque")
	public BankMessage debitByCheque(@RequestBody TxnForm txnform) throws AccountNotFoundException, TransactionException {

		service.debitUsingCheque(txnform);
		BankMessage bankmessage=new BankMessage(walletConstants.TRANSACTION_SUCCESS);

		return bankmessage;
	}
	
	/**********************************************************************************
     * Method              - creditUsingSlip
     *Description          - To credit balance using slip
     ** parameter- TxnForm - Txnform object to add a credit using slip
	 *@RequestBody -Txnform   -Request body that to credit using slip via txnform
     *Created By            - Satyam Singh                        
 
    *********************************************************************************/
	
	@PostMapping("/creditBySlip")
	public BankMessage creditUsingSlip(@RequestBody TxnForm txnform) throws AccountNotFoundException {

		service.creditUsingSlip(txnform);
		BankMessage bankmessage=new BankMessage(walletConstants.TRANSACTION_SUCCESS);
		return bankmessage;
	}
	
	/**********************************************************************************
     * Method              - creditUsingCheque
     *Description          - To credit balance using Cheque
     ** parameter- Transfer - Transfer object to add a credit using Cheque
	 *@RequestBody -Transfer   -Request body that to credit using Cheque via Transfer 
     *Created By            - Satyam Singh                        
 
    *********************************************************************************/
	
	
	@PostMapping("/creditByCheque")
	public BankMessage creditByCheque(@RequestBody Transfer transfer) throws AccountNotFoundException, TransactionException {

		service.creditUsingCheque(transfer);
		BankMessage bankmessage=new BankMessage(walletConstants.TRANSACTION_SUCCESS);

		return bankmessage;
	}
	

}