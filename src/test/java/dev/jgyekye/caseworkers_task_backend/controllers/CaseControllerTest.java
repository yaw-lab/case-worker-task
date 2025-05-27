package dev.jgyekye.caseworkers_task_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.jgyekye.caseworkers_task_backend.models.Case;
import dev.jgyekye.caseworkers_task_backend.repositories.CaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CaseController.class)
class CaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @SuppressWarnings("removal")
    @MockBean
    private CaseRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void testCreateCase() throws Exception {
        Case newCase = new Case(1, "C123", "Test", "Description", "OPEN", LocalDateTime.now());
        when(repository.save(any())).thenReturn(newCase);

        mockMvc.perform(post("/cases")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"caseNumber\":\"C123\",\"title\":\"Test\",\"status\":\"OPEN\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.caseNumber").value("C123"));
    }

    @Test
    void testGetCaseById_found() throws Exception {
        Case existingCase = new Case(1, "C124", "Test 2", "Desc", "OPEN", LocalDateTime.now());
        when(repository.findById(1)).thenReturn(Optional.of(existingCase));

        mockMvc.perform(get("/cases/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.caseNumber").value("C124"));
    }

    @Test
    void testGetCaseById_notFound() throws Exception {
        when(repository.findById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/cases/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllCases() throws Exception {
        List<Case> cases = List.of(
                new Case(1, "C125", "Title1", "Desc1", "OPEN", LocalDateTime.now()),
                new Case(2, "C126", "Title2", "Desc2", "CLOSED", LocalDateTime.now())
        );
        when(repository.findAll()).thenReturn(cases);

        mockMvc.perform(get("/cases"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testUpdateCaseStatus() throws Exception {
        Case existingCase = new Case(1, "C123", "Title", "Desc", "OPEN", LocalDateTime.now());
        when(repository.findById(1)).thenReturn(Optional.of(existingCase));
        when(repository.save(any())).thenReturn(existingCase);

        mockMvc.perform(patch("/cases/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"CLOSED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CLOSED"));
    }

    @Test
    void testUpdateCaseStatus_notFound() throws Exception {
        when(repository.findById(99)).thenReturn(Optional.empty());

        mockMvc.perform(patch("/cases/99/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"status\":\"CLOSED\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteCase_success() throws Exception {
        when(repository.existsById(1)).thenReturn(true);
        doNothing().when(repository).deleteById(1);

        mockMvc.perform(delete("/cases/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteCase_notFound() throws Exception {
        when(repository.existsById(99)).thenReturn(false);

        mockMvc.perform(delete("/cases/99"))
                .andExpect(status().isNotFound());
    }
}