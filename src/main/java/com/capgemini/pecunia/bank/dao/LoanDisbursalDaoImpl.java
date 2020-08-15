package com.capgemini.pecunia.bank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.LoanRequest;

@Repository
public class LoanDisbursalDaoImpl implements LoanDisbursalDao{

	@PersistenceContext
	private EntityManager entityManager;
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : getLoanRequest
	 * Description   : This method gets the loan  request for the loanRequestId
	 * @param  		 : loanrequestId
	 * @return 	     : LoanRequest
	 * 
	 ******************************************************************************/
	
	
	@Override
	public LoanRequest getLoanRequest(String loanRequestId) {
		return entityManager.find(LoanRequest.class, loanRequestId);
	}

	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : editAccount
	 * Description   : This method edits the account instance 
	 * @param  		 : Account
	 * @return 	     : boolean
	 * 
	 ******************************************************************************/
	
	
	@Override
	public boolean editAccount(Account account) {
		entityManager.merge(account);
		return true;
	}

	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : editLoanRequest
	 * Description   : This method stores the edited loanRequest in database
	 * @param  		 : loanRequest
	 * @return 	     : boolean
	 * 
	 ******************************************************************************/
	
	
	@Override
	public boolean editLoanRequest(LoanRequest loanRequest) {
		entityManager.merge(loanRequest);
		return true;
	}
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : retrieveDisbursedLoanRequest
	 * Description   : This method gets the loan  requests 
	 * @param  		 : loanStatus
	 * @return 	     : List<LoanRequest>
	 * 
	 ******************************************************************************/
	

	@Override
	public List<LoanRequest> retrieveDisbursedLoanRequest(String loanStatus) {
		String jpql = "from LoanRequest loanRequest where loanRequest.loanRequestStatus=:status";
		TypedQuery<LoanRequest> query = entityManager.createQuery(jpql, LoanRequest.class);
		query.setParameter("status", loanStatus);
		return query.getResultList();
	}
	
	
	
}
