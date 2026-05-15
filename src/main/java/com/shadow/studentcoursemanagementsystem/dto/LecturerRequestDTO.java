package com.shadow.studentcoursemanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LecturerRequestDTO {
    private String name;
    private String email;
    private String phone;
    private String department;
    private String type;              // "PERMANENT" or "VISITING"
    private Integer yearsOfService;   // only for permanent
    private String institution;       // only for visiting
    private Long courseId;            // optional: assign course on creation
}
