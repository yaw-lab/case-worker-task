package dev.jgyekye.caseworkers_task_backend.controllers;

import dev.jgyekye.caseworkers_task_backend.models.Case;
import dev.jgyekye.caseworkers_task_backend.repositories.CaseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cases")
@Tag(name = "Cases", description = "API for managing Case entities") // Tag for grouping endpoints
public class CaseController {

    private final CaseRepository repository;

    public CaseController(CaseRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Create a new case", description = "Adds a new case to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Case created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Case.class))),
            @ApiResponse(responseCode = "400", description = "Invalid case data provided",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = dev.jgyekye.caseworkers_task_backend.exceptions.ExceptionResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Case> createCase(@RequestBody Case newCase) {
        Case savedCase = repository.save(newCase);
        return ResponseEntity.ok(savedCase);
    }

    @Operation(summary = "Get case by ID", description = "Retrieves a single case by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the case",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Case.class))),
            @ApiResponse(responseCode = "404", description = "Case not found with the given ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = dev.jgyekye.caseworkers_task_backend.exceptions.ExceptionResponse.class))) // Reference your ErrorResponse
    })
    @GetMapping("/{id}")
    public ResponseEntity<Case> getCaseById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all cases", description = "Retrieves a list of all available cases in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of cases",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Case.class))) // Describe response content
    @GetMapping
    public ResponseEntity<List<Case>> getAllCases() {
        return ResponseEntity.ok(repository.findAll());
    }

    @Operation(summary = "Partially update a case", description = "Updates specific fields of an existing case by its ID. Only provided fields will be updated.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Case updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Case.class))),
            @ApiResponse(responseCode = "400", description = "Invalid update data provided",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = dev.jgyekye.caseworkers_task_backend.exceptions.ExceptionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Case not found with the given ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = dev.jgyekye.caseworkers_task_backend.exceptions.ExceptionResponse.class)))
    })
    @PatchMapping("/{id}/status")
    public ResponseEntity<Case> updateCaseStatus(
            @PathVariable int id,
            @RequestBody Map<String, String> updates
    ) {
        return repository.findById(id)
                .map(exampleCase -> {
                    exampleCase.setStatus(updates.get("status"));
                    return ResponseEntity.ok(repository.save(exampleCase));
                })
                .orElse(ResponseEntity.<Case>notFound().build());
    }

    @Operation(summary = "Delete a case by ID", description = "Deletes a case from the system using its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Case deleted successfully (No Content)"),
            @ApiResponse(responseCode = "404", description = "Case not found with the given ID",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = dev.jgyekye.caseworkers_task_backend.exceptions.ExceptionResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCase(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

