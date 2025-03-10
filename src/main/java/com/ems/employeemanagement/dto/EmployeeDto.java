package com.ems.employeemanagement.dto;

import com.ems.employeemanagement.entity.Role;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    private MultipartFile file;
	private String filePath;
	private String image;

}
