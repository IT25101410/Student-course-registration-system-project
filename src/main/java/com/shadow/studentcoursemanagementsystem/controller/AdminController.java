package com.shadow.studentcoursemanagementsystem.controller;

import com.shadow.studentcoursemanagementsystem.dto.*;
import com.shadow.studentcoursemanagementsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Register new admin
    @PostMapping("/register")
    public ResponseEntity<AdminResponseDTO> register(
            @RequestBody AdminRegisterDTO dto) {
        return ResponseEntity.ok(adminService.registerAdmin(dto));
    }

    // Admin login
    @PostMapping("/login")
    public ResponseEntity<AdminResponseDTO> login(
            @RequestBody AdminLoginDTO dto) {
        return ResponseEntity.ok(adminService.login(dto));
    }

    // View all admins
    @GetMapping
    public ResponseEntity<List<AdminResponseDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    // View one admin
    @GetMapping("/{id}")
    public ResponseEntity<AdminResponseDTO> getAdminById(
            @PathVariable Long id) {
        return ResponseEntity.ok(adminService.getAdminById(id));
    }

    // Delete admin
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok("Admin deleted successfully.");
    }

    // System dashboard — all module stats
    @GetMapping("/dashboard")
    public ResponseEntity<SystemStatsDTO> getDashboard() {
        return ResponseEntity.ok(adminService.getSystemStats());
    }
}
