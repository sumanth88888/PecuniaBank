package com.capgemini.pecunia.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.capgemini.pecunia.bank.dao.BankDao;
import com.capgemini.pecunia.bank.dto.LoanDisbursalForm;
import com.capgemini.pecunia.bank.dto.LoanRequestForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Customer;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;
import com.capgemini.pecunia.bank.service.LoanService;

@DisplayName(value = "TestCases")
@RunWith(MockitoJUnitRunner.class)
public class PecuniaBankApplicationTests {

	@Mock
	private LoanService loanService;
	
	@Mock
	private LoanRequestForm loanRequestForm;
	
	@Mock
	private BankDao bankDao;
	
	@Mock
	private static LoanDisbursalForm loanDisbursalForm;
	
	@Mock
	private LoanRequest loanRequest;
	
	private static List<LoanRequest> list = new ArrayList<>();
	
	@Mock
	private static Account  account;
	
	@Mock
	private static Customer  customer;
	
	@BeforeEach
	public void init() {
		customer = new Customer("459115023130", "sumanth", "8146089998", "APSBC78945", LocalDate.of(1999, 04, 13), "4/42", "ap", "kadapa", "india", "516003", "male");
		account = new Account("8146089998", customer, 500.0, "savings", "45", "SBIN0045");
		loanRequestForm = new LoanRequestForm(500000.0, 24, "8146089998", "house loan", 400000.0, 5, 890);
		loanDisbursalForm.setLoanRequestId("81460899982");
		loanDisbursalForm.setOption("Accept");
	}
	
	@Test
	@DisplayName(value = "Adding loan request")
	public void addLoanRequestTest() throws AccountNotFoundException {
		when(loanService.createLoanRequest(loanRequestForm)).thenReturn("LoanRequestSuccessful");
		assertEquals("LoanRequestSuccessful", loanService.createLoanRequest(loanRequestForm));
	}
	
	@Test
	@DisplayName(value = "adding loan request , exception thrown")
	public void addLoanRequestExceptionThrownTest()throws AccountNotFoundException {
		when(loanService.createLoanRequest(loanRequestForm)).thenThrow(AccountNotFoundException.class);
		assertThrows(AccountNotFoundException.class, ()->loanService.createLoanRequest(loanRequestForm));
	}
	
	@Test
	@DisplayName(value = "loan request successful")
	public void abc() throws AccountNotFoundException {
		when(bankDao.getAccount("8146089998")).thenReturn(account);
		assertEquals("LoanRequestSuccessful", loanService.createLoanRequest(loanRequestForm));
	}
	
	@Test
	@DisplayName(value = "disbursed loan request")
	public void disbursedLoanRequest() throws AccountNotFoundException {
		when(loanService.disburseLoanRequest(loanDisbursalForm)).thenReturn(true);
		assertEquals(true, loanService.disburseLoanRequest(loanDisbursalForm));
	}
	
	@Test
	@DisplayName(value = "retrieving loan request")
	public void retrievingLoanRequest() throws LoanException {
		when(loanService.retrievedisbursedLoanRequest("pending")).thenReturn(list);
		assertEquals(list, loanService.retrievedisbursedLoanRequest("pending"));
	}
	
	@Test
	@DisplayName(value = "loan disbursed failed")
	public void loanDisbursedFailed() throws AccountNotFoundException {
		when(bankDao.getLoanRequest("81460899982")).thenReturn(loanRequest);
		when(loanRequest.getAccount()).thenReturn(account);
		assertEquals(false, loanService.disburseLoanRequest(loanDisbursalForm));
	}
	
	@Test
	@DisplayName(value = "disbursed loan request, exception thrown")
	public void disbursedLoanRequestExceptionThrown() throws AccountNotFoundException {
		when(loanService.disburseLoanRequest(loanDisbursalForm)).thenThrow(AccountNotFoundException.class);
		assertThrows(AccountNotFoundException.class, ()->loanService.disburseLoanRequest(loanDisbursalForm));
	}
	
}
