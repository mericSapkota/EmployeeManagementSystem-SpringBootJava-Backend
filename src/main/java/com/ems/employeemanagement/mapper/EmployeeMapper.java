package com.ems.employeemanagement.mapper;

import com.ems.employeemanagement.dto.EmployeeDto;
import com.ems.employeemanagement.entity.Employee;

public class EmployeeMapper {
	
	public static Employee mapToEmployee(EmployeeDto e) {
		System.out.println(e.getId());
		return new Employee(
//				e.getId()!=0?e.getId():null,
				e.getId(),
				e.getFirstName(),
				e.getLastName(),
				e.getEmail(),
				e.getUsername(),
				e.getPassword(),
				e.getAge(),
				e.getRole()
				);
	} 
	
	public static EmployeeDto mapToEmployeeDto(Employee emp) {
		EmployeeDto e = new EmployeeDto();
		e.setId(emp.getId());
		e.setRole(emp.getRole());
		e.setAge(emp.getAge());
		e.setEmail(emp.getEmail());
		e.setUsername(emp.getUsername());
		e.setPassword(emp.getPassword());
		e.setFirstName(emp.getFirstName());
		e.setLastName(emp.getLastName());
		return e;
	}

}
