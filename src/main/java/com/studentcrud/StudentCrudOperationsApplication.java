package com.studentcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class StudentCrudOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCrudOperationsApplication.class, args);
	}

}
