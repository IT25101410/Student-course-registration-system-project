package com.shadow.studentcoursemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentRequestDTO {
    private String name; //
    private String email; //
    private String phone; //
    private String type; // "UNDERGRADUATE" or "POSTGRADUATE"
    private Integer yearOfStudy; // only for undergrad
    private String researchArea; //
}


    /*DTO - Data Transfer Object
          This is simple object use to carry data to one place to another
     */