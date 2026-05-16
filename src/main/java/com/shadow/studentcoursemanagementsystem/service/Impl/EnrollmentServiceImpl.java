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
