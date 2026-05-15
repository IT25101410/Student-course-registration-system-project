package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@DiscriminatorValue("CORE")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CoreCourse extends Course {
    //Attributes
    private boolean isMandatory;
    

}
