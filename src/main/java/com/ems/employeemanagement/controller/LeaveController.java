package com.ems.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.employeemanagement.dto.LeaveDto;
import com.ems.employeemanagement.exception.LeaveExceptionHandler;
import com.ems.employeemanagement.service.impl.LeaveServiceImpl;


@RestController
@CrossOrigin("*")
public class LeaveController {
	
	@Autowired
	private LeaveServiceImpl l;
	
	@PostMapping("/api/leave")
	public ResponseEntity<LeaveDto> saveLeaveApplication(@RequestBody LeaveDto leaveDetails){
		
		if(!l.leaveExists(leaveDetails.getEmpId())) {
			LeaveDto savedLeave = l.saveLeaveApplication(leaveDetails);
			return ResponseEntity.ok(savedLeave);
		}
		if(l.leaveCheck(leaveDetails.getStartDate(), leaveDetails.getEmpId())) {
			LeaveDto savedLeave = l.saveLeaveApplication(leaveDetails);
			return ResponseEntity.ok(savedLeave);
		}
		throw new LeaveExceptionHandler("sorry worng date");
	}
	
	@PutMapping("/api/leave/{id}")
	public ResponseEntity<LeaveDto> updateLeaveApplication(@PathVariable("id")long id, @RequestBody LeaveDto leaveDetials){
		LeaveDto updatedDetails =  l.updateLeaveApplication(leaveDetials, id);
		return ResponseEntity.ok(updatedDetails);
	}
	@DeleteMapping("/api/leave/{id}")
	public void deleteLeave(@PathVariable("id")long id) {
		l.deleteLeaveApplication(id);
	}
	@GetMapping("/api/leave/all/{id}")
	public ResponseEntity<List<LeaveDto>> getAllLeaveByEmpId(@PathVariable("id")long empId) {
		List<LeaveDto> listOfLeave = l.getAllLeave(empId);
		return ResponseEntity.ok(listOfLeave);
	}
	@GetMapping("/api/leave/all")
	public ResponseEntity<List<LeaveDto>> getAllLeave(){
		List<LeaveDto> listOfLeave = l.getAllLeave();
		return ResponseEntity.ok(listOfLeave);
	}
}
