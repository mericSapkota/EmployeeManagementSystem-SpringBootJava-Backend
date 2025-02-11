package com.ems.employeemanagement.service;

import java.util.List;

import com.ems.employeemanagement.dto.LeaveDto;

public interface LeaveService {
 public LeaveDto saveLeaveApplication(LeaveDto l);
 public LeaveDto updateLeaveApplication(LeaveDto updateDetails, long leaveId);
 public void deleteLeaveApplication(long leaveId);
 public List<LeaveDto> getAllLeave();
 public List<LeaveDto> getAllLeave(long empId);
}
