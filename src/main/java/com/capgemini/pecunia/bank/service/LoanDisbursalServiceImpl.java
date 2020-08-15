package com.capgemini.pecunia.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.bank.dao.LoanDisbursalDao;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;
import com.capgemini.pecunia.bank.util.AccountConstants;

@Service("LoanDisbursalService")
public class LoanDisbursalServiceImpl implements LoanDisbursalService{

	@Autowired
	private LoanDisbursalDao loanDisbursalDao;
	
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : disburseLoanRequest
	 * Description   : This method accepts or rejects the loan request for the loanRequestId
	 * @param  		 : loanRequestId
	 * @return 	     : boolean
	 * 
	 ******************************************************************************/
	
	@Transactional
	@Override
	public boolean disburseLoanRequest(String loanRequestId, String option) throws AccountNotFoundException {
		LoanRequest loanRequest = loanDisbursalDao.getLoanRequest(loanRequestId);
		Account account = loanRequest.getAccount();
		if(account == null) {
			throw new AccountNotFoundException( AccountConstants.INVALID_CUSTOMER );
		}
		
		if(option.contentEquals(AccountConstants.ACCEPT)) {
			if(loanRequest.getCreditScore() > AccountConstants.CREDIT_SCORE) {
				
				loanRequest.setLoanRequestStatus(AccountConstants.ACCEPTED);
				account.setBalance(account.getBalance() + loanRequest.getLoanAmount());
				loanDisbursalDao.editAccount(account);
				loanDisbursalDao.editLoanRequest(loanRequest);
				return true;
			}else {
				loanRequest.setLoanRequestStatus(AccountConstants.REJECTED);
				loanDisbursalDao.editLoanRequest(loanRequest);
				return false;
			}
		}else {
			loanRequest.setLoanRequestStatus(AccountConstants.REJECTED);
			return false;
		}
		
	}
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : retrievedisbursedLoanRequest
	 * Description   : This method gets the loanRequest for the given status
	 * @param  		 : status
	 * @return 	     : List<LoanRequest>
	 * 
	 ******************************************************************************/
	
	@Override
	public List<LoanRequest> retrievedisbursedLoanRequest(String status) throws LoanException{
		List<LoanRequest> loanRequestList = loanDisbursalDao.retrieveDisbursedLoanRequest(status);
		if(loanRequestList.isEmpty()) {
			throw new LoanException(AccountConstants.NO_LOAN);
		}else {
			return loanRequestList;
		}
		
		
	}
	
	
}
