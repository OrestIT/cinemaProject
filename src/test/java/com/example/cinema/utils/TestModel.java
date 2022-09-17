package com.example.cinema.utils;

import com.example.cinema.dao.filters.criteriaData.OrderSearchCriteria;
import com.example.cinema.dao.filters.page.OrderPage;
import com.example.cinema.dao.model.Movie;
import com.example.cinema.dao.model.Order;
import com.example.cinema.presentation.dto.MovieDto;
import com.example.cinema.presentation.dto.OrderDto;
import com.example.cinema.presentation.dto.metadata.MovieMetadata;
import com.example.cinema.presentation.dto.metadata.OrderMetadata;
import com.example.cinema.presentation.enums.Rating;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TestModel {

    public static OrderMetadata getOrderMetadata() {
        return OrderMetadata.builder()
                .id(1L)
                .completed(true)
                .ownerFirstName("Orest")
                .ownerLastName("Mahdziak")
                .price(BigDecimal.ONE)
                .createdDate(LocalDateTime.of(2022, 5, 26, 10, 0))
                .expiredDate(LocalDateTime.of(2022, 5, 26, 21, 0))
                .movieDto(MovieDto.builder()
                        .id(1L)
                        .rating(Rating.FIVE)
                        .name("Orest").build())
                .build();
    }

    public static Order getOrder() {
        return Order.builder()
                .id(1L)
                .completed(true)
                .ownerFirstName("Orest")
                .ownerLastName("Mahdziak")
                .price(BigDecimal.ONE)
                .createdDate(LocalDateTime.of(2022, 5, 26, 10, 0))
                .expiredDate(LocalDateTime.of(2022, 5, 26, 21, 0))
                .movie(Movie.builder()
                        .id(1L)
                        .name("Orest")
                        .rating(Rating.FIVE)
                        .build())
                .build();
    }

    public static Movie getMovie() {
        return Movie.builder()
                .id(1L)
                .name("Harry Potter")
                .rating(Rating.FIVE)
                .build();
    }

    public static MovieDto getMovieDto() {
        return MovieDto.builder()
                .id(1L)
                .name("Harry Potter")
                .rating(Rating.FIVE)
                .build();
    }

    public static MovieMetadata getMovieMetadata() {
        return MovieMetadata.builder()
                .id(1L)
                .name("Harry Potter")
                .rating(Rating.FIVE)
                .build();
    }

    public static OrderDto getOrderDto() {
        return OrderDto.builder()
                .id(1L)
                .completed(true)
                .ownerFirstName("Orest")
                .ownerLastName("Mahdziak")
                .price(BigDecimal.ONE)
                .createdDate(LocalDateTime.of(2022, 5, 26, 10, 0))
                .expiredDate(LocalDateTime.of(2022, 5, 26, 21, 0))
                .build();
    }

    public static OrderSearchCriteria getOrderSearchCriteria() {
        return OrderSearchCriteria.builder()
                .id(1L)
                .completed(true)
                .ownerFirstName("Orest")
                .ownerLastName("Mahdziak")
                .price(BigDecimal.ONE)
                .createdDate(LocalDateTime.of(2022, 5, 26, 10, 0))
                .expiredDate(LocalDateTime.of(2022, 5, 26, 21, 0))
                .build();
    }

    public static OrderDto getOrderDtoToUpdate() {
        return OrderDto.builder()
                .id(1L)
                .completed(true)
                .ownerFirstName("Orest2")
                .ownerLastName("Mahdziak2")
                .price(BigDecimal.ONE)
                .createdDate(LocalDateTime.of(2022, 5, 26, 10, 0))
                .expiredDate(LocalDateTime.of(2022, 5, 26, 21, 0))
                .build();
    }

    public static Order getOrderToUpdate() {
        return Order.builder()
                .id(1L)
                .completed(true)
                .ownerFirstName("Orest2")
                .ownerLastName("Mahdziak2")
                .price(BigDecimal.ONE)
                .createdDate(LocalDateTime.of(2022, 5, 26, 10, 0))
                .expiredDate(LocalDateTime.of(2022, 5, 26, 21, 0))
                .build();
    }

    public static OrderPage getOrderPage() {
        return OrderPage.builder()
                .pageNumber(1)
                .pageSize(10)
                .sortBy("id")
                .sortDirection(Sort.Direction.ASC)
                .build();
    }
}
