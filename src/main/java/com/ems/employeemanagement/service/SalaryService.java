package com.ems.employeemanagement.service;

import com.ems.employeemanagement.dto.SalaryDto;
import com.ems.employeemanagement.entity.Salary;
import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.mapper.SalaryMapper;
import com.ems.employeemanagement.repository.SalaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaryService {

    @Autowired
    SalaryRepo sr;

    public List<SalaryDto> getAllSalary(){
        List<Salary> s = sr.findAll();
        return s.stream().map(SalaryMapper::mapToDto).collect(Collectors.toList());
    }

    public List<SalaryDto> getSalaryByUsername(String username) {
        List<Salary> s = sr.findAllByUsername(username);
        return s.stream().map(SalaryMapper::mapToDto).collect(Collectors.toList());
    }

    public SalaryDto addSalary(SalaryDto dto) {
        Salary salary =SalaryMapper.mapToSalary(dto);
        Salary s =  sr.save(salary);
        return SalaryMapper.mapToDto(s);
    }

    public void deleteSalary(SalaryDto dto) {
        sr.findById(dto.getId()).orElseThrow(()->new ResourceNotFoundException("no salary record found"));
        sr.deleteById(dto.getId());
    }
}
