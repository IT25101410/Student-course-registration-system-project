
package com.shadow.studentcoursemanagementsystem.controller;

import com.shadow.studentcoursemanagementsystem.dto.*;
import com.shadow.studentcoursemanagementsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class WebController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // This must match your login.html filename
    }


    // ==================== DASHBOARD ====================
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("stats", adminService.getSystemStats());
        model.addAttribute("currentPage", "dashboard");
        return "admin/dashboard";
    }

    // ==================== STUDENTS ====================
    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("currentPage", "students");
        return "students/list";
    }

    @GetMapping("/students/new")
    public String newStudent(Model model) {
        model.addAttribute("student", new StudentRequestDTO());
        model.addAttribute("studentId", null);
        model.addAttribute("currentPage", "students");
        return "students/form";
    }

    @PostMapping("/students/save")
    public String saveStudent(@ModelAttribute StudentRequestDTO dto,
                              RedirectAttributes redirectAttributes) {
        try {
            studentService.addStudent(dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Student added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        StudentResponseDTO response = studentService.getStudentById(id);
        StudentRequestDTO dto = new StudentRequestDTO();
        dto.setName(response.getName());
        dto.setEmail(response.getEmail());
        dto.setPhone(response.getPhone());
        dto.setType(response.getType());

        model.addAttribute("student", dto);
        model.addAttribute("studentId", id);
        model.addAttribute("currentPage", "students");
        return "students/form";
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute StudentRequestDTO dto,
                                RedirectAttributes redirectAttributes) {
        try {
            studentService.updateStudent(id, dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Student updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/students";  // ✅ Updated redirect path
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Student deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/students";
    }

    // ==================== COURSES ====================
    @GetMapping("/courses")
    public String courses(Model model) {
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("courseId", null);
        model.addAttribute("currentPage", "courses");
        return "courses/list";
    }

    @GetMapping("/courses/new")
    public String newCourse(Model model) {
        model.addAttribute("course", new CourseRequestDTO());
        model.addAttribute("currentPage", "courses");
        return "courses/form";
    }

    @PostMapping("/courses/save")
    public String saveCourse(@ModelAttribute CourseRequestDTO dto,
                             RedirectAttributes redirectAttributes) {
        try {
            courseService.addCourse(dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Course added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {
        CourseResponseDTO course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        model.addAttribute("courseId", id);
        model.addAttribute("currentPage", "courses");
        return "courses/form";
    }

    @PostMapping("/courses/update/{id}")
    public String updateCourse(@PathVariable Long id,
                               @ModelAttribute CourseRequestDTO dto,
                               RedirectAttributes redirectAttributes) {
        try {
            courseService.updateCourse(id, dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Course updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id,
                               RedirectAttributes redirectAttributes) {
        try {
            courseService.deleteCourse(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Course deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/courses";
    }

    // ==================== ENROLLMENTS ====================
    @GetMapping("/enrollments")
    public String enrollments(Model model) {
        model.addAttribute("enrollments", enrollmentService.getAllEnrollments());
        model.addAttribute("currentPage", "enrollments");
        return "enrollments/list";
    }

    @GetMapping("/enrollments/new")
    public String newEnrollment(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("currentPage", "enrollments");
        return "enrollments/form";
    }

    @PostMapping("/enrollments/save")
    public String saveEnrollment(@ModelAttribute EnrollmentRequestDTO dto,
                                 RedirectAttributes redirectAttributes) {
        try {
            enrollmentService.enrollStudent(dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Student enrolled successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/enrollments";
    }

    @GetMapping("/enrollments/drop/{id}")
    public String dropEnrollment(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            enrollmentService.dropCourse(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Course dropped successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/enrollments";
    }

    @GetMapping("/enrollments/delete/{id}")
    public String deleteEnrollment(@PathVariable Long id,
                                   RedirectAttributes redirectAttributes) {
        try {
            enrollmentService.dropCourse(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Enrollment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/enrollments";
    }

    // ==================== LECTURERS ====================
    @GetMapping("/lecturers")
    public String lecturers(Model model) {
        model.addAttribute("lecturers", lecturerService.getAllLecturers());
        model.addAttribute("currentPage", "lecturers");
        return "lecturers/list";
    }

    @GetMapping("/lecturers/new")
    public String newLecturer(Model model) {
        model.addAttribute("lecturer", new LecturerRequestDTO());
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("currentPage", "lecturers");
        return "lecturers/form";
    }

    @PostMapping("/lecturers/save")
    public String saveLecturer(@ModelAttribute LecturerRequestDTO dto,
                               RedirectAttributes redirectAttributes) {
        try {
            lecturerService.addLecturer(dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Lecturer added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/lecturers";  // ✅ Updated redirect path
    }

    @GetMapping("/lecturers/edit/{id}")
    public String editLecturer(@PathVariable Long id, Model model) {
        LecturerResponseDTO lecturer = lecturerService.getLecturerById(id);
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("lecturerId", id);
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("currentPage", "lecturers");
        return "lecturers/form";
    }

    @PostMapping("/lecturers/update/{id}")
    public String updateLecturer(@PathVariable Long id,
                                 @ModelAttribute LecturerRequestDTO dto,
                                 RedirectAttributes redirectAttributes) {
        try {
            lecturerService.updateLecturer(id, dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Lecturer updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/lecturers";
    }

    @GetMapping("/lecturers/assign/{id}")
    public String assignCourseForm(@PathVariable Long id, Model model) {
        LecturerResponseDTO lecturer = lecturerService.getLecturerById(id);
        model.addAttribute("lecturer", lecturer);
        model.addAttribute("courses", courseService.getAllCourses());
        model.addAttribute("currentPage", "lecturers");
        return "lecturers/assign";
    }

    @PostMapping("/lecturers/assign-course/{id}")
    public String assignCourse(@PathVariable Long id,
                               @ModelAttribute AssignCourseDTO dto,
                               RedirectAttributes redirectAttributes) {
        try {
            lecturerService.assignCourse(id, dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Course assigned successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/lecturers";
    }

    @GetMapping("/lecturers/delete/{id}")
    public String deleteLecturer(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            lecturerService.deleteLecturer(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Lecturer deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/lecturers";
    }

    // ==================== PAYMENTS ====================
    @GetMapping("/payments")
    public String payments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        model.addAttribute("currentPage", "payments");
        return "payments/list";
    }

    @GetMapping("/payments/new")
    public String newPayment(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("currentPage", "payments");
        return "payments/form";
    }

    @PostMapping("/payments/save")
    public String savePayment(@ModelAttribute PaymentRequestDTO dto,
                              RedirectAttributes redirectAttributes) {
        try {
            paymentService.makePayment(dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Payment recorded successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/payments";
    }

    @GetMapping("/payments/status/{id}")
    public String updateStatusForm(@PathVariable Long id, Model model) {
        PaymentResponseDTO payment = paymentService.getPaymentById(id);
        model.addAttribute("payment", payment);
        model.addAttribute("currentPage", "payments");
        return "payments/status";
    }

    @PostMapping("/payments/update-status/{id}")
    public String updateStatus(@PathVariable Long id,
                               @ModelAttribute UpdatePaymentStatusDTO dto,
                               RedirectAttributes redirectAttributes) {
        try {
            paymentService.updatePaymentStatus(id, dto);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Payment status updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/payments";
    }

    @GetMapping("/payments/delete/{id}")
    public String deletePayment(@PathVariable Long id,
                                RedirectAttributes redirectAttributes) {
        try {
            paymentService.deletePayment(id);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Payment deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/payments";
    }
}