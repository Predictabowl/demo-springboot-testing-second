package com.example.spring.testing.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring.testing.demo.domain.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	Employee findByName(String string);

	List<Employee> findByNameAndSalary(String name, long salary);

	List<Employee> findByNameOrSalary(String name, long salary);

	@Query("SELECT e from Employee e where e.salary > :threshold")
	List<Employee> findAllEmployeesWithLowSalary(@Param("threshold") long salary);

}
