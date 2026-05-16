package com.shadow.studentcoursemanagementsystem.service;

import com.shadow.studentcoursemanagementsystem.dto.EnrollmentRequestDTO;
import com.shadow.studentcoursemanagementsystem.dto.EnrollmentResponseDTO;

import java.util.List;
public interface EnrollmentService {
    EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto);



    List<EnrollmentResponseDTO> getAllEnrollments();
    List<EnrollmentResponseDTO> getEnrollmentsByStudent(Long studentId);
    List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId);
    EnrollmentResponseDTO getEnrollmentById(Long id);
    void dropCourse(Long enrollmentId);
}

