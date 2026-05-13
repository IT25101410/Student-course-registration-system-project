package com.sliit.scrs.model;

import jakarta.persistence.*;  //For interact with database    // * for importing every class & interfaces
import lombok.*;  //Shortcuts for getters setters

@Entity
@Table(name="students")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="student_type")
@Data
@NoArgsConstructor
@AllArgsConstructor

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
