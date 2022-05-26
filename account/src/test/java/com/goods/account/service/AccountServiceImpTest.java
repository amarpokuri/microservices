package com.goods.account.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.goods.account.exception.AuthenticationFailedException;
import com.goods.account.model.Customer;
import com.goods.account.repo.CustomerRepo;

public class AccountServiceImpTest {
	
	// Create a mock of Resource to change its behaviour for testing
		@Mock
		CustomerRepo customerDao;

		// Testing instance, mocked `resource` should be injected here 
		@InjectMocks
		@Resource
		private AccountServiceImp accountServiceImp;
		

		@Test
		public void getAuthenticationTest() throws NoSuchAlgorithmException, AuthenticationFailedException {
			// Initialize mocks created above
		    MockitoAnnotations.openMocks(this);
		    List<Customer> customerList = new ArrayList<>();
		    Customer customer = new Customer();
		    customer.setIdcustomer(1l);
		    customer.setUsername("udd");
		    customer.setPassword("9b7ecc6eeb83abf9ade10fe38865df4499be3568dcc507ae2ec3b44989cb0093");
		    customerList.add(customer);
		    // Change behaviour of `resource`
		    Mockito.when(customerDao.findByUsername(Mockito.anyString())).thenReturn(customerList);

			Customer actualResult = accountServiceImp.authentication("udd","dd");

			assertEquals(1l, actualResult.getIdcustomer());
		}
		
		@Test
		public void getcheckCustomerExists() throws NoSuchAlgorithmException, AuthenticationFailedException {
			// Initialize mocks created above
		    MockitoAnnotations.openMocks(this);
		    Customer customer = new Customer();
		    customer.setIdcustomer(1l);
		    customer.setUsername("udd");
		    customer.setPassword("9b7ecc6eeb83abf9ade10fe38865df4499be3568dcc507ae2ec3b44989cb0093");
		 
		    // Change behaviour of `resource`
		    Mockito.when(customerDao.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));

			boolean actualResult = accountServiceImp.checkCustomerExists(1l);

			assertEquals(true, actualResult);
		}
		
		


}
