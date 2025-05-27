package dev.jgyekye.caseworkers_task_backend.controllers;

import dev.jgyekye.caseworkers_task_backend.models.Case;
import dev.jgyekye.caseworkers_task_backend.repositories.CaseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController // This activates the /cases base path
@RequestMapping("/cases")
public class CaseController {

    private final CaseRepository repository;

    public CaseController(CaseRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Case> createCase(@RequestBody Case newCase) {
        System.out.println("Received: ********** XXXXXXXXXXXXXXXXXX ");
        System.out.println("Received: ********** XXXXXXXXXXXXXXXXXXXXXXXXXXXXX" + newCase.toString());
        Case savedCase = repository.save(newCase);
        return ResponseEntity.ok(savedCase);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Case> getCaseById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Case>> getAllCases() {
        return ResponseEntity.ok(repository.findAll());
    }

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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCase(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}