package com.shadow.studentcoursemanagementsystem.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDTO {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String role;
}
