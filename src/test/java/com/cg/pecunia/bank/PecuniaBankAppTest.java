package com.cg.pecunia.bank;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.pecunia.bank.PecuniaBankApplication;
import com.capgemini.pecunia.bank.dto.BankForm;
import com.capgemini.pecunia.bank.dto.EditForm;
import com.capgemini.pecunia.bank.entity.Account;
import com.capgemini.pecunia.bank.exceptions.InvalidAccountException;
import com.capgemini.pecunia.bank.exceptions.InvalidCustomerException;
import com.capgemini.pecunia.bank.service.BankService;
import com.capgemini.pecunia.bank.util.AccountConstants;

@DisplayName(value = "TestCases of Pecunia Bank")
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = PecuniaBankApplication.class)
@AutoConfigureMockMvc
public class PecuniaBankAppTest {

	@Mock
	private BankService bankService;
	
	@Mock
	private BankForm bankForm;
	
	@Mock
	private EditForm editForm;
	
	private static Account  account;
	
	@Test
	@DisplayName(value = "adding account")
	public void addAccountTest() throws InvalidCustomerException {
		when(bankService.addAccount(bankForm)).thenReturn("AccountAddedSuccessful");
		assertEquals("AccountAddedSuccessful", bankService.addAccount(bankForm));
	}
	
	@Test
	@DisplayName(value = "Adding account but Exception is thrown")
	public void addAccount1() throws InvalidCustomerException {
		when(bankService.addAccount(bankForm)).thenThrow(InvalidCustomerException.class);
		assertThrows(InvalidCustomerException.class, ()->bankService.addAccount(bankForm));
	}
	
	@Test
	@DisplayName(value = "Editing an account")
	public void editAccount() throws InvalidAccountException {
		when(bankService.editAccount(editForm)).thenReturn(AccountConstants.ACCOUNT_EDITED);
		assertEquals(true, bankService.editAccount(editForm));
	}
	
	@Test
	@DisplayName(value = "Editing an account but exception thrown")
	public void editAccount1() throws InvalidAccountException {
		when(bankService.editAccount(editForm)).thenThrow(InvalidAccountException.class);
		assertThrows(InvalidAccountException.class, ()->bankService.editAccount(editForm));
	}
	
	
	@Test
	@DisplayName(value = "Deleting an account")
	public void deleteAccount() throws InvalidAccountException {
		when(bankService.deleteAccount("79886720091024")).thenReturn(true);
		assertEquals(true, bankService.deleteAccount("79886720091024"));
	}
	
	@Test
	@DisplayName(value = "Deleting an Account but exception raised")
	public void deleteAccount1() throws InvalidAccountException {
		when(bankService.deleteAccount("1234567898765")).thenThrow(InvalidAccountException.class);
		assertThrows(InvalidAccountException.class, ()->bankService.deleteAccount("1234567898765"));
		
	}
	
	@Test
	@DisplayName(value = "Display the Account if present")
	public void viewAccount() throws InvalidAccountException {
		when(bankService.viewAccount("79886720091029")).thenReturn(account);
		assertEquals(account, bankService.viewAccount("79886720091029"));
	}
	
	@Test
	@DisplayName(value = "Displaying an Account but Exception raised")
	public void viewAccount1() throws InvalidAccountException {
		when(bankService.viewAccount("1234567898765")).thenThrow(InvalidAccountException.class);
		assertThrows(InvalidAccountException.class, ()->bankService.viewAccount("1234567898765"));
	}
	
	
	
}
