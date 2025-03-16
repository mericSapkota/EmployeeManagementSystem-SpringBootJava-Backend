package com.ems.employeemanagement.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ems.employeemanagement.entity.Leave;


public interface LeaveRepository extends JpaRepository<Leave,Long> {

	@Query("SELECT l FROM Leave l WHERE l.id = :id")
	Leave findLeaveById(@Param("id") long id);

	List<Leave> findAllByEmpId(long empId);
}
