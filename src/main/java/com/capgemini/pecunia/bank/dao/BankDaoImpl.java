package com.capgemini.pecunia.bank.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Cheque;
import com.capgemini.pecunia.bank.entity.Customer;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.entity.Slip;
import com.capgemini.pecunia.bank.entity.Transaction;


/****************************************************************************************
 *          @author          Rakesh Kuruva
 *          Description      It is a DAO implementation class that interacts with
 *                                      Oracle DataBase for Account,Customer,Transaction
 *                                      and Loan Management.
 *          @version         1.0
 *          Created Date     05-AUG-2020
*****************************************************************************************/


@Repository
public class BankDaoImpl implements BankDao{
	
	@PersistenceContext
	private EntityManager em;


	@Override
	public Account viewAccount(String accId) {
		
		return em.find(Account.class, accId);
	}

	@Override
	public boolean deleteAccount(Account account) {
		em.remove(account);
		return true;
	}

	@Override
	public boolean addCustomer(Customer cust) {
		em.persist(cust);
		return true;
	}

	@Override
	public boolean addAccount(Account account) {
		em.persist(account);
		return true;
	}

	@Override
	public boolean deleteCustomer(Customer cust) {
		em.remove(cust);
		return false;
	}

	@Override
	public Customer viewCustomer(String aadhar_number) {
		
		return em.find(Customer.class, aadhar_number);
	}

	@Override
	public boolean editCustomer(Customer customer) {
		
		em.merge(customer);
		return true;
	}
	
	@Override
	public LoanRequest getLoanRequest(String loanRequestId) {
		return em.find(LoanRequest.class, loanRequestId);
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
		em.merge(account);
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
		em.merge(loanRequest);
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
		TypedQuery<LoanRequest> query = em.createQuery(jpql, LoanRequest.class);
		query.setParameter("status", loanStatus);
		return query.getResultList();
	}
	
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : addLoanDetails
	 * Description   : This method adds loanRequest into the database
	 * @param  		 : loanRequest
	 * @return 	     : true if loanRequest is added into database
	 * 
	 ******************************************************************************/
	
	@Override
	public boolean addLoanDetails(LoanRequest loanRequest) {
		em.persist(loanRequest);
		return true;
	}
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : getAccount
	 * Description   : This method gets the account for the given accountId
	 * @param  		 : accountId
	 * @return 	     : Account instance
	 * 
	 ******************************************************************************/
	
	@Override
	public Account getAccount(String accountId) {
		return em.find(Account.class, accountId);
	}
	
	/*****************************************************************************
	 * 
	 * @Author Name  : sai sumanth
	 * Method Name   : countLoansForCustomer
	 * Description   : This method gets the number of previous loans for customer
	 * @param  		 : accontId
	 * @return 	     : long
	 * 
	 ******************************************************************************/
	
	@Override
	public long countLoansForCustomer(String accountId) {
		String jpql = "select count(request.loanRequestId) from LoanRequest request inner join request.account account where account.accountId=:accountId";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("accountId", accountId);
		return query.getSingleResult();
	}
	
	

	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : passbookUpdate
	 * Description   : getting transactions of given user's UserId 
	 * Return Type   : List(List of Transactions)
	 * Parameter 1   : String UserId
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> passbookUpdate(String userId) {
		String jpql = "from Transaction txns inner join fetch txns.account acc " 
	                  + " where acc.accountId=:userid";
		TypedQuery<Transaction> query = em.createQuery(jpql, Transaction.class);
		query.setParameter("userid", userId);
		return query.getResultList();
	}

	
	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : accountSummary
	 * Description   : getting transactions of given user's UserId between given dates
	 * Return Type   : List(List of Transactions)
	 * Parameters    : String userId, LocalDate fromDt, LocalDate toDate
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> accountSummary(String userId, LocalDate fromDt, LocalDate toDate) {
		String jpql = "from Transaction txns inner join fetch txns.account acc "
				+ "where acc.accountId=:userid and txns.transactionDate between :fromdt and :todt";
		TypedQuery<Transaction> query = em.createQuery(jpql, Transaction.class);
		query.setParameter("userid", userId);
		query.setParameter("fromdt", fromDt);
		query.setParameter("todt", toDate);
		return query.getResultList();
	}


	/**********************************************************************************
	 * 
	 * @Author Name  : venkata sai kumar
	 * Method Name   : getBankTransactions
	 * Description   : getting transactions of given user's UserId with limited transactions
	 * Return Type   : List(List of Transactions)
	 * Parameters    : String UserId, int txns
	 * 
	 **********************************************************************************/
	@Override
	public List<Transaction> getBankTransactions(String userId, int txns) {
		String jpql = "from Transaction txns inner join fetch txns.account acc"
				+ " where acc.accountId=:userid order by txns.transactionDate desc";
		TypedQuery<Transaction> query = em.createQuery(jpql, Transaction.class);
		query.setFirstResult(1);
		query.setMaxResults(txns);
		query.setParameter("userid", userId);
		return query.getResultList();
	}

	
	@Override
	public boolean addSlip(Slip slip) {
		em.merge(slip);
		return true;
	}
	
	
	@Override
	public boolean addCheque(Cheque cheque) {
		em.persist(cheque);
		return true;
	}

	

	

}