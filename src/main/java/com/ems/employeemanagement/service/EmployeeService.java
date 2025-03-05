package com.ems.employeemanagement.service;


import java.util.List;

import com.ems.employeemanagement.dto.EmployeeDto;


public interface EmployeeService {
	
	EmployeeDto saveEmployee(EmployeeDto employeeDto);
	EmployeeDto getEmployeeById(long employeeId);
	List<EmployeeDto> getAllEmployee();
	EmployeeDto updateEmployee(long employeeId, EmployeeDto updateDetails);
	void deleteEmployee(long employeeId);

    EmployeeDto login(EmployeeDto e);

	EmployeeDto register(EmployeeDto e);
}
