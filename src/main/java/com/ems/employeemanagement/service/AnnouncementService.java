package com.ems.employeemanagement.service;

import java.util.List;

import com.ems.employeemanagement.dto.AnnouncementDto;
import com.ems.employeemanagement.entity.Announcements;

public interface AnnouncementService {
List<AnnouncementDto> getAll();
Announcements saveAnnouncement(AnnouncementDto a);
Announcements updateAnnouncement(long id, AnnouncementDto updateDetails);
void deleteAnnouncement(long id);
Announcements getAnnouncementById(long id);
}
