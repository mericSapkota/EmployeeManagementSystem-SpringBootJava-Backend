package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.dto.SalaryDto;
import com.ems.employeemanagement.service.EmployeeService;
import com.ems.employeemanagement.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SalaryController {

    @Autowired
    SalaryService ss;

    @Autowired
    EmployeeService es;

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

    @PostMapping("/salary")
    public ResponseEntity<SalaryDto> postSalary(@RequestBody SalaryDto dto){
        System.out.println(dto);

        SalaryDto saved = ss.addSalary(dto);
        return ResponseEntity.ok(saved);
    }
    @DeleteMapping("/salary")
    public void deleteSalary(@RequestBody SalaryDto dto){
        System.out.println(dto);
        ss.deleteSalary(dto);
    }
    @GetMapping("/getAllUsername")
    public ResponseEntity<List<String>> getAllUsername(){
        List<String> usernames = es.getAllUsername();
        System.out.println(usernames);
        return ResponseEntity.ok(usernames);
    }
}
