package com.example.spring.testing.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.testing.demo.domain.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
