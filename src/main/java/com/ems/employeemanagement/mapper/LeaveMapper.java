package com.ems.employeemanagement.mapper;

import com.ems.employeemanagement.dto.LeaveDto;
import com.ems.employeemanagement.entity.Leave;

public class LeaveMapper {

	public static Leave mapToLeave(LeaveDto l) {
		return  new Leave(
				l.getId(),
				l.getEmpId(),
				l.getStatus(),
				l.getLeaveReason(),
				l.getStartDate(),
				l.getEndDate()
			
				);
	}
	
	public static LeaveDto mapToDto(Leave l) {

		return  new LeaveDto(
				l.getId(),
				l.getEmpId(),
				l.getLeaveReason(),
				l.getStartDate(),
				l.getStatus(),
				l.getEndDate()
				);
	}
}
