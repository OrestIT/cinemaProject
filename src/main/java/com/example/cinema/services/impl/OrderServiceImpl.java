package com.example.cinema.services.impl;

import com.example.cinema.dao.filters.criteriaData.OrderSearchCriteria;
import com.example.cinema.dao.filters.page.OrderPage;
import com.example.cinema.dao.filters.specification.OrderSpecification;
import com.example.cinema.dao.model.Order;
import com.example.cinema.dao.repository.OrderRepository;
import com.example.cinema.presentation.dto.OrderDto;
import com.example.cinema.presentation.dto.metadata.OrderMetadata;
import com.example.cinema.services.OrderService;
import com.example.cinema.services.exception.order.OrderNotFoundFoundException;
import com.example.cinema.services.mapper.OrderMapper;
import com.example.cinema.services.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto getById(Long id) {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundFoundException(Constants.CAN_NOT_FIND_ORDER + id));

        return this.orderMapper.toDto(order);
    }

    @Override
    public OrderDto create(OrderMetadata order) {
        Order savedOrder = this.orderRepository.save(this.orderMapper.toModel(order));
        return this.orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto update(OrderDto orderDto, Long id) {
        Order order = this.orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundFoundException(Constants.CAN_NOT_FIND_ORDER + id));

        this.orderMapper.updateOrderFromDto(orderDto, order);
        return this.orderMapper.toDto(this.orderRepository.save(order));
    }

    @Override
    public void delete(Long id) {
        try {
            this.orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error(Constants.CAN_NOT_DELETE_ORDER + "{}", id, e);
            throw new OrderNotFoundFoundException(Constants.CAN_NOT_DELETE_ORDER + id);
        }
    }

    @Override
    public Page<OrderDto> getByParam(OrderSearchCriteria orderSearchCriteria, OrderPage orderPage) {
        Specification<Order> orderSpecification = OrderSpecification.buildOrderSpecification(orderSearchCriteria);
        List<OrderDto> orderDtos = this.orderRepository.findAll(orderSpecification).stream()
                .map(this.orderMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(orderDtos, PageRequest.of(orderPage.getPageNumber(), orderPage.getPageSize(),
                Sort.by(Objects.isNull(orderPage.getSortDirection()) ? "id" : orderPage.getSortBy())), orderDtos.size());
    }
}