package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.DiscriminatorValue;  //For label when dealing with inheritance
import jakarta.persistence.Entity;  //
import lombok.AllArgsConstructor;  //
import lombok.Data;   //
import lombok.EqualsAndHashCode;  //
import lombok.NoArgsConstructor;  //

@Entity  //Set this class as database-backed object
@DiscriminatorValue("POSTGRADUATE")  //Its work like a label
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class PostgraduateStudent extends Student {
    private String researchArea; //Attribute

    public PostgraduateStudent(Long id, String name, String email, String phone, String researchArea) {
        super(id, name, email, phone); //Access to parent class constructors
        this.researchArea = researchArea;
    }  //Constructor

    @Override
    public double calculateFee() {
        return 120000.0; //higher fee for postgraduate
    }  //Method
}
