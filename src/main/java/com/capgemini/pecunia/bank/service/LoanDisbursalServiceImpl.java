package com.capgemini.pecunia.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.bank.dao.LoanDisbursalDao;
import com.capgemini.pecunia.bank.dao.LoanRepository;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;
import com.capgemini.pecunia.bank.util.AccountConstants;

@Service("LoanDisbursalService")
public class LoanDisbursalServiceImpl implements LoanDisbursalService{

	@Autowired
	private LoanDisbursalDao loanDisbursalDao;
	
	@Autowired
	@Qualifier(value = "repo")
	private LoanRepository loanRepository;
	
	@Transactional
	@Override
	public boolean disburseLoanRequest(String loanRequestId) throws AccountNotFoundException {
		LoanRequest loanRequest = loanDisbursalDao.getLoanRequest(loanRequestId);
		Account account = loanRequest.getAccount();
		if(account == null) {
			throw new AccountNotFoundException(AccountConstants.INVALID_CUSTOMER);
		}
		
		if(loanRequest.getCreditScore()>670) {
			loanRequest.setLoanRequestStatus(AccountConstants.ACCEPTED);
			account.setBalance(account.getBalance()+loanRequest.getLoanAmount());
			loanDisbursalDao.editAccount(account);
			loanDisbursalDao.editLoanRequest(loanRequest);
			return true;
		}else {
			loanRequest.setLoanRequestStatus(AccountConstants.REJECTED);
			loanDisbursalDao.editLoanRequest(loanRequest);
			return false;
		}
		
	}
	
	@Override
	public List<LoanRequest> retrievedisbursedLoanRequest(String status) throws LoanException{
//		List<LoanRequest> loanRequestList = loanDisbursalDao.retrieveDisbursedLoanRequest(status);
//		if(loanRequestList.isEmpty()) {
//			throw new LoanException(AccountConstants.NO_LOAN);
//		}else {
//			return loanRequestList;
//		}
		
		if(loanRepository.retrieveDisburseLoanRequest(status).isEmpty()) {
			throw new LoanException(AccountConstants.NO_LOAN);
		}else {
			return loanRepository.retrieveDisburseLoanRequest(status);
		}
	}
	
	
}
