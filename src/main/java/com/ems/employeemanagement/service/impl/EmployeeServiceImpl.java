package com.ems.employeemanagement.service.impl;


import java.util.List;

import java.util.stream.Collectors;


import com.ems.employeemanagement.entity.Role;
import com.ems.employeemanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

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
		employee.setUsername(updatedDetails.getUsername());
		employee.setPassword(bCryptPasswordEncoder.encode(updatedDetails.getPassword()));
		
		Employee savedEmployee = employeeRepo.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public void deleteEmployee(long employeeId) {
		employeeRepo.findById(employeeId).orElseThrow(()->new ResourceNotFoundException("no id to delete "+employeeId));
		employeeRepo.deleteById(employeeId);
	}

	@Override
	public EmployeeDto login(EmployeeDto e) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(e.getUsername(),e.getPassword()));
		if(authentication.isAuthenticated()){
			Employee employee = employeeRepo.findByUsername(e.getUsername());
			EmployeeDto emp = EmployeeMapper.mapToEmployeeDto(employee);
			emp.setToken(jwtService.generateToken(e));
			System.out.println("printing"+jwtService.generateToken(e));
			return emp;
		}
		return null;
	}

	@Override
	public EmployeeDto register(EmployeeDto e) {
		e.setRole(Role.EMPLOYEE);
		e.setPassword(bCryptPasswordEncoder.encode(e.getPassword()));
		Employee emp = EmployeeMapper.mapToEmployee(e);
		return EmployeeMapper.mapToEmployeeDto(employeeRepo.save(emp));
	}

}
