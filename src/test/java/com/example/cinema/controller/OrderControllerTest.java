package com.example.cinema.controller;

import com.example.cinema.presentation.controller.OrderController;
import com.example.cinema.presentation.dto.OrderDto;
import com.example.cinema.services.OrderService;
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
public class OrderControllerTest {
    private MockMvc mockMvc;
    private static ObjectMapper objectMapper;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController controller;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    private final String apiLink = "/api/cinema/order";

    @Test
    @SneakyThrows
    void shouldCreateOrderTest() {
        OrderDto orderDto = TestModel.getOrderDto();
        String request = objectMapper.writeValueAsString(orderDto);

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

        verify(orderService).getById(1L);
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
        OrderDto orderToUpdate = TestModel.getOrderDtoToUpdate();
        String requestBody = objectMapper.writeValueAsString(orderToUpdate);

        mockMvc.perform(put(this.apiLink + "/1")
                        .content(requestBody)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(orderService).update(orderToUpdate, orderToUpdate.getId());
    }

    @Test
    @SneakyThrows
    void shouldDeleteById() {
        mockMvc.perform(delete(this.apiLink + "/1" )).andExpect(status().isOk());

        verify(orderService).delete(1L);
    }

}
