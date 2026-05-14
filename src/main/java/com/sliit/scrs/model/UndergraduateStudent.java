package com.sliit.scrs.model;

import jakarta.persistence.DiscriminatorValue; //
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("UNDERGRADUATE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class UndergraduateStudent extends Student {
    private int yearOfStudy;

    public UndergraduateStudent(Long id, String name, String email, String phone, int yearOfStudy) {
        super(id, name, email, phone);
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public double calculateFee() {
        return 75000.0;
    }
}
