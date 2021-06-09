package com.example.spring.testing.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.spring.testing.demo.domain.model.Employee;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Test
	void firstLearningTest() {
		Employee employee = new Employee(2L, "test", 1000);
		Employee saved = repository.save(employee);
		Collection<Employee> employees = repository.findAll();
		assertThat(employees).containsExactly(saved);
	}

}
