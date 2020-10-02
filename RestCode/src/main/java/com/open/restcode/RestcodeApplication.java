package com.open.restcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestcodeApplication.class, args);
    }

}
