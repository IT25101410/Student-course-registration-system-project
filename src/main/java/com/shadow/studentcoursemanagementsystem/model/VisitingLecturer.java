package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("VISITING") // Value used in single table inheritance to identify visiting lecturers
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true) // Includes parent class fields in equals() and hashCode()
public class VisitingLecturer extends Lecturer {

    private String institution; // their home institution

    public VisitingLecturer(Long id, String name, String email,
                            String phone, String department,
                            Course assignedCourse, String institution) {
        super(id, name, email, phone, department, assignedCourse);
        this.institution = institution;
    }
    // Calculate workload for visiting lecturers
    @Override
    public double calculateWorkload() {
        // Visiting lecturers handle fewer hours
        return 20.0;
    }
}
