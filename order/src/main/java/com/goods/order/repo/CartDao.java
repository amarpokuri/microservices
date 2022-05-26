package com.goods.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goods.order.model.Cart;

public interface CartDao extends JpaRepository<Cart, Long> {


	
}
