package dev.jgyekye.caseworkers_task_backend.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import dev.jgyekye.caseworkers_task_backend.config.CaseWorkerTaskProperties;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RootController.class)
class RootControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CaseWorkerTaskProperties properties;

    @BeforeEach
    void setUp() {
        // When the controller calls properties, etc.,
        // we'll tell the mock what values to return.
        when(properties.welcomeMessage()).thenReturn("Welcome to Case Worker Task Manager!");
        when(properties.about()).thenReturn("Case Worker Task Manager!");

    }

    @Test
    void welcomeReturnsProperties() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.welcomeMessage").value("Welcome to Case Worker Task Manager!"))
                .andExpect(jsonPath("$.about").value("Case Worker Task Manager!"));

    }
}