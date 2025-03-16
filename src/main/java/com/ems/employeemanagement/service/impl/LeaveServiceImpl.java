package com.ems.employeemanagement.service.impl;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
		Leave l = lr.findById(leaveId).orElseThrow(()->new RuntimeException("error in leave "));
		l.setStatus(updateDetails.getStatus());
		Leave updatedDetails =  lr.save(l);
		return LeaveMapper.mapToDto(updatedDetails);


	}

	@Override
	public void deleteLeaveApplication(long leaveId) {
		Leave l = lr.findById(leaveId).orElseThrow(()->new RuntimeException(""));
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
		return  listLeave.stream().map((leave)->LeaveMapper.mapToDto(leave)).collect(Collectors.toList());
	}

	@Override
	public LeaveDto getLeaveById(long id) {
		Leave l = lr.findLeaveById(id);
		System.out.println(l);
		return LeaveMapper.mapToDto(l);
	}



}
