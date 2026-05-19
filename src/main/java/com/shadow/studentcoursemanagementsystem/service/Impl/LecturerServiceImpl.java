package com.shadow.studentcoursemanagementsystem.service.Impl;

import com.sliit.scrs.dto.*;
import com.sliit.scrs.model.*;
import com.sliit.scrs.repository.*;
import com.sliit.scrs.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LecturerServiceImpl implements LecturerService {

    // Repository for lecturer database operations
    @Autowired
    private LecturerRepository lecturerRepository;

    // Repository for course database operations
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public LecturerResponseDTO addLecturer(LecturerRequestDTO dto) {

        // Check whether lecturer email already exists
        if (lecturerRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Lecturer with this email already exists.");
        }

        // Resolve assigned course if provided
        Course course = null;
        if (dto.getCourseId() != null) {
            course = courseRepository.findById(dto.getCourseId())
                    .orElseThrow(() -> new RuntimeException(
                            "Course not found with id: " + dto.getCourseId()));
        }

        Lecturer lecturer;

        // Inheritance + Polymorphism: create correct subtype
        if ("PERMANENT".equalsIgnoreCase(dto.getType())) {
            lecturer = new PermanentLecturer(
                    null, dto.getName(), dto.getEmail(),
                    dto.getPhone(), dto.getDepartment(),
                    course, dto.getYearsOfService()
            );
        } else {
            lecturer = new VisitingLecturer(
                    null, dto.getName(), dto.getEmail(),
                    dto.getPhone(), dto.getDepartment(),
                    course, dto.getInstitution()
            );
        }

        Lecturer saved = lecturerRepository.save(lecturer);
        return mapToResponse(saved);
    }

    @Override
    public List<LecturerResponseDTO> getAllLecturers() {
        return lecturerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LecturerResponseDTO getLecturerById(Long id) {
        Lecturer lecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Lecturer not found with id: " + id));
        return mapToResponse(lecturer);
    }

    @Override
    public LecturerResponseDTO updateLecturer(Long id, LecturerRequestDTO dto) {
        Lecturer lecturer = lecturerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Lecturer not found with id: " + id));

        lecturer.setName(dto.getName());
        lecturer.setEmail(dto.getEmail());
        lecturer.setPhone(dto.getPhone());
        lecturer.setDepartment(dto.getDepartment());

        // Add this — handle type-specific fields
        if (lecturer instanceof PermanentLecturer pl) {
            pl.setYearsOfService(dto.getYearsOfService());
        } else if (lecturer instanceof VisitingLecturer vl) {
            vl.setInstitution(dto.getInstitution());
        }

        Lecturer updated = lecturerRepository.save(lecturer);
        return mapToResponse(updated);
    }

    @Override
    public void deleteLecturer(Long id) {
        if (!lecturerRepository.existsById(id)) {
            throw new RuntimeException("Lecturer not found with id: " + id);
        }
        lecturerRepository.deleteById(id);
    }



    @Override
    public LecturerResponseDTO assignCourse(Long lecturerId, AssignCourseDTO dto) {
        Lecturer lecturer = lecturerRepository.findById(lecturerId)
                .orElseThrow(() -> new RuntimeException(
                        "Lecturer not found with id: " + lecturerId));

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException(
                        "Course not found with id: " + dto.getCourseId()));

        lecturer.setAssignedCourse(course);
        Lecturer updated = lecturerRepository.save(lecturer);
        return mapToResponse(updated);
    }

    @Override
    public List<LecturerResponseDTO> getLecturersByDepartment(String department) {
        return lecturerRepository.findByDepartment(department)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private LecturerResponseDTO mapToResponse(Lecturer lecturer) {
        String type = (lecturer instanceof PermanentLecturer)
                ? "PERMANENT" : "VISITING";

        Long courseId = null;
        String courseName = null;
        if (lecturer.getAssignedCourse() != null) {
            courseId = lecturer.getAssignedCourse().getId();
            courseName = lecturer.getAssignedCourse().getCourseName();
        }
        // Return response DTO
        return new LecturerResponseDTO(
                lecturer.getId(),
                lecturer.getName(),
                lecturer.getEmail(),
                lecturer.getPhone(),
                lecturer.getDepartment(),
                type,
                lecturer.calculateWorkload(), // Polymorphism
                courseId,
                courseName
        );
    }
}