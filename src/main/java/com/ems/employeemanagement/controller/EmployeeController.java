package com.ems.employeemanagement.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.ems.employeemanagement.entity.Employee;
import com.ems.employeemanagement.service.impl.AnnouncementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ems.employeemanagement.dto.EmployeeDto;

import com.ems.employeemanagement.service.impl.EmployeeServiceImpl;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeService;
	@Autowired
	private AnnouncementServiceImpl as;
	
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
		returnedEmployee.setImage(as.sendImageFromDownloads(returnedEmployee.getFilePath()));
		return ResponseEntity.ok(returnedEmployee);
	}
	
	@GetMapping("/api/employee")
	public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
		List<EmployeeDto> allEmployee = employeeService.getAllEmployee();
		return ResponseEntity.ok(allEmployee);
	}
	
	@PutMapping("/api/employee/{id}")
	public ResponseEntity<EmployeeDto> updateEmployeeDetails(@PathVariable("id") long employeeId, @ModelAttribute EmployeeDto updatedDetails) throws IOException {
		if(updatedDetails.getFile()!=null) {
			MultipartFile file = updatedDetails.getFile();
			String uploadDirectory = "/Users/mericsapkota/Documents/workspace-spring-tool-suite-4-4.24.0.RELEASE/employeemanagement/src/main/java/com/ems/employeemanagement/images/";
			File directory = new File(uploadDirectory);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			String fileName = System.currentTimeMillis() + file.getOriginalFilename();
			String filePath = uploadDirectory + fileName;

			file.transferTo(new File(filePath));

			updatedDetails.setFilePath(filePath);
		}

		EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, updatedDetails);
		updatedEmployee.setImage(as.sendImageFromDownloads(updatedEmployee.getFilePath()));
		return ResponseEntity.ok(updatedEmployee);
	}
	@DeleteMapping("/api/employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long employeeId ){
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.ok("success");
	}

	@PostMapping("/login")
	public ResponseEntity<EmployeeDto> login(@RequestBody EmployeeDto e){
		EmployeeDto dto = employeeService.login(e);
		dto.setImage(employeeService.sendImageFromDownloads(dto.getFilePath()));

		return ResponseEntity.ok(dto);
	}

	@PostMapping("/r")
	public ResponseEntity<?> register(@ModelAttribute EmployeeDto e) throws IOException {
		if(e.getFile()!=null) {
			MultipartFile file = e.getFile();
			String uploadDirectory = "/Users/mericsapkota/Documents/workspace-spring-tool-suite-4-4.24.0.RELEASE/employeemanagement/src/main/java/com/ems/employeemanagement/images/";
			File directory = new File(uploadDirectory);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			String fileName = System.currentTimeMillis() + file.getOriginalFilename();
			String filePath = uploadDirectory + fileName;
			file.transferTo(new File(filePath));

			e.setFilePath(filePath);
		}
		EmployeeDto dto = employeeService.register(e);


		return ResponseEntity.ok(dto);
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
