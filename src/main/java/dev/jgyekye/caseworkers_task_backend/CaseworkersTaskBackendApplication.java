package dev.jgyekye.caseworkers_task_backend;

import dev.jgyekye.caseworkers_task_backend.config.CaseWorkerTaskProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CaseWorkerTaskProperties.class)
public class CaseworkersTaskBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseworkersTaskBackendApplication.class, args);
	}

}
