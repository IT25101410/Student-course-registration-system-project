package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name="courses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn(name = "course_type")
@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    //Attributes
    private Long id;
    // variable map to the column of database
    @Column(nullable = false, unique = true) //unique = true (cant value duplicate)
    private String courseCode;
    @Column(nullable = false)// nullable = false(cant this  column empty )
    private String courseName;
    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private int credits;

    //Abstract Method
    public abstract String getDescription();


}
