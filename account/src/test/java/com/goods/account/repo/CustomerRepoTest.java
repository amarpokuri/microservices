package com.goods.account.repo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.goods.account.model.Customer;

@DataJpaTest
public class CustomerRepoTest {

	@Autowired
	private CustomerRepo customerRepo; // class under test

	@Autowired
	private TestEntityManager testEntityManager; // for verification

	@Test
	void findByNameContainingIgnoreCase() {
		Customer customer = new Customer();
		customer.setLastName("udd");
		customer.setUsername("udd");
		customer.setPassword("9b7ecc6eeb83abf9ade10fe38865df4499be3568dcc507ae2ec3b44989cb0093");
		customer.setFirstName("udd");

		testEntityManager.persist(customer);

		testEntityManager.flush();

		List<Customer> empOpt = customerRepo.findByUsername("udd");
		assertTrue(!empOpt.isEmpty());

	}

}
