package com.example.spring.testing.demo.service;

import java.util.List;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.repository.EmployeeRepository;

public class EmployeeService {
	
	private static final String TEMPORARY_IMPLEMENTATION = "Temporary Implementation";
	
	public EmployeeService(EmployeeRepository employeeRepository) {
	}
	
	public List<Employee> getAllEmployees(){
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
	
	public Employee getEmployeeById() {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
	
	public Employee insertNewEmployee(Employee employee) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
	
	public Employee updateEmployeeById(Long id, Employee replacement) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
}
