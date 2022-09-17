package com.example.cinema.service;

import com.example.cinema.dao.model.Movie;
import com.example.cinema.dao.repository.MovieRepository;
import com.example.cinema.presentation.dto.MovieDto;
import com.example.cinema.presentation.dto.metadata.MovieMetadata;
import com.example.cinema.services.exception.movie.MovieNotFoundException;
import com.example.cinema.services.impl.MovieServiceImpl;
import com.example.cinema.services.mapper.MovieMapper;
import com.example.cinema.services.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.cinema.utils.TestModel.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @Mock
    MovieMapper mapper;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Test
    void shouldGetMovieById() {
        MovieMetadata movieMetadata = getMovieMetadata();
        Movie movie = getMovie();

        when(mapper.toMetadata(movie)).thenReturn(movieMetadata);
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        assertEquals(movieMetadata, movieService.getById(1L));
    }

    @Test
    void getMovieByIdShouldThrowException() {
        when(movieRepository.findById(2L)).thenReturn(Optional.empty());

        Exception thrownException = assertThrows(MovieNotFoundException.class, () -> movieService.getById(2L));
        assertEquals(Constants.CAN_NOT_FIND_MOVIE + 2L, thrownException.getMessage());
    }

    @Test
    void shouldCreateMovie() {
        Movie movie = getMovie();
        MovieDto movieDto = getMovieDto();

        when(mapper.toModel(movieDto)).thenReturn(movie);
        when(mapper.toDto(movie)).thenReturn(movieDto);
        when(movieRepository.save(movie)).thenReturn(movie);
        movieService.create(movieDto);

        assertEquals(1L, movieDto.getId());
    }

    @Test
    void shouldUpdateMovie() {
        MovieDto movieDto = getMovieDto();
        Movie movie = getMovie();

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        when(movieRepository.save(movie)).thenReturn(movie);
        doNothing().when(mapper).updateMovieFromDto(movieDto, movie);
        movieService.update(movieDto, 1L);
        verify(mapper).updateMovieFromDto(movieDto, movie);
    }

    @Test
    void shouldDeleteOrder() {
        MovieDto movieDto = getMovieDto();

        movieService.delete(movieDto.getId());
        verify(movieRepository).deleteById(movieDto.getId());
    }
}