package com.example.cinema.integration.controller;

import com.example.cinema.presentation.dto.OrderDto;
import com.example.cinema.utils.TestModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Sql(scripts = "/sql/order/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/order/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private final String apiLink = "/api/cinema/order";
    private static ObjectMapper objectMapper;

    @BeforeAll
    static void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    @SneakyThrows
    public void createOrderTest() {
        OrderDto orderDto = TestModel.getOrderDto();
        String request = objectMapper.writeValueAsString(orderDto);

        this.mockMvc.perform(post(this.apiLink)
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @SneakyThrows
    public void deleteOrderTest() {
        this.mockMvc.perform(delete(this.apiLink + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void updateOrderTest() {
        OrderDto orderDto = TestModel.getOrderDtoToUpdate();
        String request = objectMapper.writeValueAsString(orderDto);

        this.mockMvc.perform(put(this.apiLink + "/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void getOrderByIdTest() {
        this.mockMvc.perform(get(this.apiLink + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void getByParamTest() {
        this.mockMvc.perform(get(this.apiLink + "/param?ownerLastName=Orest&pageNumber=1&pageSize=2"))
                .andExpect(status().isOk());
    }
}