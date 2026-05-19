package com.shadow.studentcoursemanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String department;
    private String type; // Lecturer type (Full-Time / Visiting / Part-Time)
    private double weeklyWorkloadHours;
    private Long assignedCourseId;
    private String assignedCourseName;
}
