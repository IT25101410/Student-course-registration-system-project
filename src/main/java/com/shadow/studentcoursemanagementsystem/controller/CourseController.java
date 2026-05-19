package com.shadow.studentcoursemanagementsystem.controller;


import com.shadow.studentcoursemanagementsystem.dto.CourseRequestDTO;
import com.shadow.studentcoursemanagementsystem.dto.CourseResponseDTO;
import com.shadow.studentcoursemanagementsystem.repository.CourseRepository;
import com.shadow.studentcoursemanagementsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // get HTTP Request from Frontend
@RequestMapping("/api/courses") // Create public URL path
public class CourseController {

    //Connect Course Service
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    // Create the new course
    @PostMapping
    public ResponseEntity<CourseResponseDTO>addCourse(@RequestBody CourseRequestDTO dto){

        return ResponseEntity.ok(courseService.addCourse(dto));
    }

    //Read All the Course
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    //Read One
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO>getCourseById(@PathVariable Long id){
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Long id,
            @RequestBody CourseRequestDTO dto) {
        return ResponseEntity.ok(courseService.updateCourse(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok("Course deleted successfully.");
    }



}
