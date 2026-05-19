package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lecturers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Stores all lecturer types in one table
@DiscriminatorColumn(name = "lecturer_type") // Identifies lecturer subtype
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Lecturer {

    // Primary key of the lecturer table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String department;

    // Association: Lecturer assigned to a Course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course assignedCourse;

    // Abstract method — Polymorphism
    public abstract double calculateWorkload();
}
