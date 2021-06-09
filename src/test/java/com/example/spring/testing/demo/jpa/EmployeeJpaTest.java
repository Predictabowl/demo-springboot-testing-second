package com.example.spring.testing.demo.jpa;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.domain.model.EmployeeId;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class EmployeeJpaTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	void test_JpaMapping() {
		Employee saved = entityManager.persistAndFlush(new Employee(new EmployeeId(1L), "test", 1500));
		
		assertThat(saved.getName()).isEqualTo("test");
		assertThat(saved.getSalary()).isEqualTo(1500);
		assertThat(saved.getId()).isEqualTo(new EmployeeId(1L));
		
		LoggerFactory.getLogger(EmployeeJpaTest.class)
			.info("Saved: "+saved.toString());
	}
}
