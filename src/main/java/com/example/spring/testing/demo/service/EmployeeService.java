package com.example.spring.testing.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.domain.model.EmployeeId;
import com.example.spring.testing.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	public Employee getEmployeeById(EmployeeId id) {
		return employeeRepository.findById(id).orElse(null);
	}
	
	public Employee insertNewEmployee(Employee employee) {
		employee.setId(null);
		return employeeRepository.save(employee);
	}
	
	public Employee updateEmployeeById(EmployeeId id, Employee replacement) {
		replacement.setId(id);
		return employeeRepository.save(replacement);
	}
}
