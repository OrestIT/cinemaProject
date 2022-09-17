package com.example.cinema.services.mapper;

import com.example.cinema.dao.model.Movie;
import com.example.cinema.presentation.dto.MovieDto;
import com.example.cinema.presentation.dto.metadata.MovieMetadata;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto toDto(Movie movie);

    Movie toModel(MovieDto movieDto);

    MovieMetadata toMetadata(Movie movie);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMovieFromDto(MovieDto movieDto, @MappingTarget Movie movie);
}
