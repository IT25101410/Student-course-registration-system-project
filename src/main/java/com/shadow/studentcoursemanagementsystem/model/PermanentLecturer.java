package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Lombok annotation to generate getters, setters, toString, etc.
@DiscriminatorValue("PERMANENT") // Lombok annotation to generate getters, setters, toString, etc.
@Data // Lombok annotation to generate getters, setters, toString, etc.
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor

@EqualsAndHashCode(callSuper = true) // Includes parent class fields in equals() and hashCode()
public class PermanentLecturer extends Lecturer {

    private int yearsOfService;

    // Parameterized constructor
    public PermanentLecturer(Long id, String name, String email,
                             String phone, String department,
                             Course assignedCourse, int yearsOfService) {
        super(id, name, email, phone, department, assignedCourse);
        this.yearsOfService = yearsOfService;
    }

    @Override
    public double calculateWorkload() {
        // Permanent lecturers handle more hours
        return 40.0 + (yearsOfService * 0.5);
    }
}
