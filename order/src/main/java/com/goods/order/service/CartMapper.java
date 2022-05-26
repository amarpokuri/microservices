package com.goods.order.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goods.order.model.Cart;

@Mapper(uses = { LineItemMapper.class },componentModel = "spring")
public interface CartMapper {

	CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

	@Mapping(target = "idCart", source = "idCart")
	@Mapping(target = "subtotal", source = "subtotal")
	@Mapping(target = "idCustomer", source = "cart.customer.idCustomer")
	@Mapping(target = "linesItems", source = "cart.linesItems")
	Cart getEntityFromModel(com.goods.order.domain.Cart cart);
}
