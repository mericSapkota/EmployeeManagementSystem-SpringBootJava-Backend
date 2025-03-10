package com.ems.employeemanagement.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import com.ems.employeemanagement.dto.AnnouncementDto;
import com.ems.employeemanagement.mapper.AnnouncementMapper;
import com.ems.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.employeemanagement.entity.Announcements;
import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.repository.AnnouncementRepo;
import com.ems.employeemanagement.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

	@Autowired
	private AnnouncementRepo aRepo;
	@Autowired
	private EmployeeRepository eRepo;

	@Override
	public List<AnnouncementDto> getAll() {
		List<Announcements> allAnnouncements = aRepo.findAll();
		return allAnnouncements.stream()
				.map((a)->{
					AnnouncementDto d = AnnouncementMapper.mapToAnnouncementDto(a);
					d.setPostedImage(sendImageFromDownloads(d.getFilePath()));
					String posterPath =  eRepo.findFilepathByUsername(d.getUsername());
					d.setPosterImage(sendImageFromDownloads(posterPath));
					return d;
				})
				.collect(Collectors.toList());

	}
	public String sendImageFromDownloads(String filePath){
		try {
			return Base64.getEncoder().encodeToString(Files.readAllBytes(new File(filePath).toPath()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Announcements saveAnnouncement(AnnouncementDto a) {
		Announcements details =  AnnouncementMapper.mapToAnnouncement(a);
		return aRepo.save(details);
	}

	@Override
	public Announcements updateAnnouncement(long id, AnnouncementDto updateDetails) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAnnouncement(long id) {
		aRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("no id found to delete"));
		aRepo.deleteById(id);
		
	}

	@Override
	public Announcements getAnnouncementById(long id) {
		Announcements an = aRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("no announcement found"));
		return an;
	}

}
