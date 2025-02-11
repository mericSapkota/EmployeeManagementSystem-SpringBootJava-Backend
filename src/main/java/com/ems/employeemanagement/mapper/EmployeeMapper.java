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
				e.getAge()
				);
	} 
	
	public static EmployeeDto mapToEmployeeDto(Employee e) {
		return new EmployeeDto(
				e.getId(),
				e.getFirstName(),
				e.getLastName(),
				e.getEmail(),
				e.getUsername(),
				e.getPassword(),
				e.getAge()
				);
	}

}
