package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@DiscriminatorValue("CORE")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CoreCourse extends Course {
    //Attributes
    private boolean isMandatory;

    //Parameterized Constructor
    public CoreCourse(Long id,String courseCode,String courseName,String department,int credits , boolean isMandatory){
        super(id, courseCode, courseName, department, credits);
        this.isMandatory=isMandatory;
    }

    @Override
    public String getDescription() {
        return "Core course — Mandatory: " + (isMandatory ? "Yes" : "No");
    }
}
