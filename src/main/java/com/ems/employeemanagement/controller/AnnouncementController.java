package com.ems.employeemanagement.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import com.ems.employeemanagement.dto.AnnouncementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ems.employeemanagement.entity.Announcements;
import com.ems.employeemanagement.service.impl.AnnouncementServiceImpl;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
public class AnnouncementController {
	
@Autowired
private AnnouncementServiceImpl aService;

	@PostMapping("/api/announcements")
	public ResponseEntity<Announcements> saveA(@ModelAttribute AnnouncementDto a ){
		MultipartFile file = a.getFile();
		String uploadDirectory = "/Users/mericsapkota/Documents/workspace-spring-tool-suite-4-4.24.0.RELEASE/employeemanagement/src/main/java/com/ems/employeemanagement/images/announcement/";
		File directory = new File(uploadDirectory);
		if(!directory.exists()){
			directory.mkdirs();
		}
		String fileName = System.currentTimeMillis()+file.getOriginalFilename();
		String filePath = uploadDirectory + fileName;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		a.setTime(LocalTime.now());
		a.setDate(LocalDate.now());
		a.setFilePath(filePath);

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
	public ResponseEntity<List<AnnouncementDto>> getAllAnnouncements(){
		List<AnnouncementDto> a = aService.getAll();
		System.out.println(a);
		return  ResponseEntity.ok(a);
	}
}
