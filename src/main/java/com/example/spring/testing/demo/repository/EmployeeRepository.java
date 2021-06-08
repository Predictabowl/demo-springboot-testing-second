package com.example.spring.testing.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.domain.model.EmployeeId;

@Repository
public class EmployeeRepository {
	
	private static final String TEMPORARY_IMPLEMENTATION = "Temporary Implementation";
	
	public List<Employee> findAll(){
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
	
	public Optional<Employee> findById(EmployeeId id){
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
	
	public Employee save(Employee employee) {
		throw new UnsupportedOperationException(TEMPORARY_IMPLEMENTATION);
	}
}
