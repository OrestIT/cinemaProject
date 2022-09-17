package com.example.cinema.services;

import com.example.cinema.dao.filters.criteriaData.MovieSearchCriteria;
import com.example.cinema.dao.filters.page.MoviePage;
import com.example.cinema.presentation.dto.MovieDto;
import com.example.cinema.presentation.dto.metadata.MovieMetadata;
import org.springframework.data.domain.Page;

public interface MovieService {
    MovieMetadata getById(Long id);

    MovieDto create(MovieDto movie);

    MovieDto update(MovieDto movie, Long id);

    void delete(Long id);

    Page<MovieMetadata> getByParam(MovieSearchCriteria movieSearchCriteria, MoviePage moviePage);
}