package com.goods.account.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.goods.account.model.Customer;

@WebMvcTest
public class AccountConrollerTest {

	    @Autowired
	    private MockMvc mockMvc;
	    
	    @MockBean
	    private AccountController accountConroller;
	    
	    @Test
	    void logintest() throws Exception {
	    	Customer customer = new Customer();
	        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/login?username='res'&password='res'",customer);
	        mockMvc.perform(request)
	                .andDo(MockMvcResultHandlers.print())
	                .andExpect(MockMvcResultMatchers.status().isOk());

	        Mockito.verify(accountConroller, Mockito.times(1)).login(Mockito.anyString(),Mockito.anyString());

	    }
	    
	    @Test
	    void checkCustomertest() throws Exception {
	        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/checkidexists/1");
	        mockMvc.perform(request)
	                .andDo(MockMvcResultHandlers.print())
	                .andExpect(MockMvcResultMatchers.status().isOk());

	        Mockito.verify(accountConroller, Mockito.times(1)).checkCustomer(Mockito.anyLong());

	    }

}
