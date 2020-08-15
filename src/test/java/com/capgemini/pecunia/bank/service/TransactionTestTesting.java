package com.capgemini.pecunia.bank.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.pecunia.bank.dao.TransactionDao;
import com.capgemini.pecunia.bank.dao.TransactionDaoImpl;
import com.capgemini.pecunia.bank.dto.Transfer;
import com.capgemini.pecunia.bank.dto.TxnForm;
import com.capgemini.pecunia.bank.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.bank.exceptions.TransactionException;
import com.capgemini.pecunia.bank.web.TransactionController;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
// @RunWith(MockitoJUnitRunner.class)
// @ExtendWith(MockitoExtension.class)
public class TransactionTestTesting {

	@Mock
	TransactionService transactionService;

	/*
	 * Logger used to Record unusual circumstances or error that may be happening in
	 * the program. also use to get info what is going in the application.
	 */

	Logger logger = LoggerFactory.getLogger(TransactionController.class);

	/*
	 * @Autowired is used to Inject the dependency of walletService class into
	 * WalletApplicationProjectBackendApplicationTests class. It internally uses
	 * setter or constructor to inject dependency.
	 */

	@Autowired
	TransactionService transactionServiceObject;

	private TransactionDao transactionDao = new TransactionDaoImpl();

	TxnForm txnform = new TxnForm();
	Transfer transfer = new Transfer();
	boolean message = false;

//		public Boolean creditUsingSlip(TxnForm txnform) throws AccountNotFoundException;
	//
//		public Boolean debitUsingSlip(TxnForm txnform) throws AccountNotFoundException,TransactionException;
	//
//		public Boolean creditUsingCheque(Transfer transfer) throws AccountNotFoundException,TransactionException;
	//
//		public Boolean debitUsingCheque(TxnForm txnform) throws AccountNotFoundException,TransactionException;
	//
//		public Boolean addSlip(Slip slip);
	//
//		public boolean addCust(Customer cust);
	//
//		public boolean addCheque(Cheque cheque);
	//
//		public boolean addAccount(Account account);

	/*
	 * Test will do testing for creditUsingSlip() method which fetches credit
	 * through slip from database
	 */

	// --------------------- TEST CASE 1 -----------------------------//
	/*
	 * In this test case we are passing a invalid Account Number that function
	 * throws AccountNotFoundException ".
	 */

//		@Test
//		@DisplayName("creditUsingSlip")
//		public void testCreditUsingSlips() throws Exception{
//			logger.info("Validation for creditUsingSlip From Database");
//			txnform.setAccId("123456");
//			txnform.setAmount(2500.00);
//			 assertThrows(AccountNotFoundException.class,()->{
//				transactionService.creditUsingSlip(txnform);
//				
//			});
//			
//		}

	/*
	 * Test will do testing for creditUsingSlip() method which fetches credit
	 * through slip from database
	 */

	@Test
	public void testCreditUsingSlipss() throws Exception {

		logger.info("Validation for creditUsingSlip From Database");
		txnform.setAccId("987654321");
		txnform.setAmount(500.00);
		boolean result = transactionService.creditUsingSlip(txnform);

		assertEquals(result, message);

	}

	/*
	 * Test will do testing for debitUsingSlip() method which fetches debit through
	 * slip from database
	 */

	@Test
	public void testDebitUsingSlipss() throws Exception {

		logger.info("Validation for DebitUsingSlip From Database");
		txnform.setAccId("66646578564");
		txnform.setAmount(100.00);
		boolean result = transactionService.debitUsingSlip(txnform);

		assertEquals(result, message);

	}

	/*
	 * Test will do testing for creditUsingCheque() method which fetches credit
	 * through cheque from database
	 */

	
	
	@Test
	public void testCreditUsingCheque() throws Exception {

		logger.info("Validation for creditUsingCheque From Database");
		transfer.setFromAccId("8656453");
		transfer.setToAccId("686844");
		transfer.setAmount(1000.00);

		boolean result = transactionService.creditUsingCheque(transfer);

		assertEquals(result, message);

	}

	/*
	 * Test will do testing for debitUsingSlip() method which fetches debit through
	 * cheque from database
	 */

	@Test
	public void testDebitUsingChequee() throws Exception {

		logger.info("Validation for DebitUsingCheque From Database");
		txnform.setAccId("9765444");
		txnform.setAmount(200.00);

		boolean result = transactionService.debitUsingCheque(txnform);

		assertEquals(result, message);

	}

	@Test
	public void testdebitBySlip() throws AccountNotFoundException, TransactionException {
		TxnForm txnform1 = new TxnForm();
//		txnform1.setAccId("987654321");
//		txnform1.setAmount(400.0);

		when(transactionService.debitUsingSlip(txnform1)).thenReturn(true);
		assertEquals(true, transactionService.debitUsingSlip(txnform1));

	}

	@Test
	public void testcreditBySlip() throws AccountNotFoundException, TransactionException {
		TxnForm txnform1 = new TxnForm();
//		txnform1.setAccId("987654321");
//		txnform1.setAmount(400.0);

		when(transactionService.creditUsingSlip(txnform1)).thenReturn(true);
		assertEquals(true, transactionService.creditUsingSlip(txnform1));

	}

	@Test
	public void testcreditUsingCheque() throws AccountNotFoundException, TransactionException {
		Transfer slip = new Transfer();
		when(transactionService.creditUsingCheque(slip)).thenReturn(true);
		assertEquals(true, transactionService.creditUsingCheque(slip));
	}

	@Test
	public void testdebitUsingCheque() throws AccountNotFoundException, TransactionException {
		Transfer slip = new Transfer();
		when(transactionService.creditUsingCheque(slip)).thenReturn(true);
		assertEquals(true, transactionService.creditUsingCheque(slip));
	}

}
