package com.example.cinema.services.impl;

import com.example.cinema.dao.filters.criteriaData.MovieSearchCriteria;
import com.example.cinema.dao.filters.page.MoviePage;
import com.example.cinema.dao.filters.specification.MovieSpecification;
import com.example.cinema.dao.model.Movie;
import com.example.cinema.dao.repository.MovieRepository;
import com.example.cinema.presentation.dto.MovieDto;
import com.example.cinema.presentation.dto.metadata.MovieMetadata;
import com.example.cinema.services.MovieService;
import com.example.cinema.services.exception.movie.MovieDeleteException;
import com.example.cinema.services.exception.movie.MovieNotFoundException;
import com.example.cinema.services.mapper.MovieMapper;
import com.example.cinema.services.utils.Constants;
import lombok.RequiredArgsConstructor;
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

import static com.example.cinema.services.utils.Constants.CAN_NOT_DELETE_MOVIE;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;

    @Override
    public MovieMetadata getById(Long id) {
        Movie currentMovie = this.movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(Constants.CAN_NOT_FIND_MOVIE + id));
        return this.movieMapper.toMetadata(currentMovie);
    }

    @Override
    public MovieDto create(MovieDto movie) {
        Movie savedMovie = this.movieRepository.save(this.movieMapper.toModel(movie));
        return this.movieMapper.toDto(savedMovie);
    }

    @Override
    public MovieDto update(MovieDto movie, Long id) {
        Movie currentMovie = this.movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException(Constants.CAN_NOT_FIND_MOVIE + id));

        this.movieMapper.updateMovieFromDto(movie, currentMovie);

        return this.movieMapper.toDto(this.movieRepository.save(currentMovie));
    }

    @Override
    public void delete(Long id) {
        try {
            this.movieRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new MovieDeleteException(CAN_NOT_DELETE_MOVIE + id);
        }
    }

    @Override
    public Page<MovieMetadata> getByParam(MovieSearchCriteria movieSearchCriteria, MoviePage moviePage) {
        Specification<Movie> movieSpecification = MovieSpecification.buildMovieSpecification(movieSearchCriteria);
        List<MovieMetadata> movies = this.movieRepository.findAll(movieSpecification).stream()
                .map(this.movieMapper::toMetadata)
                .collect(Collectors.toList());

        return new PageImpl<>(movies, PageRequest.of(moviePage.getPageNumber(), moviePage.getPageSize(),
                Sort.by(Objects.isNull(moviePage.getSortBy()) ? "id" : moviePage.getSortBy())), movies.size());
    }
}
