package com.capgemini.pecunia.bank;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.pecunia.bank.PecuniaBankApplication;
import com.capgemini.pecunia.bank.dao.LoanDisbursalDao;
import com.capgemini.pecunia.bank.dao.LoanRequestDao;
import com.capgemini.pecunia.bank.dto.LoanRequestForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.entity.Customer;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;
import com.capgemini.pecunia.bank.service.LoanDisbursalService;
import com.capgemini.pecunia.bank.service.LoanRequestService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName(value = "TestCases")
@RunWith(MockitoJUnitRunner.class)
public class PecuniaBankApplicationTests {

	@Mock
	private LoanRequestService loanRequestService;
	
	@Mock
	private LoanRequestForm loanRequestForm;
	
	@Mock
	private LoanDisbursalService loanDisbursalService;
	
	@Mock
	private LoanRequestDao loanRequestDao;
	
	@Mock
	private LoanDisbursalDao loanDisbursalDao;
	
	@Mock
	private LoanRequest loanRequest;
	
	private static List<LoanRequest> list = new ArrayList<>();
	private static Account  account;
	private static Customer  customer;
	
	@BeforeEach
	public void init() {
		customer = new Customer("459115023130", "sumanth", "8146089998", "APSBC78945", LocalDate.of(1999, 04, 13), "4/42", "ap", "kadapa", "india", "516003", "male");
		account = new Account("8146089998", customer, 500.0, "savings", "45", "SBIN0045");
		loanRequestForm = new LoanRequestForm(500000.0, 24, "8146089998", "house loan", 400000.0, 5, 890);
	}
	
	@Test
	@DisplayName(value = "Adding loan request")
	public void addLoanRequestTest() throws AccountNotFoundException {
		when(loanRequestService.createLoanRequest(loanRequestForm)).thenReturn("LoanRequestSuccessful");
		assertEquals("LoanRequestSuccessful", loanRequestService.createLoanRequest(loanRequestForm));
	}
	
	@Test
	@DisplayName(value = "adding loan request , exception thrown")
	public void addLoanRequestExceptionThrownTest()throws AccountNotFoundException {
		when(loanRequestService.createLoanRequest(loanRequestForm)).thenThrow(AccountNotFoundException.class);
		assertThrows(AccountNotFoundException.class, ()->loanRequestService.createLoanRequest(loanRequestForm));
	}
	
	@Test
	@DisplayName(value = "loan request successful")
	public void abc() throws AccountNotFoundException {
		when(loanRequestDao.getAccount("8146089998")).thenReturn(account);
		assertEquals("LoanRequestSuccessful", loanRequestService.createLoanRequest(loanRequestForm));
	}
	
	@Test
	@DisplayName(value = "disbursed loan request")
	public void disbursedLoanRequest() throws AccountNotFoundException {
		when(loanDisbursalService.disburseLoanRequest("81460899982","accept")).thenReturn(true);
		assertEquals(true, loanDisbursalService.disburseLoanRequest("81460899982","accept"));
	}
	
	@Test
	@DisplayName(value = "retrieving loan request")
	public void retrievingLoanRequest() throws LoanException {
		when(loanDisbursalService.retrievedisbursedLoanRequest("pending")).thenReturn(list);
		assertEquals(list, loanDisbursalService.retrievedisbursedLoanRequest("pending"));
	}
	
	@Test
	@DisplayName(value = "loan disbursed failed")
	public void loanDisbursedFailed() throws AccountNotFoundException {
		when(loanDisbursalDao.getLoanRequest("81460899982")).thenReturn(loanRequest);
		when(loanRequest.getAccount()).thenReturn(account);
		assertEquals(false, loanDisbursalService.disburseLoanRequest("81460899982","accept"));
	}
	
	@Test
	@DisplayName(value = "disbursed loan request, exception thrown")
	public void disbursedLoanRequestExceptionThrown() throws AccountNotFoundException {
		when(loanDisbursalService.disburseLoanRequest("81460899982","accept")).thenThrow(AccountNotFoundException.class);
		assertThrows(AccountNotFoundException.class, ()->loanDisbursalService.disburseLoanRequest("81460899982","accept"));
	}
	
}
