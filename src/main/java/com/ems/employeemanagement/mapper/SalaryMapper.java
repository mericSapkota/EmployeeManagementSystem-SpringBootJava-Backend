package com.ems.employeemanagement.mapper;

import com.ems.employeemanagement.dto.SalaryDto;
import com.ems.employeemanagement.entity.Salary;

public class SalaryMapper {
    public static Salary mapToSalary(SalaryDto s){
        Salary salary = new Salary(s.getId(),s.getUsername(),s.getSalary(),s.getTax(),s.getTotal(),s.getDate());
        return salary;
    }

    public  static  SalaryDto mapToDto(Salary s){
        SalaryDto dto = new SalaryDto(s.getId(),s.getUsername(),s.getSalary(),s.getTax(),s.getTotal(),s.getDate());
        return dto;
    }

}
