package com.example.spring.testing.demo.domain.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Employee {
	
	/* We cannot autogenerate ID with an embedded ID,
	 * if we want to use a auto generated ID we should 
	 * soimply switch to Long.
	 * This is just kept it as an embeddedID example
	 * 
	 * it's worth to notice that in the other branches we
	 * assumes that the ID is auto generated, which of course will
	 * cause a mess when we merge.
	 * 
	 */
	
	@EmbeddedId
	private EmployeeId id;
	private String name;
	private long salary;
	
	public Employee() {}
	

	public Employee(EmployeeId id, String name, long salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}
	
	public void setId(EmployeeId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public EmployeeId getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + "]";
	}
	
}
