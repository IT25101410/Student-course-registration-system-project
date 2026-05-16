package com.shadow.studentcoursemanagementsystem.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponseDTO {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseName;
    private String courseCode;
    private LocalDate enrollmentDate;
    private String status;
    private String enrollmentType;
    private int maxCreditsAllowed;
}

