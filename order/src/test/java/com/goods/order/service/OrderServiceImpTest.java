package com.goods.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.goods.order.domain.Cart;
import com.goods.order.domain.Customer;
import com.goods.order.domain.Product;
import com.goods.order.repo.AccountDAO;
import com.goods.order.repo.CartDao;
import com.goods.order.repo.OrderDao;
import com.goods.order.repo.ProductDAO;

public class OrderServiceImpTest {
	
	// Create a mock of Resource to change its behaviour for testing
			@Mock
			OrderDao orderDao;
			
			@Mock
			CartDao cartDao;
			
			@Mock
			AccountDAO accountDao;
			
			@Mock
			ProductDAO productDao;

			// Testing instance, mocked `resource` should be injected here 
			@InjectMocks
			@Resource
			private OrderServiceImp orderServiceImp;
			
			@Mock
			private CartMapper cartMapper;
			

			@Test
			public void addTest() throws NoSuchAlgorithmException {
				// Initialize mocks created above
			    MockitoAnnotations.openMocks(this);
			    Cart cart = new Cart();
			    Customer customer = new Customer();			    
			    customer.setIdCustomer(1l);
			    customer.setUsername("udd");
			    cart.setCustomer(customer);
			    cart.setLinesItems(null);
			    
			    com.goods.order.model.Cart cartIn = new com.goods.order.model.Cart();
			    cartIn.setIdCart(1l);
			    Product product = new Product();
			    product.setIdProduct(1l);
			  
			    Mockito.when(cartDao.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(cartIn));
			    Mockito.when(productDao.getProduct(Mockito.any())).thenReturn(product);
			    Mockito.when(cartDao.save(Mockito.any())).thenReturn(cartIn);
			    orderServiceImp.add(1l, 1l, 1);
			    Mockito.verify(cartDao, Mockito.times(1)).save(Mockito.any());
			}

}
