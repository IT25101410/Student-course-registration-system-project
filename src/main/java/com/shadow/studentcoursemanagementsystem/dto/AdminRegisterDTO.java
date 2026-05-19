package com.shadow.studentcoursemanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminRegisterDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String role; // "SUPER_ADMIN" or "ADMIN"

    //......

}
