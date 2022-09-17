package com.example.cinema.presentation.dto.metadata;

import com.example.cinema.presentation.dto.OrderDto;
import com.example.cinema.presentation.enums.Rating;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MovieMetadata {
    private Long id;
    private String name;
    private Rating rating;
    private List<OrderDto> orders;
}
