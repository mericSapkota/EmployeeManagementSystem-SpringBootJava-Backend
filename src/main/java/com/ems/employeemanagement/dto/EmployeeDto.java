package com.ems.employeemanagement.dto;

import com.ems.employeemanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private int age;
	private Role role;
	private String token;
}
