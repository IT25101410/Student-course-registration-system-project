package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("ELECTIVE")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ElectiveCourse extends Course{
    //Attributes
    private String electiveCategory;
    //Parameterized Constructor
    public ElectiveCourse(Long id, String courseCode, String courseName, String department, int credits, String electiveCategory) {
        super(id, courseCode, courseName, department, credits);
        this.electiveCategory = electiveCategory;
    }




}
