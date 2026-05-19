package com.shadow.studentcoursemanagementsystem.controller;

import com.sliit.scrs.dto.*;  // Import all DTO (Data Transfer Object) classes
import com.sliit.scrs.service.LecturerService;  // Import LecturerService class
import org.springframework.beans.factory.annotation.Autowired; // Used for dependency injection
import org.springframework.http.ResponseEntity; // Used to send HTTP responses
import org.springframework.web.bind.annotation.*; // Import Spring REST annotations
import java.util.List; // Import List collection

@RestController // Marks this class as a REST API controller
@RequestMapping("/api/lecturers") // Base URL for all lecturer-related APIs

public class LecturerController {

    @Autowired
    private LecturerService lecturerService;

    // Add lecturer
    @PostMapping
    public ResponseEntity<LecturerResponseDTO> addLecturer(
            @RequestBody LecturerRequestDTO dto) { // Takes lecturer details from request body
        return ResponseEntity.ok(lecturerService.addLecturer(dto)); // Call service layer and return saved lecturer
    }

    // View all lecturers
    @GetMapping
    public ResponseEntity<List<LecturerResponseDTO>> getAllLecturers() {
        return ResponseEntity.ok(lecturerService.getAllLecturers()); // Return all lecturers from database
    }

    // View one lecturer
    @GetMapping("/{id}")
    public ResponseEntity<LecturerResponseDTO> getLecturerById(
            @PathVariable Long id) { // Get lecturer ID from URL
        return ResponseEntity.ok(lecturerService.getLecturerById(id)); // Return lecturer details by ID
    }

    // Update lecturer
    @PutMapping("/{id}")
    public ResponseEntity<LecturerResponseDTO> updateLecturer(
            @PathVariable Long id, // Get lecturer ID from URL
            @RequestBody LecturerRequestDTO dto) { // Get updated lecturer data from request body
        return ResponseEntity.ok(lecturerService.updateLecturer(id, dto)); // Update lecturer and return updated data
    }

    // Delete lecturer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLecturer(@PathVariable Long id) {
        lecturerService.deleteLecturer(id); // Call service method to delete lecturer
        return ResponseEntity.ok("Lecturer deleted successfully."); // Return success message
    }

    // Assign course to lecturer
    @PatchMapping("/{id}/assign-course")
    public ResponseEntity<LecturerResponseDTO> assignCourse(
            @PathVariable Long id,
            @RequestBody AssignCourseDTO dto) { // Get course assignment details from request body
        return ResponseEntity.ok(lecturerService.assignCourse(id, dto)); // Assign course and return updated lecturer details
    }

    // View lecturers by department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<LecturerResponseDTO>> getByDepartment(
            @PathVariable String department) { // Get department name from URL

        return ResponseEntity.ok(lecturerService.getLecturersByDepartment(department)); // Return lecturers belonging to given department
    }
}
