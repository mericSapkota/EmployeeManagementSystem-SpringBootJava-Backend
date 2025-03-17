package com.ems.employeemanagement.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalaryDto {
    private long id;
    private String username;
    private float salary;
    private int tax;
    private float total;
    private LocalDate date;
}
