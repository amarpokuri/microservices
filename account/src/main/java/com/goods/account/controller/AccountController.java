package com.goods.account.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goods.account.exception.AuthenticationFailedException;
import com.goods.account.model.Customer;
import com.goods.account.service.AccountService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class AccountController {

	@Autowired
	AccountService customerService;

	@HystrixCommand(fallbackMethod = "getFromFallBacklogin", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	@PostMapping("/users/login")
	public Customer login(@RequestParam("username") String username, @RequestParam("password") String password)
			throws NoSuchAlgorithmException, AuthenticationFailedException {
		Customer customer = customerService.authentication(username, password);
		return customer;
	}

	@HystrixCommand(fallbackMethod = "getFromFallBackCheckCustomer", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	@GetMapping("/users/checkidexists/{id}")
	public ResponseEntity<String> checkCustomer(@PathVariable("id") Long id) throws NoSuchAlgorithmException {
		boolean validCustomer = customerService.checkCustomerExists(id);
		if (validCustomer) {
			return new ResponseEntity<String>("exists", HttpStatus.CREATED);
		}

		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}

	@HystrixCommand(fallbackMethod = "addCustomerFallBack", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	@PostMapping("/users")
	public @ResponseBody ResponseEntity<Void> addCustomer(@RequestBody Customer customer, HttpServletRequest request)
			throws URISyntaxException, NoSuchAlgorithmException {
		Long id = customerService.addCustomer(customer);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
	
	public Customer getFromFallBacklogin(String username, String password) {
		return  new Customer(); // need to add some kind of message
	}
	
	public ResponseEntity<String> getFromFallBackCheckCustomer(Long id) {
		return new ResponseEntity<String>("service down", HttpStatus.INTERNAL_SERVER_ERROR); // need to add some kind of message
	}
	
	public ResponseEntity<Void> addCustomerFallBack(Customer customer, HttpServletRequest request) {
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
