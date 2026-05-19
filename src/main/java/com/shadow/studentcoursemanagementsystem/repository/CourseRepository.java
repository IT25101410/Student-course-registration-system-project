package com.shadow.studentcoursemanagementsystem.repository;

import com.shadow.studentcoursemanagementsystem.dto.CourseResponseDTO;
import com.shadow.studentcoursemanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository  extends JpaRepository<Course,Long> {
    Optional<Course>findByCourseCode(String courseCode);
    boolean existsByCourseCode(String  courseCode);



}
