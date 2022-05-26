package com.goods.order.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.goods.order.domain.LineItem;

@Mapper
public interface LineItemMapper {
	LineItemMapper INSTANCE = Mappers.getMapper(LineItemMapper.class);
	
	 @Mapping(target="idProduct", source="product.idProduct")
	 com.goods.order.model.LineItem toDto(LineItem patient);

}
