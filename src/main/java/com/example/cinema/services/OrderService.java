package com.example.cinema.services;

import com.example.cinema.dao.filters.criteriaData.OrderSearchCriteria;
import com.example.cinema.dao.filters.page.OrderPage;
import com.example.cinema.presentation.dto.OrderDto;
import com.example.cinema.presentation.dto.metadata.OrderMetadata;
import org.springframework.data.domain.Page;

public interface OrderService {
    OrderDto getById(Long id);

    OrderDto create(OrderMetadata movie);

    OrderDto update(OrderDto movie, Long id);

    void delete(Long id);

    Page<OrderDto> getByParam(OrderSearchCriteria orderSearchCriteria, OrderPage orderPage);
}
