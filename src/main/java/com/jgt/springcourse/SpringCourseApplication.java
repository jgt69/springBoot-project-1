package com.jgt.springcourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // Enables Spring's asynchronous method execution capability
public class SpringCourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCourseApplication.class, args);
    }

}
