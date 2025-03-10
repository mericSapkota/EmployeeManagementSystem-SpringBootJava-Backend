package com.ems.employeemanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name="employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email",nullable = false,unique = true)
	private String email;
	@Column(name="username",nullable = false, unique = true)
	private String username;
	@Column(name="password")
	private String password;
	private int age;
	@Enumerated(EnumType.STRING)
	private Role role;
	private String filepath;
}
