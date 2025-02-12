package com.ems.employeemanagement.service;

import java.util.List;

import com.ems.employeemanagement.entity.Announcements;

public interface AnnouncementService {
List<Announcements> getAll();
Announcements saveAnnouncement(Announcements a);
Announcements updateAnnouncement(long id, Announcements updateDetails);
void deleteAnnouncement(long id);
Announcements getAnnouncementById(long id);
}
