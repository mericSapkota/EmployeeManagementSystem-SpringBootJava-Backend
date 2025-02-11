package com.ems.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.employeemanagement.dto.EmployeeDto;

import com.ems.employeemanagement.service.impl.EmployeeServiceImpl;

@RestController
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	
	@PostMapping("/api/employee")
	public ResponseEntity<EmployeeDto> saveEmploye(@RequestBody EmployeeDto emp){
		if(emp.getFirstName()==null) {
			return new ResponseEntity<>(emp,HttpStatus.BAD_REQUEST);
		}
		EmployeeDto savedEmployee = employeeService.saveEmployee(emp);
		return  new ResponseEntity<>(savedEmployee,HttpStatus.CREATED);
	}
	
	@GetMapping("/api/employee/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") long employeeId){
		EmployeeDto returnedEmployee = employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(returnedEmployee);
	}
	
	@GetMapping("/ap/employee")
	public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
		List<EmployeeDto> allEmployee = employeeService.getAllEmployee();
		return ResponseEntity.ok(allEmployee);
	}
	
	@PutMapping("/api/employee/{id}")
	public ResponseEntity<EmployeeDto> updateEmployeeDetails(@PathVariable("id") long employeeId, @RequestBody EmployeeDto updatedDetails){
		EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, updatedDetails);
		return ResponseEntity.ok(updatedEmployee);
	}
	@DeleteMapping("/api/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId ){
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("success");
	}

	
	@GetMapping("/")
	public String hello() {
		return "Hello";
	}
	
	@PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
	@GetMapping("/user")
	public String userEndPoint() {
		return "Hello user";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String adminEndPoint() {
		return "Hello Admin";
	}
}
