package com.shadow.studentcoursemanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;


    @Entity
    @Table(name = "admins")
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Admin {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, unique = true)
        private String username;

        // Encapsulation: password stored securely
        @Column(nullable = false)
        private String password;

        @Column(nullable = false)
        private String fullName;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        private String role; // "SUPER_ADMIN" or "ADMIN"
    }

//after pom change and cloned

