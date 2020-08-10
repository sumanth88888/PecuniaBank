package com.capgemini.pecunia.bank.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.pecunia.bank.entity.LoanRequest;

@Qualifier(value = "repo")
public interface LoanRepository extends JpaRepository<LoanRequest, String>{

	@Query("from LoanRequest loanRequest where loanRequest.loanRequestStatus = ?1")
	public List<LoanRequest> retrieveDisburseLoanRequest(String status);
	
	
	
}
