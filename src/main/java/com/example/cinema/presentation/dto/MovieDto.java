package com.example.cinema.presentation.dto;

import com.example.cinema.presentation.enums.Rating;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class MovieDto {
    private Long id;
    private String name;
    private Rating rating;
}