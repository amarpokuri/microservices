package com.goods.order.controller;

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

import com.goods.order.domain.Cart;

@WebMvcTest
public class OrderControllerTest {


    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private OrderController orderController;
    
    @Test
    void createest() throws Exception {
    	Cart cart = new Cart();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/users/1/carts",cart);
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
    
    @Test
    void addProducttest() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/users/1/carts/3?idProduct=1&quantity=1");
        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());

    }

}
