package com.goods.order.service;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goods.order.model.Order.BuilderOrder;
import com.goods.order.domain.Product;
import com.goods.order.model.Cart;
import com.goods.order.model.LineItem;
import com.goods.order.model.Order;
import com.goods.order.repo.AccountDAO;
import com.goods.order.repo.CartDao;
import com.goods.order.repo.OrderDao;
import com.goods.order.repo.ProductDAO;
import com.goods.order.util.OrderStatus;

@Service
@Transactional
public class OrderServiceImp implements OrderService {

	@Autowired
	CartDao cartDao;

	@Autowired
	ProductDAO productDao;
	
	@Autowired
	AccountDAO accountDao;
	
	@Autowired
	OrderDao orderDao;

	@Override
	public Long save(com.goods.order.domain.Cart cart) {
		if(cart != null && cart.getCustomer() != null && accountDao.checkAccountExists(cart.getCustomer().getIdCustomer())) {
			Cart cartDAO = CartMapper.INSTANCE.getEntityFromModel(cart);
			return cartDao.save(cartDAO).getIdCart();
		}
		
		throw new NoSuchElementException();
	}

	@Override
	public void add(Long idCart, Long idProduct, Integer quantity) {
		Optional<Cart> cart = cartDao.findById(idCart);
		Product product = productDao.getProduct(idProduct);
		if(cart.isPresent() && product != null) {
			LineItem lineItem = new LineItem(cart.get(), product.getIdProduct(), quantity, product.getPrice());
			cart.get().getLinesItems().add(lineItem);
			cartDao.save(cart.get());
		}else {
			throw new NoSuchElementException();
		}
		
	}

	@Override
	public Long ordered(Long idCart) {
		Optional<Cart> cart = cartDao.findById(idCart);
		if(cart.isPresent()) {
			Order order = new BuilderOrder()
					.setIdCustomer(cart.get().getIdCustomer())
					.setOrdered(new Date())
					.setStatus(OrderStatus.NEW.toString())
					.setTotal(cart.get().calculateTotal())
					.setLinesItems(cart.get().getLinesItems())
					.build();
			return orderDao.save(order).getIdorder();
		}
		else {
			throw new NoSuchElementException();
		}
		
	}

}
