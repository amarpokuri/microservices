/**
 * 
 */
package com.goods.account.service;


import java.security.NoSuchAlgorithmException;

import com.goods.account.exception.AuthenticationFailedException;
import com.goods.account.model.Customer;

public interface AccountService {

	Customer authentication(String username, String password) 
			throws NoSuchAlgorithmException, AuthenticationFailedException;
	Long addCustomer(Customer customer) throws NoSuchAlgorithmException;
	boolean checkCustomerExists(Long id) throws NoSuchAlgorithmException;
}
