package com.example.cinema.dao.filters.specification;

import com.example.cinema.dao.filters.criteriaData.MovieSearchCriteria;
import com.example.cinema.dao.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class MovieSpecification {
    public static Specification<Movie> buildMovieSpecification(MovieSearchCriteria movieSearchCriteria) {
       return Specification.where(hasId(movieSearchCriteria.getId()))
                .and(hasName(movieSearchCriteria.getName()))
                .and(hasRating(movieSearchCriteria.getRating()));
    }

    private static Specification<Movie> hasId(Long id) {
        return (root, query, criteriaBuilder) -> Objects.isNull(id) ?
                criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("id"), id);
    }

    private static Specification<Movie> hasName(String name) {
        return (root, query, criteriaBuilder) -> Objects.isNull(name) ?
                criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Movie> hasRating(String rating) {
        return (root, query, criteriaBuilder) -> Objects.isNull(rating) ?
                criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("rating"), rating);
    }
}
