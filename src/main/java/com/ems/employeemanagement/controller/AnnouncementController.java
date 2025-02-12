package com.ems.employeemanagement.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ems.employeemanagement.entity.Announcements;
import com.ems.employeemanagement.service.impl.AnnouncementServiceImpl;

@RestController
@CrossOrigin("*")
public class AnnouncementController {
	
@Autowired
private AnnouncementServiceImpl aService;

	@PostMapping("/api/announcements")
	public ResponseEntity<Announcements> saveA(@RequestBody Announcements a ){
		Announcements an=  aService.saveAnnouncement(a);
		return ResponseEntity.ok(an);
	}
	
	@PutMapping("/api/announcements/{id}")
	
	@DeleteMapping("/api/announcements/delete/{id}")
		public ResponseEntity<String> delete(@PathVariable ("id") long id) {
		  aService.deleteAnnouncement(id);
		  return ResponseEntity.ok("success");
		}
	

	@GetMapping("/api/announcements/{id}")
	public ResponseEntity<Announcements> getAnnouncementById(@PathVariable ("id") long id){
		Announcements a =  aService.getAnnouncementById(id);
		return ResponseEntity.ok(a);
	}
	

	@GetMapping("/api/announcements/all")
	public ResponseEntity<List<Announcements>> getAllAnnouncements(){
		List<Announcements> a = aService.getAll();
		return  ResponseEntity.ok(a);
	}
}
