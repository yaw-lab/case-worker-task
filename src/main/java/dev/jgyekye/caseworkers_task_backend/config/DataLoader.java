package dev.jgyekye.caseworkers_task_backend.config;

import java.io.InputStream;
import java.util.List;

import dev.jgyekye.caseworkers_task_backend.models.Case;
import dev.jgyekye.caseworkers_task_backend.repositories.CaseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


/*
 *  DataLoader class is responsible for initializing the content database by
 * loading data from a specified JSON file when the Spring Boot application
 * is started, but only if the repository currently contains no data.
 * This is a common pattern for seeding a database with initial data.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final CaseRepository repository;
    private final ObjectMapper objectMapper;

    public DataLoader(CaseRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataLoading from: +++++++++++++++ /data/case.json ");
        if(repository.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/case.json")) {
                repository.saveAll(objectMapper.readValue(inputStream,new TypeReference<List<Case>>(){}));
                System.out.println("DataLoading :&&&&&&&&&&&&&&&&&&&&&&&&& ");
            }
        }
    }

}