package com.example.spring.testing.demo.service;

import java.util.List;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.domain.model.EmployeeId;
import com.example.spring.testing.demo.repository.EmployeeRepository;

public class EmployeeService {
	
	private static final String TEMPORARY_IMPLEMENTATION = "Temporary Implementation";
	
	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getAllEmployees(){
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
	
	public Employee getEmployeeById(EmployeeId id) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
	
	public Employee insertNewEmployee(Employee employee) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
	
	public Employee updateEmployeeById(EmployeeId id, Employee replacement) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
}
