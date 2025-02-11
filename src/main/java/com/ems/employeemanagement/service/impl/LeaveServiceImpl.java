package com.ems.employeemanagement.service.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.ems.employeemanagement.dto.LeaveDto;
import com.ems.employeemanagement.entity.Leave;
import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.mapper.LeaveMapper;
import com.ems.employeemanagement.repository.LeaveRepository;
import com.ems.employeemanagement.service.LeaveService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LeaveServiceImpl implements LeaveService{

	private LeaveRepository lr;

	@Override
	public LeaveDto saveLeaveApplication(LeaveDto l) {
		Leave leaveDetails = LeaveMapper.mapToLeave(l);
		Leave savedDetails= lr.save(leaveDetails);
		return LeaveMapper.mapToDto(savedDetails);
	}

	@Override
	public LeaveDto updateLeaveApplication(LeaveDto updateDetails, long leaveId) {
		Leave l = lr.findById(leaveId).orElseThrow(()->new ResourceNotFoundException("no id found "+leaveId));
		if(!updateDetails.getLeaveReason().isBlank() && (updateDetails.getEndDate()!=null) && (updateDetails.getStartDate()!=null)) {
		l.setLeaveReason(updateDetails.getLeaveReason());
		l.setStartDate(updateDetails.getStartDate());
		l.setEndDate(updateDetails.getEndDate());
		l.setStatus(updateDetails.getStatus());
		Leave updatedDetails =  lr.save(l);
		return LeaveMapper.mapToDto(updatedDetails);
		}
		throw new ResourceNotFoundException("Not valid input");
	}

	@Override
	public void deleteLeaveApplication(long leaveId) {
		Leave l = lr.findById(leaveId).orElseThrow(()-> new ResourceNotFoundException("no id present "+leaveId));
		lr.delete(l);
	}

	@Override
	public List<LeaveDto> getAllLeave(){
		List<Leave> list = lr.findAll();
		List<LeaveDto> listLeave = list.stream().map((listofleave)->LeaveMapper.mapToDto(listofleave)).collect(Collectors.toList());
		return listLeave;
	}
	
	@Override
	public List<LeaveDto> getAllLeave(long empId){
		List<Leave> listLeave = lr.findAllByEmpId(empId);
		
		List<LeaveDto> listOfLeaveDto = listLeave.stream().map((leave)->LeaveMapper.mapToDto(leave)).collect(Collectors.toList());
		return listOfLeaveDto;
	}
	
	public  boolean leaveCheck(LocalDate date, long empId) {
		LocalDate prevLeaveDate = lr.findEndDateByEmpId(empId);
		LocalDate appliedLeaveDate = date;
		if(prevLeaveDate.isBefore(appliedLeaveDate)) {
			return true;
		}
		return false;
	}
	public boolean leaveExists(long empId) {
		Leave l = lr.findEmpIdByEmpId(empId);
		
		if(l!=null) {
			return true;
		}
		else {
			return false;
		}
	}

}
