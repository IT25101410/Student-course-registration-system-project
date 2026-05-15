package com.shadow.studentcoursemanagementsystem.controller;

import com.sliit.scrs.dto.*;  // Import all DTO (Data Transfer Object) classes
import com.sliit.scrs.service.LecturerService;  // Import LecturerService class
import org.springframework.beans.factory.annotation.Autowired; // Used for dependency injection
import org.springframework.http.ResponseEntity; // Used to send HTTP responses
import org.springframework.web.bind.annotation.*; // Import Spring REST annotations
import java.util.List; // Import List collection

@RestController // Marks this class as a REST API controller
@RequestMapping("/api/lecturers") // Base URL for all lecturer-related APIs

public class CourseController {
}
