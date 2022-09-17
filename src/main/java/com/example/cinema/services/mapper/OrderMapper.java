package com.example.cinema.services.mapper;

import com.example.cinema.dao.model.Order;
import com.example.cinema.presentation.dto.OrderDto;
import com.example.cinema.presentation.dto.metadata.OrderMetadata;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMapper {
    OrderDto toDto(Order order);

    @Mapping(target = "movie", source = "movieDto")
    Order toModel(OrderMetadata orderMetadata);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateOrderFromDto(OrderDto movieDto, @MappingTarget Order order);
}
