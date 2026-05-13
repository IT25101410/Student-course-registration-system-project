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


}
