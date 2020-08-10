package com.capgemini.pecunia.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.bank.dao.LoanRequestDao;
import com.capgemini.pecunia.bank.dto.LoanRequestForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.util.AccountConstants;

@Transactional
@Service("LoanRequestService")
public class LoanRequestServiceImpl implements LoanRequestService{

	@Autowired
	private LoanRequestDao loanRequestDao;
	
	@Override
	public String createLoanRequest(LoanRequestForm loanRequestForm) throws AccountNotFoundException {
		Account account = loanRequestDao.getAccount(loanRequestForm.getAccountId());
		if(account==null)
			throw new AccountNotFoundException(AccountConstants.INVALID_CUSTOMER);
		long count = loanRequestDao.countLoansForCustomer(loanRequestForm.getAccountId())+AccountConstants.ONE;
		String loanRequestId = loanRequestForm.getAccountId()+AccountConstants.EMPTY+count;
		LoanRequest loanRequest = new LoanRequest();
		loanRequest.setLoanRequestId(loanRequestId);
		loanRequest.setLoanAmount(loanRequestForm.getLoanAmt());
		loanRequest.setCreditScore(loanRequestForm.getCreditScore());
		loanRequest.setLoanTenure(loanRequestForm.getTenure());
		loanRequest.setLoanType(loanRequestForm.getLoanType());
		loanRequest.setRateOfInterest(loanRequest.getRateOfInterest());
		loanRequest.setAccount(account);
		loanRequest.setLoanRequestStatus("Pending");
		loanRequestDao.addLoanDetails(loanRequest);
		return loanRequestId;
	}

}
