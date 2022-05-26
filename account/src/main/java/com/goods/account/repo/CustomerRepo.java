package com.goods.account.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goods.account.model.Customer;

//import com.goods.account.app.model.Customer;
public interface CustomerRepo extends JpaRepository<Customer, Long>{

	List<Customer> findByUsername(String username);
}



