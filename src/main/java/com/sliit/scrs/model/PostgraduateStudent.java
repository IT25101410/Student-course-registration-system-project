package com.sliit.scrs.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("POSTGRADUATE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class PostgraduateStudent extends Student {
    private String researchArea;

    public PostgraduateStudent(Long id, String name, String email, String phone, String researchArea) {
        super(id, name, email, phone);
        this.researchArea = researchArea;
    }

    @Override
    public double calculateFee() {
        return 120000.0; // higher fee for postgrad
    }
}
