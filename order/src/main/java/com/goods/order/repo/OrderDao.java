package com.goods.order.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goods.order.model.Order;

public interface OrderDao extends JpaRepository<Order, Long> {

}
