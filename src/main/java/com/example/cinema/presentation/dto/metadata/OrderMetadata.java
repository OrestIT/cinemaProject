package com.example.cinema.presentation.dto.metadata;

import com.example.cinema.presentation.dto.MovieDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderMetadata {
    private Long id;
    private BigDecimal price;
    private String ownerFirstName;
    private String ownerLastName;
    private Boolean completed;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime expiredDate;
    private MovieDto movieDto;
}