package com.example.spring.testing.demo.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.domain.model.EmployeeId;
import com.example.spring.testing.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EmployeeRestController.class)
class EmployeeRestControllerTest {

	private static final String FIXTURE_API_EMPLOYEES = "/api/employees";

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private EmployeeService employeeService;
	
	@Test
	void test_allEmployees_Empty() throws Exception {
		this.mvc.perform(get(FIXTURE_API_EMPLOYEES).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json("[]"));
	}

	@Test
	void test_allEmployees_notEmpty() throws Exception {
		when(employeeService.getAllEmployees())
			.thenReturn(Arrays.asList(
					new Employee(new EmployeeId(1L), "first", 1000),
					new Employee(new EmployeeId(2L), "second", 5000)));
		
		this.mvc.perform(get(FIXTURE_API_EMPLOYEES).accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[0].id.id", is(1)))
			.andExpect(jsonPath("$[0].name", is("first")))
			.andExpect(jsonPath("$[0].salary", is(1000)))
			.andExpect(jsonPath("$[1].id.id", is(2)))
			.andExpect(jsonPath("$[1].name", is("second")))
			.andExpect(jsonPath("$[1].salary", is(5000)))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	void test_oneEmployeeById_with_no_employees() throws Exception {
		when(employeeService.getEmployeeById(isA(EmployeeId.class)))
		.thenReturn(null);
		
		this.mvc.perform(get(FIXTURE_API_EMPLOYEES+"/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().string(""));
		
	}
	
	@Test
	void test_oneEmployeeById_with_existing_employee() throws Exception {
		when(employeeService.getEmployeeById(isA(EmployeeId.class)))
			.thenReturn(new Employee(new EmployeeId(1L), "first", 1000));
		
		this.mvc.perform(get(FIXTURE_API_EMPLOYEES+"/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id.id", is(1)))
				.andExpect(jsonPath("$.name", is("first")))
				.andExpect(jsonPath("$.salary", is(1000)));
	}
	
	@Test
	void test_insertEmployee() throws Exception {
		Employee requestBody = new Employee(null, "first", 1000);
		when(employeeService.insertNewEmployee(requestBody ))
			.thenReturn(new Employee(new EmployeeId(1L), "first", 1000));
		
		//To convert the object into a json we use Jackson ObjectMapper
		String jsonBody = new ObjectMapper().writeValueAsString(requestBody);
		
		this.mvc.perform(post(FIXTURE_API_EMPLOYEES+"/new")
			.contentType(MediaType.APPLICATION_JSON_VALUE)
			.content(jsonBody))
			.andExpect(status().isOk())
			.andExpect(jsonPath("id.id", is(1)))
			.andExpect(jsonPath("$.name", is("first")))
			.andExpect(jsonPath("$.salary", is(1000)));
	}
	
	
	@Test
	void test_updateEmployee() throws Exception {
		Employee requestBody = new Employee(null, "first", 1000);
		String jsonBody = new ObjectMapper().writeValueAsString(requestBody);
		when(employeeService.updateEmployeeById(new EmployeeId(2L), requestBody))
			.thenReturn(new Employee(new EmployeeId(2L), "someone else", 2000));
		
		this.mvc.perform(put(FIXTURE_API_EMPLOYEES+"/update/2")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(jsonBody))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id.id", is(2)))
			.andExpect(jsonPath("$.name", is("someone else")))
			.andExpect(jsonPath("$.salary", is(2000)));
	}
}
