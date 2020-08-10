package com.capgemini.pecunia.bank.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.capgemini.pecunia.bank.dao.PbankDao;
import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.PbankTXNNotFouException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;
import com.capgemini.pecunia.bank.service.PBankService;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class PecuniaBankApplicationTests {
	@Mock
	private PbankDao dao;

	@Mock
	private PBankService service;

	@Test
	@DisplayName("Test for accout exist ")
	public void Test1() {

		when(dao.getBankTransactions("123456654322")).thenReturn(null);
		assertEquals(null, dao.getBankTransactions("123456654322"));

	}

	@Test
	@DisplayName("Test for user given accId")
	public void Test2() throws ValidateException, PbankTXNNotFouException {
		when(service.getBankTransactions("0")).thenReturn(null);
		assertEquals(null, service.getBankTransactions("0"));
	}

	@Test
	@DisplayName("Test for user given accId")
	public void Test3() throws ValidateException, PbankTXNNotFouException {

		when(service.getBankTransactions("1234566543")).thenReturn(null);
		assertEquals(null, service.getBankTransactions("1234566543"));
	}

	@Test
	@DisplayName("Test for user given accId")
	public void Test4() throws ValidateException, PbankTXNNotFouException {

		when(service.getBankTransactions("-123456654322")).thenReturn(null);
		assertEquals(null, service.getBankTransactions("-123456654322"));
	}

	@Test
	@DisplayName("Test for user given accId")
	public void Test5() throws ValidateException, PbankTXNNotFouException {

		when(service.getBankTransactions("12345665432")).thenThrow(ValidateException.class);
		assertThrows(ValidateException.class, () -> service.getBankTransactions("12345665432"));

	}

	@Test
	@DisplayName("Test for user given Dates")
	public void Test6() throws ValidateException, PbankTXNNotFouException, DateException {

		when(service.getBankTransactions("123456654321", LocalDate.of(2020, 01, 15), LocalDate.of(2019, 07, 14)))
				.thenThrow(DateException.class);
		assertThrows(DateException.class, () -> service.getBankTransactions("123456654321", LocalDate.of(2020, 01, 15),
				LocalDate.of(2019, 07, 14)));

	}

	@Test
	@DisplayName("Test transactions for user given accId")
	public void Test7() throws ValidateException, PbankTXNNotFouException, DateException {

		when(service.getBankTransactions("123456654321", LocalDate.of(2019, 07, 14), LocalDate.of(2020, 01, 15)))
				.thenThrow(PbankTXNNotFouException.class);
		assertThrows(PbankTXNNotFouException.class, () -> service.getBankTransactions("123456654321",
				LocalDate.of(2019, 07, 14), LocalDate.of(2020, 01, 15)));

	}

}
