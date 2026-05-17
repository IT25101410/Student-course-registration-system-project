package com.shadow.studentcoursemanagementsystem.repository;

import com.sliit.scrs.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Repository interface for Lecturer entity
@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Long> {

    // Find lecturer by email
    Optional<Lecturer> findByEmail(String email);
    // Check if a lecturer exists with the given email
    boolean existsByEmail(String email);
    // Find all lecturers in a specific department
    List<Lecturer> findByDepartment(String department);
    // Find lecturers assigned to a specific course
    List<Lecturer> findByAssignedCourseId(Long courseId);
}