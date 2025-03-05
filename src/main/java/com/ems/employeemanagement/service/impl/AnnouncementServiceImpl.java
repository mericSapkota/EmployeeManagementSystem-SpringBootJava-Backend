package com.ems.employeemanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.employeemanagement.entity.Announcements;
import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.repository.AnnouncementRepo;
import com.ems.employeemanagement.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementRepo a;
	@Override
	public List<Announcements> getAll() {
		List<Announcements> all = a.findAll();
		return all;
	}

	@Override
	public Announcements saveAnnouncement(Announcements a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Announcements updateAnnouncement(long id, Announcements updateDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAnnouncement(long id) {
		a.findById(id).orElseThrow(()->new ResourceNotFoundException("no id found to delete"));
		a.deleteById(id);
		
	}

	@Override
	public Announcements getAnnouncementById(long id) {
		Announcements an = a.findById(id).orElseThrow(()-> new ResourceNotFoundException("no announcement found"));
		return an;
	}

}
