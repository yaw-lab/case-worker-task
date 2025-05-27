package dev.jgyekye.caseworkers_task_backend.repositories;

import dev.jgyekye.caseworkers_task_backend.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CaseRepository extends JpaRepository<Case, Integer> {
}