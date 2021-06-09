package com.example.spring.testing.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.spring.testing.demo.domain.model.Employee;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository repository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	void firstLearningTest() {
		Employee employee = new Employee(2L, "test", 1000);
		Employee saved = repository.save(employee);
		Collection<Employee> employees = repository.findAll();
		assertThat(employees).containsExactly(saved);
	}

	@Test
	void secondLearningTest() {
		Employee employee = new Employee(null, "test", 1500);
		Employee saved = entityManager.persistAndFlush(employee);
		Collection<Employee> employees = repository.findAll();

		assertThat(employees).containsExactly(saved);
		assertThat(employee.getId()).isEqualTo(saved.getId());
	}

	@Test
	void test_findByEmployeeName() {
		Employee saved = entityManager.persistAndFlush(new Employee(null, "someone", 1010));
		Employee found = repository.findByName("someone");

		assertThat(found).isEqualTo(saved);
	}

	@Test
	void test_findByEmployee_name_and_salary() {
		entityManager.persistAndFlush(new Employee(null, "someone", 1010));
		Employee saved = entityManager.persistAndFlush(new Employee(null, "someone", 1210));
		
		List<Employee> employees = repository.findByNameAndSalary("someone",1210);
		
		assertThat(employees).containsExactly(saved);
	}
	
	@Test
	void test_findByEmployee_name_or_salary() {
		Employee saved1 = entityManager.persistAndFlush(new Employee(null, "someone", 1010));
		Employee saved2 = entityManager.persistAndFlush(new Employee(null, "soemone else", 1210));
		
		List<Employee> employees = repository.findByNameOrSalary("someone",1210);
		
		assertThat(employees).containsExactly(saved1,saved2);
	}
	
	@Test
	void test_findAllEmployees_with_low_salary() {
		Employee saved1 = entityManager.persistAndFlush(new Employee(null, "someone", 1000));
		Employee saved2 = entityManager.persistAndFlush(new Employee(null, "soemone else", 2010));
		
		List<Employee> employees = repository.findAllEmployeesWithLowSalary(1500L);
		
		assertThat(employees).containsExactly(saved2);
	}

}
