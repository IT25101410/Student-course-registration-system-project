package com.shadow.studentcoursemanagementsystem.repository;

import com.shadow.studentcoursemanagementsystem.model.Student;  //Call Student.java in model package
import org.springframework.data.jpa.repository.JpaRepository;  //JpaRepository automatically creates standard CRUD
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
    Optional<Student> findByEmail(String email);
    boolean existsByEmail(String email);
}
