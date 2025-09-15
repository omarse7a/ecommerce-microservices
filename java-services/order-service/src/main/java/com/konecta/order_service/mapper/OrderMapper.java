package com.konecta.order_service.mapper;

import com.konecta.order_service.dto.OrderRequest;
import com.konecta.order_service.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "items", source = "items")
    Order toEntity(OrderRequest dto);

    @Mapping(target = "items", source = "items")
    OrderRequest toDto(Order entity);
}
