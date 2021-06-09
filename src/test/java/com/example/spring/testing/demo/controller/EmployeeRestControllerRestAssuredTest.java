package com.example.spring.testing.demo.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.domain.model.EmployeeId;
import com.example.spring.testing.demo.service.EmployeeService;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@ExtendWith(MockitoExtension.class)
class EmployeeRestControllerRestAssuredTest {
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeRestController employeeRestController;

	@BeforeEach
	void setUp() {
		RestAssuredMockMvc.standaloneSetup(employeeRestController);
	}
	
	@Test
	void test_findById_with_existing_employee(){
		when(employeeService.getEmployeeById(new EmployeeId(1L)))
			.thenReturn(new Employee(new EmployeeId(1L), "first", 1000));
		
		given()
		.when()
			.get("/api/employees/1")
		.then()
			.statusCode(200)
			.assertThat()
			.body("id.id", equalTo(1),
				"name", equalTo("first"),
				"salary", equalTo(1000)
		);
	}
	
	@Test
	void test_postEmployee() {
		Employee requestBodyEmployee = new Employee(null, "test", 1000);
		
		when(employeeService.insertNewEmployee(requestBodyEmployee))
			.thenReturn(new Employee(new EmployeeId(1L), "test", 1000));
		
		given()
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.body(requestBodyEmployee)
		.when()
			.post("/api/employees/new")
		.then()
			.statusCode(200)
			.body(
					"id.id", equalTo(1),
					"name", equalTo("test"),
					"salary", equalTo(1000)
		);	
	}
	
	@Test
	void test_updateEmployee() {
		Employee restBodyEmployee = new Employee(null, "test", 1000);
		when(employeeService.updateEmployeeById(new EmployeeId(1L),restBodyEmployee))
			.thenReturn(new Employee(new EmployeeId(1L), "test", 1000));
		
		given()
			.accept(MediaType.APPLICATION_JSON_VALUE)
			.body(restBodyEmployee)
		.when()
			.put("/api/employees/update/1")
		.then()
			.statusCode(200)
			.body(
				"id.id", equalTo(1),
				"name", equalTo("test"),
				"salary", equalTo(1000)
		);
	}

}
