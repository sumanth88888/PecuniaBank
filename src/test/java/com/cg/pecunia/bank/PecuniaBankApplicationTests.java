package com.cg.pecunia.bank;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.capgemini.pecunia.bank.PecuniaBankApplication;
import com.capgemini.pecunia.bank.dto.LoanRequestForm;
import com.capgemini.pecunia.bank.entity.LoanRequest;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.LoanException;
import com.capgemini.pecunia.bank.service.LoanDisbursalService;
import com.capgemini.pecunia.bank.service.LoanRequestService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PecuniaBankApplication.class)
@AutoConfigureMockMvc
public class PecuniaBankApplicationTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Mock
	private LoanRequestService loanRequestService;
	
	@Mock
	private LoanRequestForm loanRequestForm;
	
	@Mock
	private LoanDisbursalService loanDisbursalService;
	
	private List<LoanRequest> list = new ArrayList<>();
	
	@Rule
    public ExpectedException expectedThrown = ExpectedException.none();
	
	@Test
	public void addLoanRequestTest() {
		when(loanRequestService.createLoanRequest(loanRequestForm)).thenReturn("LoanRequestSuccessful");
		assertEquals("LoanRequestSuccessful", loanRequestService.createLoanRequest(loanRequestForm));
	}


	@Test(/*expected = AccountNotFoundException.class*/)
	public void addLoanRequestAccountNotFound() {
		//LoanRequestService spy = Mockito.spy(loanRequestService);
		//when(loanRequestService.createLoanRequest(loanRequestForm)).thenThrow(AccountNotFoundException.class);
		//when(spy.createLoanRequest(loanRequestForm)).thenThrow(AccountNotFoundException.class);
		//doThrow(AccountNotFoundException.class).when(loanRequestService.createLoanRequest(loanRequestForm));
		expectedThrown.expect(AccountNotFoundException.class);
		//spy.createLoanRequest(loanRequestForm);
		loanRequestService.createLoanRequest(loanRequestForm);
	}
	
	
	@Test
	public void abc() throws AccountNotFoundException {
		when(loanDisbursalService.disburseLoanRequest("81460899982")).thenReturn(true);
		assertEquals(true, loanDisbursalService.disburseLoanRequest("81460899982"));
	}
	
	@Test
	public void aadf() throws LoanException {
		when(loanDisbursalService.retrievedisbursedLoanRequest("pending")).thenReturn(list);
		assertEquals(list, loanDisbursalService.retrievedisbursedLoanRequest("pending"));
	}
	
	
	
}
