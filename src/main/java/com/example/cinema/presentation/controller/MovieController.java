package com.example.cinema.presentation.controller;

import com.example.cinema.dao.filters.criteriaData.MovieSearchCriteria;
import com.example.cinema.dao.filters.page.MoviePage;
import com.example.cinema.presentation.dto.MovieDto;
import com.example.cinema.presentation.dto.metadata.MovieMetadata;
import com.example.cinema.services.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cinema/movie")
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDto> createMovie(@RequestBody MovieDto movie) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.movieService.create(movie));
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieMetadata> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok(this.movieService.getById(id));
    }

    @GetMapping("/param")
    public ResponseEntity<Page<MovieMetadata>> getByParam(MovieSearchCriteria movieSearchCriteria, MoviePage moviePage) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(movieService.getByParam(movieSearchCriteria, moviePage));
    }

    @PutMapping("{id}")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto movie, @PathVariable Long id) {
        return ResponseEntity.ok(this.movieService.update(movie, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteMovie(@PathVariable Long id) {
        this.movieService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}