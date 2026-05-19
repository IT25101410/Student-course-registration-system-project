package com.shadow.studentcoursemanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CourseRequestDTO {
    //variables
    private String courseCode;
    private String courseName;
    private  String department;
    private int credits;
    private String type;
    private Boolean isMandatory;
    private String electiveCategory;


}
