package com.example.spring.testing.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.spring.testing.demo.domain.model.Employee;
import com.example.spring.testing.demo.domain.model.EmployeeId;
import com.example.spring.testing.demo.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceWithMockitoTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeService employeeService;
	
	@Test
	void test_getAllEmployees() {
		Employee employee1 = new Employee(new EmployeeId(1L),"first", 1000);
		Employee employee2 = new Employee(new EmployeeId(2L),"second", 1000);
		
		when(employeeRepository.findAll())
			.thenReturn(Arrays.asList(employee1,employee2));
		
		assertThat(employeeService.getAllEmployees())
			.containsExactly(employee1,employee2);
	}
	
	@Test
	void test_getEmployeeById_Found() {
		EmployeeId id = new EmployeeId(1L);
		Employee employee = new Employee(id, "Employee", 1000);
		
		when(employeeRepository.findById(id))
			.thenReturn(Optional.of(employee));
		
		assertThat(employeeService.getEmployeeById(new EmployeeId(1L)))
			.isSameAs(employee);
	}
	
	@Test
	void test_getEmployeeById_not_found() {
		
		when(employeeRepository.findById(any(EmployeeId.class)))
			.thenReturn(Optional.empty());
		
		assertThat(employeeService.getEmployeeById(new EmployeeId(1L)))
			.isNull();
	}
	
	@Test
	void test_insertNewEmployee_setsIdNotNull_and_returnSAvedEmployee() {
		Employee toSave = spy(new Employee(new EmployeeId(99L), "", 0));
		Employee saved = new Employee(new EmployeeId(1L), "saved", 1000);
		when(employeeRepository.save(any(Employee.class)))
			.thenReturn(saved);
		
		Employee result = employeeService.insertNewEmployee(toSave);
		
		assertThat(result).isSameAs(saved);
		InOrder inOrder = inOrder(toSave,employeeRepository);
		inOrder.verify(toSave).setId(null);
		inOrder.verify(employeeRepository).save(toSave);
	}
	
	@Test
	void test_updateEmployeeById_setsIdToArgument_and_returnsSavedEmployee() {
		Employee replacement = spy(new Employee(null, "employee", 0));
		Employee replaced = new Employee(new EmployeeId(1L), "saved", 1000);
		when(employeeRepository.save(any(Employee.class)))
			.thenReturn(replaced);
		
		Employee result = employeeService.updateEmployeeById(new EmployeeId(1L), replacement);
		
		assertThat(result).isSameAs(replaced);
		InOrder inOrder = inOrder(replacement,employeeRepository);
		inOrder.verify(replacement).setId(new EmployeeId(1L));
		inOrder.verify(employeeRepository).save(replacement);
	}
	
}
