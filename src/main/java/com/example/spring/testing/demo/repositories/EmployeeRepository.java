package com.example.spring.testing.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.domain.model.EmployeeId;

public interface EmployeeRepository extends JpaRepository<Employee, EmployeeId>{

}
