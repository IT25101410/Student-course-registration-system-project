package com.shadow.studentcoursemanagementsystem.service;

import com.shadow.studentcoursemanagementsystem.dto.*;
import java.util.List;

public interface StudentService {
    StudentResponseDTO addStudent(StudentRequestDTO dto);
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto);
    void deleteStudent(Long id);
    StudentResponseDTO getStudentById(Long id);
    List<StudentResponseDTO> getAllStudents();
}
