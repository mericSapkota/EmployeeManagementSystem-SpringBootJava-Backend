package com.ems.employeemanagement.repository;

import com.ems.employeemanagement.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepo extends JpaRepository<Salary,Long> {

    List<Salary> findAllByUsername(String username);
}
