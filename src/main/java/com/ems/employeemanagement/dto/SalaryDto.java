package com.ems.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryDto {
    private long id;
    private String username;
    private float salary;
    private int tax;
    private float total;
    private LocalDate date;
}
