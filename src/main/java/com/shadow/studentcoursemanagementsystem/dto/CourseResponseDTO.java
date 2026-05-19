package com.shadow.studentcoursemanagementsystem.dto;

import lombok.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    //Variable
    private Long id;
    private String courseCode;
    private String courseName;
    private String department;
    private int credits;
    private String type;
    private String description;





}
