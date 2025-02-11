package com.ems.employeemanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ems.employeemanagement.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {

	@Query("SELECT l.endDate FROM Leave l  WHERE l.empId =:empId")
	LocalDate findEndDateByEmpId(@Param("empId") long empId);
	
	<Optional>Leave findEmpIdByEmpId(long empId);
	List<Leave> findAllByEmpId(long empId);
}
