package com.example.cinema.presentation.controller;

import com.example.cinema.dao.filters.criteriaData.OrderSearchCriteria;
import com.example.cinema.dao.filters.page.OrderPage;
import com.example.cinema.presentation.dto.OrderDto;
import com.example.cinema.presentation.dto.metadata.OrderMetadata;
import com.example.cinema.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cinema/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderMetadata order) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.orderService.create(order));
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(this.orderService.getById(id));
    }

    @GetMapping("/param")
    public ResponseEntity<Page<OrderDto>> getByParam(OrderSearchCriteria orderSearchCriteria, OrderPage orderPage) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getByParam(orderSearchCriteria, orderPage));
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDto> updateOrder(@RequestBody OrderDto order, @PathVariable Long id) {
        return ResponseEntity.ok(this.orderService.update(order, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable Long id) {
        this.orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}