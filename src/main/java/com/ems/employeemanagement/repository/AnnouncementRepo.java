package com.ems.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ems.employeemanagement.entity.Announcements;

import java.util.List;

public interface AnnouncementRepo extends JpaRepository<Announcements, Long> {
    List<Announcements> findAllByOrderByTimeDesc();
}
