package com.capgemini.pecunia.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.bank.dao.BankDao;
import com.capgemini.pecunia.bank.dto.LoanDisbursalForm;
import com.capgemini.pecunia.bank.dto.LoanRequestForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;
import com.capgemini.pecunia.bank.util.LoanConstants;

@Service
@Transactional
public class LoanServiceImpl implements LoanService{
	
	@Autowired
	private BankDao bankDao;
	
	
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
	public boolean disburseLoanRequest(LoanDisbursalForm loanDisbursalForm) throws AccountNotFoundException {
		LoanRequest loanRequest = bankDao.getLoanRequest(loanDisbursalForm.getLoanRequestId());
		Account account = loanRequest.getAccount();
		if(account == null) {
			throw new AccountNotFoundException( LoanConstants.INVALID_CUSTOMER );
		}
		
		if(loanDisbursalForm.getOption().contentEquals(LoanConstants.ACCEPT)) {
			if(loanRequest.getCreditScore() > LoanConstants.CREDIT_SCORE) {
				
				loanRequest.setLoanRequestStatus(LoanConstants.ACCEPTED);
				account.setBalance(account.getBalance() + loanRequest.getLoanAmount());
				bankDao.editAccount(account);
				bankDao.editLoanRequest(loanRequest);
				return true;
			}else {
				loanRequest.setLoanRequestStatus(LoanConstants.REJECTED);
				bankDao.editLoanRequest(loanRequest);
				return false;
			}
		}else {
			loanRequest.setLoanRequestStatus(LoanConstants.REJECTED);
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
		List<LoanRequest> loanRequestList = bankDao.retrieveDisbursedLoanRequest(status);
		if(loanRequestList.isEmpty()) {
			throw new LoanException(LoanConstants.NO_LOAN);
		}else {
			return loanRequestList;
		}
		
		
	}
	
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : createLoanRequest
	 * Description   : This method adds a loan request for given user details
	 * @param  		 : LoanRequestForm
	 * @return 	     : String
	 * 
	 ******************************************************************************/
	@Override
	public String createLoanRequest(LoanRequestForm loanRequestForm) throws AccountNotFoundException {
		Account account = bankDao.getAccount(loanRequestForm.getAccountId());
		if(account==null)
			throw new AccountNotFoundException(LoanConstants.INVALID_CUSTOMER);
		long count = bankDao.countLoansForCustomer(loanRequestForm.getAccountId()) + LoanConstants.ONE;
		String loanRequestId = loanRequestForm.getAccountId() + LoanConstants.EMPTY +count;
		LoanRequest loanRequest = new LoanRequest();
		loanRequest.setLoanRequestId(loanRequestId);
		loanRequest.setLoanAmount(loanRequestForm.getLoanAmt());
		loanRequest.setCreditScore(loanRequestForm.getCreditScore());
		loanRequest.setLoanTenure(loanRequestForm.getTenure());
		loanRequest.setLoanType(loanRequestForm.getLoanType());
		loanRequest.setRateOfInterest(loanRequest.getRateOfInterest());
		loanRequest.setAccount(account);
		loanRequest.setLoanRequestStatus(LoanConstants.PENDING);
		bankDao.addLoanDetails(loanRequest);
		return loanRequestId;
	}
	

}
