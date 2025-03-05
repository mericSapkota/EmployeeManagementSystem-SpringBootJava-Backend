package com.ems.employeemanagement.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ems.employeemanagement.entity.Employee;


@SuppressWarnings("serial")
public class EmployeePrincipal implements UserDetails{
	
	private Employee employee;
	
	public EmployeePrincipal(Employee employee) {
	
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(employee.getRole().getAuthority()));
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
