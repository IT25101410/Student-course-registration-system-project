package com.shadow.studentcoursemanagementsystem.service;

import com.shadow.studentcoursemanagementsystem.dto.CourseRequestDTO;
import com.shadow.studentcoursemanagementsystem.dto.CourseResponseDTO;

import java.util.List;

//Data input and output
public interface CourseService {
    CourseResponseDTO addCourse(CourseRequestDTO dto);
    // Read all Course
    List<CourseResponseDTO> getAllCourses();

    //Read only one Course
    CourseResponseDTO getCourseById(Long id);

}
