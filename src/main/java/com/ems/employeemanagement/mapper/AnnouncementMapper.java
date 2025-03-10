package com.ems.employeemanagement.mapper;

import com.ems.employeemanagement.dto.AnnouncementDto;
import com.ems.employeemanagement.entity.Announcements;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



public class AnnouncementMapper {
    public static Announcements mapToAnnouncement(AnnouncementDto dto){
        Announcements a = new Announcements();
        a.setDate(dto.getDate());
        a.setTime(dto.getTime());
        a.setImage(dto.getFilePath());
        a.setContent(dto.getContent());
        a.setUsername(dto.getUsername());
        return a;
    }

    public static AnnouncementDto mapToAnnouncementDto(Announcements a) {
        AnnouncementDto dto = new AnnouncementDto();
        dto.setFilePath(a.getImage());
        dto.setContent(a.getContent());
        dto.setTime(a.getTime());
        dto.setDate(a.getDate());
        dto.setUsername(a.getUsername());
        return dto;
    }
}
