package com.ems.employeemanagement.controller;

import java.util.List;

import com.ems.employeemanagement.entity.Leave;
import com.ems.employeemanagement.repository.EmployeeRepository;
import com.ems.employeemanagement.repository.LeaveRepository;
import com.ems.employeemanagement.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ems.employeemanagement.dto.LeaveDto;
import com.ems.employeemanagement.exception.LeaveExceptionHandler;
import com.ems.employeemanagement.service.impl.LeaveServiceImpl;


@RestController
@CrossOrigin("*")
public class LeaveController {
	
	@Autowired
	private LeaveServiceImpl l;
	@Autowired
	private EmployeeServiceImpl ei;

	
	@PostMapping("/api/leave")
	public ResponseEntity<LeaveDto> saveLeaveApplication(@RequestBody LeaveDto leaveDetails){
			LeaveDto savedLeave = l.saveLeaveApplication(leaveDetails);
			return ResponseEntity.ok(savedLeave);
	}
	
	@PutMapping("/api/leave/{id}")
	public ResponseEntity<LeaveDto> updateLeaveApplication(@PathVariable("id")long id, @RequestBody LeaveDto leave){

		LeaveDto updatedDetails =  l.updateLeaveApplication(leave, id);
		return ResponseEntity.ok(updatedDetails);
	}
	@DeleteMapping("/api/leave/{id}")
	public void deleteLeave(@PathVariable("id")long id) {
		l.deleteLeaveApplication(id);
	}

	@GetMapping("/api/leave/all/{username}")
	public ResponseEntity<List<LeaveDto>> getAllLeaveByEmpId(@PathVariable("username")String username) {

		Long empId= ei.getEmpIdByUsername(username);
		List<LeaveDto> listOfLeave = l.getAllLeave(empId);
		System.out.println(listOfLeave);
		return ResponseEntity.ok(listOfLeave);
	}
	@GetMapping("/api/leave/all")
	public ResponseEntity<List<LeaveDto>> getAllLeave(){
		List<LeaveDto> listOfLeave = l.getAllLeave();
		return ResponseEntity.ok(listOfLeave);
	}

	@GetMapping("/api/leave/{id}")
	public ResponseEntity<LeaveDto> getListById(@PathVariable("id")long id){
		LeaveDto dto = l.getLeaveById(id);
		return  ResponseEntity.ok(dto);
	}
}
