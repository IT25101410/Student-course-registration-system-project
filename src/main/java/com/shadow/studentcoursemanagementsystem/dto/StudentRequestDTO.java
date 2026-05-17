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
