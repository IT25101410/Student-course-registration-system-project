package com.sliit.scrs.service.IMPL;


import com.sliit.scrs.dto.*;
import com.sliit.scrs.model.*;
import com.sliit.scrs.repository.StudentRepository;
import com.sliit.scrs.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceIMPL implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentResponseDTO addStudent(StudentRequestDTO dto) {
        Student student;

        if ("UNDERGRADUATE".equalsIgnoreCase(dto.getType())) {
            student = new UndergraduateStudent(null, dto.getName(), dto.getEmail(),
                    dto.getPhone(), dto.getYearOfStudy());
        } else {
            student = new PostgraduateStudent(null, dto.getName(), dto.getEmail(),
                    dto.getPhone(), dto.getResearchArea());
        }

        Student saved = studentRepository.save(student);
        return mapToResponse(saved);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return mapToResponse(student);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPhone(dto.getPhone());

        if (student instanceof UndergraduateStudent ug) {
            ug.setYearOfStudy(dto.getYearOfStudy());
        } else if (student instanceof PostgraduateStudent pg) {
            pg.setResearchArea(dto.getResearchArea());
        }

        Student updated = studentRepository.save(student);
        return mapToResponse(updated);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    private StudentResponseDTO mapToResponse(Student student) {
        String type = (student instanceof UndergraduateStudent) ? "UNDERGRADUATE" : "POSTGRADUATE";
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getPhone(),
                type,
                student.calculateFee()  // Polymorphism in action
        );
    }
}
