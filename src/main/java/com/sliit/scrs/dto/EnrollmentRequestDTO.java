package com.sliit.scrs.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequestDTO {
    private Long studentId;
    private Long courseId;
    private String enrollmentType;
}

