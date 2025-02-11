package com.ems.employeemanagement.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.employeemanagement.entity.Employee;
import com.ems.employeemanagement.repository.EmployeeRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	private final EmployeeRepository employeeRepository;

	public MyUserDetailsService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Employee employee = employeeRepository.findByUsername(username);
		
		if(employee == null) {
			throw new UsernameNotFoundException("Sorry no username found");
		}
		
		return new EmployeePrincipal(employee);
	}

}
