package com.ems.employeemanagement.dto;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LeaveDto {
 
 private long id;
 private long empId;
 private String leaveReason;
 private LocalDate startDate;
 private String status;
 private LocalDate endDate;
}
