package com.goods.account.service;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.goods.account.exception.AuthenticationFailedException;
import com.goods.account.model.Customer;
import com.goods.account.repo.CustomerRepo;
import com.goods.account.util.ShaHashing;

@Service
@Transactional
public class AccountServiceImp implements AccountService {

	@Autowired
	CustomerRepo customerDao;
	
	@Override
	public Customer authentication(String username, String password) 
			throws NoSuchAlgorithmException, AuthenticationFailedException {
		List<Customer> customer = customerDao.findByUsername(username);
		if(!CollectionUtils.isEmpty(customer) && customer.get(0).getPassword().equals(ShaHashing.encrypted(password)))
			return customer.get(0);
		else
			throw new AuthenticationFailedException();
	}

	@Override
	public Long addCustomer(Customer customer) throws NoSuchAlgorithmException {
		customer.setPassword(ShaHashing.encrypted(customer.getPassword()));
		return customerDao.save(customer).getIdcustomer();
	}

	@Override
	public boolean checkCustomerExists(Long id) throws NoSuchAlgorithmException {
		return customerDao.findById(id).isPresent();
	}

}
