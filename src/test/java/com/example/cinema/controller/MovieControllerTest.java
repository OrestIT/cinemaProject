package com.example.cinema.controller;

import com.example.cinema.presentation.controller.MovieController;
import com.example.cinema.presentation.dto.MovieDto;
import com.example.cinema.services.MovieService;
import com.example.cinema.utils.TestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MovieControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController controller;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    private final String apiLink = "/api/cinema/movie";

    @Test
    @SneakyThrows
    void shouldCreateMovieTest() {
        MovieDto movieDto = TestModel.getMovieDto();
        String request = objectMapper.writeValueAsString(movieDto);

        this.mockMvc.perform(post(this.apiLink)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    void shouldGetOrderById() {
        mockMvc.perform(get(this.apiLink + "/1")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

        verify(movieService).getById(1L);
    }

    @Test
    @SneakyThrows
    void shouldGetByParam() {
        mockMvc.perform(get(this.apiLink + "/param?ownerLastName=Orest&pageNumber=1&pageSize=2"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldUpdateOrder() {
        MovieDto movieDto = TestModel.getMovieDto();
        String requestBody = objectMapper.writeValueAsString(movieDto);

        mockMvc.perform(put(this.apiLink + "/1")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void shouldDeleteById() {
        mockMvc.perform(delete(this.apiLink + "/1" )).andExpect(status().isOk());

        verify(movieService).delete(1L);
    }
}
