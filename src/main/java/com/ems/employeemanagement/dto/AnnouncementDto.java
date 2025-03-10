package com.ems.employeemanagement.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDto {
    private String username;
    private LocalDate date;
    private LocalTime time;
    private String content;
    private String FilePath;
    private MultipartFile file;
    private String posterImage;
    private String postedImage;
}
