package com.example.cinema.service;

import com.example.cinema.dao.filters.criteriaData.OrderSearchCriteria;
import com.example.cinema.dao.filters.page.OrderPage;
import com.example.cinema.dao.filters.specification.OrderSpecification;
import com.example.cinema.dao.model.Order;
import com.example.cinema.dao.repository.OrderRepository;
import com.example.cinema.presentation.dto.OrderDto;
import com.example.cinema.presentation.dto.metadata.OrderMetadata;
import com.example.cinema.services.exception.order.OrderNotFoundFoundException;
import com.example.cinema.services.impl.OrderServiceImpl;
import com.example.cinema.services.mapper.OrderMapper;
import com.example.cinema.services.utils.Constants;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.cinema.utils.TestModel.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    OrderMapper mapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void shouldGetOrderById() {
        OrderDto orderDto = getOrderDto();
        Order order = getOrder();

        when(mapper.toDto(order)).thenReturn(orderDto);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        assertEquals(orderDto, orderService.getById(1L));
    }

    @Test
    void getOrderByIdShouldThrowException() {
        when(orderRepository.findById(2L)).thenReturn(Optional.empty());

        Exception thrownException = assertThrows(OrderNotFoundFoundException.class, () -> orderService.getById(2L));
        assertEquals(Constants.CAN_NOT_FIND_ORDER + 2L, thrownException.getMessage());
    }

    @Test
    void shouldCreateOrder() {
        Order order = getOrder();
        OrderDto orderDto = getOrderDto();
        OrderMetadata orderMetadata = getOrderMetadata();

        when(mapper.toModel(orderMetadata)).thenReturn(order);
        when(mapper.toDto(order)).thenReturn(orderDto);
        when(orderRepository.save(order)).thenReturn(order);
        orderService.create(orderMetadata);

        assertEquals(1L, orderDto.getId());
    }

    @Test
    void shouldUpdateOrder() {
        OrderDto orderDto = getOrderDtoToUpdate();
        Order order = getOrder();

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);
        doNothing().when(mapper).updateOrderFromDto(orderDto, order);
        orderService.update(orderDto, 1L);
        verify(mapper).updateOrderFromDto(orderDto, order);
    }

    @Test
    void shouldDeleteOrder() {
        OrderDto orderDto = getOrderDto();

        orderService.delete(orderDto.getId());
        verify(orderRepository).deleteById(orderDto.getId());
    }

    @Test
    @Disabled
    void shouldGetByParam() {
        Order order = getOrder();
        OrderDto orderDto = getOrderDto();
        OrderSearchCriteria orderSearchCriteria = getOrderSearchCriteria();
        OrderPage orderPage = getOrderPage();
        List<OrderDto> orderDtos = List.of(orderDto);
        PageImpl<OrderDto> pageDto = new PageImpl<>(orderDtos, PageRequest.of(orderPage.getPageNumber(), orderPage.getPageSize(),
                Sort.by(Objects.isNull(orderPage.getSortDirection()) ? "id" : orderPage.getSortBy())), orderDtos.size());

        when(orderRepository.findAll(OrderSpecification.buildOrderSpecification(orderSearchCriteria))).thenReturn(Collections.emptyList());
        when(mapper.toDto(order)).thenReturn(orderDto);

        assertEquals(pageDto, orderService.getByParam(orderSearchCriteria, orderPage));

    }
}