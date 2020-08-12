package com.capgemini.pecunia.bank.test;




import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.capgemini.pecunia.bank.exceptions.DateException;
import com.capgemini.pecunia.bank.exceptions.ValidateException;
import com.capgemini.pecunia.bank.service.PBankService;
import com.capgemini.pecunia.bank.service.PBankServiceImpl;


/**********************************************************************************
 * 
 * @Author Name  : venkata sai kumar
 * Class Name    : PecuniaBankAppTest
 * Description   : cheacking code using Junit
 *
 * 
 **********************************************************************************/
@TestInstance(Lifecycle.PER_CLASS)
public class PecuniaBankAppTest {
	static PBankService ser;

	@BeforeAll
	public static void beforeAll() {
		ser = new PBankServiceImpl();
	}

	@Test
	@DisplayName("Test user given accId")
	public void Test1() {
		assertThrows(ValidateException.class,
				() -> ser.accountSummary("12345", LocalDate.of(2019, 07, 14), LocalDate.of(2020, 01, 15)));
	}
	
	@Test
	@DisplayName("Test user given Dates")
	public void Test2() {
		assertThrows(DateException.class,
				() -> ser.accountSummary("123456654322", LocalDate.of(2020, 01, 15), LocalDate.of(2019, 07, 14)));
	}
	
	@Test
	@DisplayName("Test user given Dates")
	public void Test3() {
		assertThrows(DateException.class,
				() -> ser.accountSummary("123456654322", LocalDate.of(2020, 8, 1), LocalDate.of(2020, 8, 28)));
	}
	

}