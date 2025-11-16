package com.example.estatechecker;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class EstatecheckerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EstatecheckerApplication.class, args);
        log.info("Application running on: http://localhost:8080/swagger-ui/index.html#/");
	}

}
