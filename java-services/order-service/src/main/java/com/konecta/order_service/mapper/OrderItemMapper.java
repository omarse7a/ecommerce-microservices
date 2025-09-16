package com.konecta.order_service.mapper;

import com.konecta.order_service.dto.OrderItemDto;
import com.konecta.order_service.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    OrderItem toEntity(OrderItemDto dto);

    OrderItemDto toDto(OrderItem entity);
}
