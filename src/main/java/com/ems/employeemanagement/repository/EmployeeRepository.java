package com.ems.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ems.employeemanagement.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByUsername(String username);

    @Query("SELECT e.filepath FROM Employee e WHERE e.username = :username")
    String findFilepathByUsername(@Param("username") String username);

    @Query("SELECT e.id from Employee e WHERE e.username = :username")
    long findEmpIdByUsername(@Param(("username")) String username);
}
