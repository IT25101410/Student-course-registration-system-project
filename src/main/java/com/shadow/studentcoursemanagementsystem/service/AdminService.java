package com.shadow.studentcoursemanagementsystem.service;

import com.shadow.studentcoursemanagementsystem.dto.AdminLoginDTO;
import com.shadow.studentcoursemanagementsystem.dto.AdminRegisterDTO;
import com.shadow.studentcoursemanagementsystem.dto.AdminResponseDTO;

import java.util.List;

public interface AdminService {
    AdminResponseDTO registerAdmin(AdminRegisterDTO dto);
    AdminResponseDTO login(AdminLoginDTO dto);
    List<AdminResponseDTO> getAllAdmins();
    AdminResponseDTO getAdminById(Long id);
    void deleteAdmin(Long id);
    //editttttttttt
   // SystemStatsDTO getSystemStats();
}