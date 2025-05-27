package dev.jgyekye.caseworkers_task_backend.models;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "cases")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    private LocalDateTime createdDate;

    @PrePersist
    public void onCreate() {
        this.createdDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "ExampleCase{" +
                "id=" + id +
                ", caseNumber='" + caseNumber + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}