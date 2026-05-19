package com.shadow.studentcoursemanagementsystem.service;

import com.shadow.studentcoursemanagementsystem.dto.*;


import java.util.List;

public interface LecturerService {
    LecturerResponseDTO addLecturer(LecturerRequestDTO dto);
    List<LecturerResponseDTO> getAllLecturers();
    LecturerResponseDTO getLecturerById(Long id);
    LecturerResponseDTO updateLecturer(Long id, LecturerRequestDTO dto);
    void deleteLecturer(Long id);
    LecturerResponseDTO assignCourse(Long lecturerId, AssignCourseDTO dto);
    List<LecturerResponseDTO> getLecturersByDepartment(String department);
}
