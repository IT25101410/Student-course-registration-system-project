package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.DiscriminatorValue; // For label when dealing with inheritance
import jakarta.persistence.Entity;  //
import lombok.AllArgsConstructor;  //
import lombok.Data;  //
import lombok.EqualsAndHashCode;  //
import lombok.NoArgsConstructor;  //

@Entity //Set this class as database-backed object
@DiscriminatorValue("UNDERGRADUATE")  //Its work like a label
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true) //When comparing two objects, if they are the same go to Parent class and check those fields too.

public class UndergraduateStudent extends Student {
    private int yearOfStudy;   //Attribute

    public UndergraduateStudent(Long id, String name, String email, String phone, int yearOfStudy) {
        super(id, name, email, phone);  //Access to parent class constructors
        this.yearOfStudy = yearOfStudy;
    }   //Constructors

    @Override
    public double calculateFee() {
        return 75000.0; //Lower fee for postgraduate
    }   //Method
}
