package dev.jgyekye.caseworkers_task_backend.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "ct")
public record CaseWorkerTaskProperties(String welcomeMessage, String about ) {
}
