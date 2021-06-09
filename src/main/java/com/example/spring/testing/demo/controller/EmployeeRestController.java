package com.example.spring.testing.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.domain.model.EmployeeId;
import com.example.spring.testing.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> allEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee oneEmployee(@PathVariable long id) {
		/* this is wrong, it should be the service layer to create the id,
		 * also the model for the web layer should be simpler and different
		 * than the domain model.
		 * Anyway this is just a learning test
		 */
		return employeeService.getEmployeeById(new EmployeeId(id));
	}
	
	@PostMapping("/new")
	public Employee newEmployee(@RequestBody Employee employee) {
		return employeeService.insertNewEmployee(employee);
	}
	
	@PutMapping("/update/{id}")
	public Employee updateEmployee(@PathVariable long id, @RequestBody Employee employee) {
		return employeeService.updateEmployeeById(new EmployeeId(id), employee);
	}
}
