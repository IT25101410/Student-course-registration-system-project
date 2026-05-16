package com.shadow.studentcoursemanagementsystem.service.Impl;


import com.shadow.studentcoursemanagementsystem.service.EnrollmentService;
import com.shadow.studentcoursemanagementsystem.dto.EnrollmentResponseDTO;
import com.shadow.studentcoursemanagementsystem.model.Course;
import com.shadow.studentcoursemanagementsystem.model.Enrollment;
import com.shadow.studentcoursemanagementsystem.model.Student;
import com.shadow.studentcoursemanagementsystem.repository.CourseRepository;
import com.shadow.studentcoursemanagementsystem.repository.EnrollmentRepository;
import com.shadow.studentcoursemanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public  class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public EnrollmentResponseDTO enrollStudent(EnrollmentRequestDTO dto) {

        // Abstraction: check student exists before enrolling
        Student student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException(
                        "Student not found with id: " + dto.getStudentId()));

        // Abstraction: check course exists before enrolling
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException(
                        "Course not found with id: " + dto.getCourseId()));

        // Check if already enrolled
        if (enrollmentRepository.existsByStudentIdAndCourseId(
                dto.getStudentId(), dto.getCourseId())) {
            throw new RuntimeException("Student is already enrolled in this course.");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setStatus("ACTIVE");
        enrollment.setEnrollmentType(dto.getEnrollmentType());

        Enrollment saved = enrollmentRepository.save(enrollment);
        return mapToResponse(saved);
    }

    @Override
    public List<EnrollmentResponseDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentResponseDTO> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EnrollmentResponseDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Enrollment not found with id: " + id));
        return mapToResponse(enrollment);
    }

    @Override
    public void dropCourse(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException(
                        "Enrollment not found with id: " + enrollmentId));

        if ("DROPPED".equals(enrollment.getStatus())) {
            throw new RuntimeException("Course already dropped.");
        }

        enrollment.setStatus("DROPPED");
        enrollmentRepository.save(enrollment);
    }

    // Polymorphism: full-time gets 6 credits max, part-time gets 3
    private int getMaxCredits(String enrollmentType) {
        if ("FULL_TIME".equalsIgnoreCase(enrollmentType)) {
            return 6;
        }
        return 3;
    }

    private EnrollmentResponseDTO mapToResponse(Enrollment e) {
        return new EnrollmentResponseDTO(
                e.getId(),
                e.getStudent().getId(),
                e.getStudent().getName(),
                e.getCourse().getId(),
                e.getCourse().getCourseName(),
                e.getCourse().getCourseCode(),
                e.getEnrollmentDate(),
                e.getStatus(),
                e.getEnrollmentType(),
                getMaxCredits(e.getEnrollmentType())
        );
    }
}


