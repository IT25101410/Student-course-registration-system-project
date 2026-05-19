package com.shadow.studentcoursemanagementsystem.service.Impl;

import com.shadow.studentcoursemanagementsystem.dto.*;
import com.shadow.studentcoursemanagementsystem.model.Admin;
import com.shadow.studentcoursemanagementsystem.*;
//import com.shadow.studentcoursemanagementsystem.AdminService;
import com.shadow.studentcoursemanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Connecting all modules — system integration
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private LecturerRepository lecturerRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public AdminResponseDTO registerAdmin(AdminRegisterDTO dto) {

        if (adminRepository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException(
                    "Username already taken: " + dto.getUsername());
        }

        if (adminRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException(
                    "Email already registered: " + dto.getEmail());
        }

        Admin admin = new Admin(
                null,
                dto.getUsername(),
                dto.getPassword(), // In production use BCrypt
                dto.getFullName(),
                dto.getEmail(),
                dto.getRole()
        );

        Admin saved = adminRepository.save(admin);
        return mapToResponse(saved);
    }

    @Override
    public AdminResponseDTO login(AdminLoginDTO dto) {

        // Encapsulation: login logic hidden in service
        Admin admin = adminRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException(
                        "Invalid username or password."));

        if (!admin.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid username or password.");
        }

        return mapToResponse(admin);
    }

    @Override
    public List<AdminResponseDTO> getAllAdmins() {
        return adminRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AdminResponseDTO getAdminById(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Admin not found with id: " + id));
        return mapToResponse(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin not found with id: " + id);
        }
        adminRepository.deleteById(id);
    }

    // System integration: pulls stats from ALL modules
    @Override
    public SystemStatsDTO getSystemStats() {

        long totalStudents    = studentRepository.count();
        long totalCourses     = courseRepository.count();
        long totalEnrollments = enrollmentRepository.count();
        long totalLecturers   = lecturerRepository.count();
        long totalPayments    = paymentRepository.count();

        double totalRevenue = paymentRepository.findAll()
                .stream()
                .filter(p -> "COMPLETED".equals(p.getStatus()))
                .mapToDouble(p -> p.getAmount())
                .sum();

        return new SystemStatsDTO(
                totalStudents,
                totalCourses,
                totalEnrollments,
                totalLecturers,
                totalPayments,
                totalRevenue
        );
    }

    private AdminResponseDTO mapToResponse(Admin admin) {
        return new AdminResponseDTO(
                admin.getId(),
                admin.getUsername(),
                admin.getFullName(),
                admin.getEmail(),
                admin.getRole()
        );
    }
}
