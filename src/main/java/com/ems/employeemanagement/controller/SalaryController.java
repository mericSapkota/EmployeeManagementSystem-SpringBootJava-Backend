package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.dto.SalaryDto;
import com.ems.employeemanagement.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalaryController {

    @Autowired
    SalaryService ss;

    @GetMapping("/salary")
    public ResponseEntity<List<SalaryDto>> getAllSalary(){
        List<SalaryDto> salaries = ss.getAllSalary();
        return ResponseEntity.ok(salaries);
    }

    @GetMapping("/salary/{username}")
    public ResponseEntity<List<SalaryDto>> getSalaryByUsername(@PathVariable("username") String username){
        List<SalaryDto> salaries = ss.getSalaryByUsername(username);
        return ResponseEntity.ok(salaries);
    }

    @PostMapping("/salary/add")
    public ResponseEntity<SalaryDto> postSalary(@RequestBody SalaryDto dto){
        float taxAmount =((float) dto.getTax() / 100) * dto.getSalary();
        float total = dto.getSalary()-taxAmount;
        dto.setTotal(total);
        SalaryDto saved = ss.addSalary(dto);
        return ResponseEntity.ok(saved);
    }
}
