//package com.shadow.studentcoursemanagementsystem.controller;
//
//public class StudentDashboardController {
//}
package  com.shadow.studentcoursemanagementsystem.controller ;

import  com.shadow.studentcoursemanagementsystem.dto.*;
import  com.shadow.studentcoursemanagementsystem.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentDashboardController {

    private static final Logger logger = LoggerFactory.getLogger(StudentDashboardController.class);

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private PaymentService paymentService;


    @ModelAttribute
    public void addGlobalSidebarAttributes(Principal principal, Model model) {
        if (principal != null) {
            StudentResponseDTO student = getSafeStudent(principal);
            model.addAttribute("student", student);

            if (isRealStudent(student)) {
                try {
                    List<EnrollmentResponseDTO> enrollments = enrollmentService.getEnrollmentsByStudentId(student.getId());
                    model.addAttribute("enrollments", enrollments != null ? enrollments : Collections.emptyList());
                } catch (Exception e) {
                    logger.error("[SCRS] Global filter failed loading enrollments for student {}: {}", student.getId(), e.getMessage());
                    model.addAttribute("enrollments", Collections.emptyList());
                }

                try {
                    List<PaymentResponseDTO> payments = (List<PaymentResponseDTO>) paymentService.getPaymentsByStudentId(student.getId());
                    model.addAttribute("payments", payments != null ? payments : Collections.emptyList());
                } catch (Exception e) {
                    logger.error("[SCRS] Global filter failed loading payments for student {}: {}", student.getId(), e.getMessage());
                    model.addAttribute("payments", Collections.emptyList());
                }
            } else {
                model.addAttribute("enrollments", Collections.emptyList());
                model.addAttribute("payments", Collections.emptyList());
            }
        }
    }

    /**
     * Attempts to load the student dynamically from the DB using the active session credentials.
     */
    private StudentResponseDTO getSafeStudent(Principal principal) {
        if (principal == null) {
            return createMockStudent("guest@sliit.lk", "Guest User");
        }

        String identifier = principal.getName();
        try {
            // CRITICAL CHECK: Fetch by email first.
            // If your login uses Student ID instead of Email, make sure your service supports lookup by Username/ID.
            StudentResponseDTO student = studentService.getStudentByEmail(identifier);
            if (student != null) {
                return student;
            }
        } catch (Exception e) {
            logger.error("[SCRS] Student database lookup failed for credential [{}]: {}", identifier, e.getMessage());
        }

        // Dynamic Mock Generation: Fallback if the user is logged in but not linked in the Student Table
        return createMockStudent(identifier, "User Account (" + identifier + ")");
    }

    private StudentResponseDTO createMockStudent(String email, String name) {
        StudentResponseDTO mock = new StudentResponseDTO();
        mock.setId(null); // Real DB records will have an ID, Mocks won't.
        mock.setName(name);
        mock.setEmail(email.contains("@") ? email : email + "@sliit.lk");
        mock.setPhone("N/A");
        mock.setType("Undergraduate");
        return mock;
    }

    /** Returns true if the student has a valid DB verification record */
    private boolean isRealStudent(StudentResponseDTO student) {
        return student != null && student.getId() != null;
    }

    // ==================== DASHBOARD ====================

    @GetMapping("/dashboard")
    public String dashboard(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }
        // Note: 'student', 'enrollments', and 'payments' are auto-injected by addGlobalSidebarAttributes()
        model.addAttribute("currentPage", "dashboard");
        return "student/dashboard";
    }

    // ==================== PROFILE ====================

    @GetMapping({"/profile", "/profile/{id}"})
    public String viewProfile(Principal principal,
                              @PathVariable(required = false) Long id,
                              Model model) {
        if (principal == null) return "redirect:/login";

        // FIXED: No longer crashes here because sidebar data is injected automatically now.
        model.addAttribute("currentPage", "profile");
        return "student/profile";
    }

    @GetMapping({"/profile/edit", "/profile/edit/{id}"})
    public String editProfile(Principal principal,
                              @PathVariable(required = false) Long id,
                              Model model) {
        if (principal == null) return "redirect:/login";

        StudentResponseDTO response = getSafeStudent(principal);
        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setName(response.getName());
        dto.setEmail(response.getEmail());
        dto.setPhone(response.getPhone());
        dto.setType(response.getType());

        // We override the global "student" model attribute specifically for the form data binding binding
        model.addAttribute("studentForm", dto);
        model.addAttribute("studentId", response.getId());
        model.addAttribute("currentPage", "profile");
        return "student/profile-form";
    }

    @PostMapping("/profile/update")
    public String updateProfile(Principal principal,
                                @ModelAttribute("studentForm") StudentRequestDTO dto,
                                RedirectAttributes redirectAttributes) {
        if (principal == null) return "redirect:/login";
        try {
            StudentResponseDTO student = studentService.getStudentByEmail(principal.getName());
            if (student == null) throw new RuntimeException("Cannot update an unlinked authentication profile.");
            studentService.updateStudent(student.getId(), dto);
            redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
        } catch (Exception e) {
            logger.error("[SCRS] Profile update failed: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/student/profile";
    }

    // ==================== COURSES ====================

    @GetMapping("/courses")
    public String browseCourses(Principal principal,
                                @RequestParam(value = "success", required = false) String success,
                                @RequestParam(value = "error", required = false) String error,
                                Model model) {
        try {
            model.addAttribute("courses", courseService.getAllCourses());
        } catch (Exception e) {
            logger.error("[SCRS] Error loading courses: {}", e.getMessage());
            model.addAttribute("courses", Collections.emptyList());
        }
        model.addAttribute("currentPage", "courses");
        if (success != null) model.addAttribute("success", success);
        if (error != null) model.addAttribute("error", error);
        return "student/courses";
    }

    @GetMapping("/courses/{id}")
    public String courseDetail(@PathVariable Long id, Model model) {
        Object course = null;
        try {
            course = courseService.getCourseById(id);
        } catch (Exception e) {
            logger.error("[SCRS] Course lookup failed for ID {}: {}", id, e.getMessage());
        }

        if (course == null) {
            CourseRequestDTO fallback = new CourseRequestDTO();
            fallback.setCourseCode("N/A");
            fallback.setCourseName("Course Not Found");
            model.addAttribute("course", fallback);
        } else {
            model.addAttribute("course", course);
        }
        model.addAttribute("currentPage", "courses");
        return "student/course-detail";
    }

    // ==================== ENROLLMENTS ====================

    @GetMapping("/enrollments")
    public String myEnrollments(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        model.addAttribute("currentPage", "enrollments");
        return "student/enrollments";
    }

    @PostMapping("/enrollments/save")
    public String saveEnrollment(Principal principal,
                                 @ModelAttribute EnrollmentRequestDTO dto,
                                 RedirectAttributes redirectAttributes) {
        if (principal == null) return "redirect:/login";

        try {
            StudentResponseDTO student = getSafeStudent(principal);

            if (!isRealStudent(student)) {
                throw new RuntimeException("This account credentials do not map to an active database student record.");
            }

            dto.setStudentId(student.getId());
            logger.info("[SCRS] Enrollment submission — StudentID: {}, CourseID: {}, Type: {}",
                    dto.getStudentId(), dto.getCourseId(), dto.getEnrollmentType());

            enrollmentService.enrollStudent(dto);
            redirectAttributes.addFlashAttribute("successMessage", "Successfully enrolled!");
            return "redirect:/student/enrollments";

        } catch (Exception e) {
            logger.error("[SCRS] Enrollment failed: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Failed: " + e.getMessage());
            return "redirect:/student/enrollments/new";
        }
    }

    // ==================== PAYMENTS ====================

    @GetMapping("/payments")
    public String myPayments(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        model.addAttribute("currentPage", "payments");
        return "student/payments";
    }

    @GetMapping("/payments/new")
    public String newPaymentForm(Principal principal, Model model) {
        if (principal == null) return "redirect:/login";

        StudentResponseDTO student = getSafeStudent(principal);
        model.addAttribute("studentId", student.getId());
        model.addAttribute("currentPage", "payments");
        return "student/payment-form";
    }

    @PostMapping("/payments/save")
    public String savePayment(Principal principal,
                              @ModelAttribute PaymentRequestDTO dto,
                              RedirectAttributes redirectAttributes) {
        if (principal == null) return "redirect:/login";

        try {
            StudentResponseDTO student = getSafeStudent(principal);

            if (!isRealStudent(student)) {
                throw new RuntimeException("This account credentials do not map to an active database student record.");
            }

            dto.setStudentId(student.getId());
            logger.info("[SCRS] Payment submission — StudentID: {}, Amount: {}, Type: {}",
                    dto.getStudentId(), dto.getAmount(), dto.getType());

            paymentService.makePayment(dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Payment of $" + String.format("%.2f", dto.getAmount()) + " submitted successfully!");
            return "redirect:/student/payments";

        } catch (Exception e) {
            logger.error("[SCRS] Payment failed: {}", e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Payment failed: " + e.getMessage());
            return "redirect:/student/payments/new";
        }
    }
}
