package com.ems.employeemanagement.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ems.employeemanagement.dto.EmployeeDto;
import com.ems.employeemanagement.entity.Employee;
import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.mapper.EmployeeMapper;
import com.ems.employeemanagement.repository.EmployeeRepository;
import com.ems.employeemanagement.service.EmployeeService;


@Service


public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
		Employee savedEmployee = employeeRepo.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(long employeeId) {
		Employee employee = employeeRepo.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("id not found"+employeeId));
		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
		List<Employee> eList = employeeRepo.findAll();
		return eList.stream()
				.map((employee)->EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(long employeeId, EmployeeDto updatedDetails) {
		Employee employee =  employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("no id found :"+employeeId));
		employee.setFirstName(updatedDetails.getFirstName());
		employee.setLastName(updatedDetails.getLastName());
		employee.setEmail(updatedDetails.getEmail());
		employee.setAge(updatedDetails.getAge());
		
		Employee savedEmployee = employeeRepo.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public void deleteEmployee(long employeeId) {
		employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("no id to delete "+employeeId));
		employeeRepo.deleteById(employeeId);
	}

}
