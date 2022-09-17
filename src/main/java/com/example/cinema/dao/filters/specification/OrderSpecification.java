package com.example.cinema.dao.filters.specification;

import com.example.cinema.dao.filters.criteriaData.OrderSearchCriteria;
import com.example.cinema.dao.model.Order;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@AllArgsConstructor
public class OrderSpecification {
    public static Specification<Order> buildOrderSpecification(OrderSearchCriteria orderSearchCriteria) {
        return Specification.where(hasId(orderSearchCriteria.getId()))
                .and(hasStatus(orderSearchCriteria.getCompleted()))
                .and(hasPrice(orderSearchCriteria.getPrice()))
                .and(hasFirstName(orderSearchCriteria.getOwnerFirstName()))
                .and(hasLastName(orderSearchCriteria.getOwnerLastName()))
                .and(hasOrderBetweenDate(orderSearchCriteria.getCreatedDate(), orderSearchCriteria.getExpiredDate()));
    }

    private static Specification<Order> hasId(Long id) {
        return (root, query, criteriaBuilder) -> Objects.isNull(id) ?
                criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("id"), id);
    }

    private static Specification<Order> hasStatus(Boolean status) {
        return (root, query, criteriaBuilder) -> Objects.isNull(status) ?
                criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("completed"), status);
    }

    private static Specification<Order> hasPrice(BigDecimal price) {
        return (root, query, criteriaBuilder)  -> Objects.isNull(price)
                ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("price"), price);
    }

    private static Specification<Order> hasFirstName(String customerFirstName) {
        return (root, query, criteriaBuilder) -> Objects.isNull(customerFirstName)
                ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("ownerFirstName"), "%" + customerFirstName + "%");
    }

    private static Specification<Order> hasLastName(String customerLastName) {
        return (root, query, criteriaBuilder) -> Objects.isNull(customerLastName)
                ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("ownerLastName"), "%" + customerLastName + "%");
    }

    private static Specification<Order> hasOrderBetweenDate(LocalDateTime startedDate, LocalDateTime expiredDate) {
        return (root, query, criteriaBuilder) -> Objects.isNull(startedDate) || Objects.isNull(expiredDate)
                ? criteriaBuilder.conjunction() : criteriaBuilder.between(root.get("expiredDate"), startedDate, expiredDate);
    }
}