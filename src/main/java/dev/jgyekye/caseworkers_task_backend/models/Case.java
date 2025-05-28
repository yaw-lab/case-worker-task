package dev.jgyekye.caseworkers_task_backend.models;

import jakarta.persistence.*;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "cases")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Case number is required")
    private String caseNumber;

    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @Column(nullable = false)
    private String status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Schema(description = "Timestamp when the case was created", example = "2024-05-28T10:30:00")
    private LocalDateTime createdDate;
}