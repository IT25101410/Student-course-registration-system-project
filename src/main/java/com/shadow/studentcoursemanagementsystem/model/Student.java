package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.*;  //For interact with database    // * for importing every class & interfaces
import lombok.*;  //Shortcuts for getters setters

@Entity //Set this class as database-backed object
@Table(name="students") //Name table as students
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //This means if there different classes like parent and child, use single talbe...
@DiscriminatorColumn(name="student_type") //This works like a label ("UNDERGRADUATE" or "POSTGRADUATE")
@Data //Automatic generate getters and setters
@NoArgsConstructor //Create constructor with no arguments
@AllArgsConstructor //Creates  constructor accepts all fields

public abstract class Student {
    @Id //Set id field as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generate ID values---(GenerationType.IDENTITY = auto increment)
    private Long id;

    //Column = variable should be a column
    @Column(nullable = false) //Not null constrain (Can't be empty)
    private String name;

    @Column(nullable = false,unique = true) //email cannot be multiple times
    private String email;

    @Column(nullable = false)
    private String phone;

    //Abstract method
    public abstract double calculateFee();

}
