package dev.jgyekye.caseworkers_task_backend.controllers;

import dev.jgyekye.caseworkers_task_backend.config.CaseWorkerTaskProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    private final CaseWorkerTaskProperties properties;
    public RootController(CaseWorkerTaskProperties properties){
        this.properties = properties;
    }

    @GetMapping("/")
    public CaseWorkerTaskProperties welcome() {
        return properties;
    }
}