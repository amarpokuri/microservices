package com.goods.order.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.goods.order.domain.Cart;
import com.goods.order.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class OrderController {

	@Autowired
	OrderService cartService;

	@PostMapping("/users/{idUser}/carts")
	@HystrixCommand(fallbackMethod = "getFromFallBackcreate", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	public @ResponseBody ResponseEntity<Void> create(@RequestBody Cart cart, HttpServletRequest request) throws URISyntaxException {
		Long id = cartService.save(cart);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

	@PutMapping("/users/{idUser}/carts/{idCart}")
	@HystrixCommand(fallbackMethod = "getFromFallBackAddProducts", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	public @ResponseBody ResponseEntity<Void> addProduct(@PathVariable("idCart") Long idCart, @RequestParam("idProduct") Long idProduct,
			@RequestParam("quantity") Integer quantity) {
		cartService.add(idCart, idProduct, quantity);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@PostMapping("/orders")
	@HystrixCommand(fallbackMethod = "getFromFallBackordered", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			   @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")			   
			})
	public @ResponseBody ResponseEntity<Void> ordered(@PathVariable("idCart") Long idCart, HttpServletRequest request) throws URISyntaxException {
		Long id = cartService.ordered(idCart);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/" + id.toString()));
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}
	
	public ResponseEntity<Void> getFromFallBackcreate(Cart cart, HttpServletRequest request) {
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR); // need to add some kind of message
	}
	
	public ResponseEntity<Void> getFromFallBackAddProducts(Long idCart,Long idProduct,Integer quantity) {
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	public ResponseEntity<Void> getFromFallBackordered(Long idCart, HttpServletRequest request) {
		return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
