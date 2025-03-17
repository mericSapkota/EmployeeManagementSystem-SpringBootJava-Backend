package com.ems.employeemanagement.service.impl;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;

import java.util.stream.Collectors;

import com.ems.employeemanagement.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import org.springframework.web.multipart.MultipartFile;

@Service


public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JavaMailSender jms;

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
		if(employee.getFilepath()!=null){
			String filePath = employee.getFilepath();
			File file = new File(filePath);
			if(file.exists()){
				file.delete();
			}
		}

		employee.setFirstName(updatedDetails.getFirstName());
		employee.setLastName(updatedDetails.getLastName());
		employee.setEmail(updatedDetails.getEmail());
		employee.setAge(updatedDetails.getAge());
		employee.setUsername(updatedDetails.getUsername());
		employee.setPassword(bCryptPasswordEncoder.encode(updatedDetails.getPassword()));
		employee.setFilepath(updatedDetails.getFilePath());
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
			String filePath=employee.getFilepath();
			emp.setId(employee.getId());
			return emp;
		}

		return null;
	}

	public String sendImageFromDownloads(String filePath){
		if(filePath==null){
			return null;
		}
        try {
            return Base64.getEncoder().encodeToString(Files.readAllBytes(new File(filePath).toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public EmployeeDto register(EmployeeDto e) {
		String pass = e.getPassword();
		e.setPassword(bCryptPasswordEncoder.encode(pass));
		System.out.println(e.getEmail());
		Employee emp = EmployeeMapper.mapToEmployee(e);
		System.out.println(emp);

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(e.getEmail());
		mail.setSubject("Welcome to Employee Management System");
		mail.setText("Welcome to Employee Management System. I hope You will have fun Best Regards. These are your " +
				"email and passwords \n username: "+e.getUsername()+"\n passowrd: "+ pass+
				" \n login from link below: \n"+" http://localhost:3000/login");
		jms.send(mail);

		return EmployeeMapper.mapToEmployeeDto(employeeRepo.save(emp));
	}

	@Override
	public List<String> getAllUsername() {

		return employeeRepo.findAllUsername();
	}

	public Long getEmpIdByUsername(String username) {
		return employeeRepo.findEmpIdByUsername(username);
	}
}
