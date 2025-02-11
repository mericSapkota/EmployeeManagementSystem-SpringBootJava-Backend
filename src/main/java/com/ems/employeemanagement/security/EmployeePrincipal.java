package com.ems.employeemanagement.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ems.employeemanagement.entity.Employee;


public class EmployeePrincipal implements UserDetails{
	
	private Employee employee;
	
	public EmployeePrincipal(Employee employee) {
	
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		
		return employee.getUsername();
	}

}
