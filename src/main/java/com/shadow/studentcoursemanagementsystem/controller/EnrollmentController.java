package com.shadow.studentcoursemanagementsystem.controller;

import com.sliit.scrs.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shadow.studentcoursemanagementsystem.dto.EnrollmentRequestDTO;
import com.shadow.studentcoursemanagementsystem.service.EnrollmentService;
import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    // Register student to course
    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enrollStudent(
            @RequestBody EnrollmentRequestDTO dto) {
        return ResponseEntity.ok(enrollmentService.enrollStudent(dto));
    }

    // View all enrollments
    @GetMapping
    public ResponseEntity<List<EnrollmentResponseDTO>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    // View enrollments by student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }

    // View enrollments by course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<EnrollmentResponseDTO>> getByCourse(
            @PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByCourse(courseId));
    }

    // View single enrollment
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }

    // Drop a course
    @PatchMapping("/{id}/drop")
    public ResponseEntity<String> dropCourse(@PathVariable Long id) {
        enrollmentService.dropCourse(id);
        return ResponseEntity.ok("Course dropped successfully.");
    }
}

