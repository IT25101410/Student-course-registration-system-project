package com.sliit.scrs.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="students")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="student_type")
@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String phone;

    //Abstract method
    public abstract double calculateFee();



}
