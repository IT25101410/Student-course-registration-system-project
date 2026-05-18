package com.shadow.studentcoursemanagementsystem.repository;

import com.shadow.studentcoursemanagementsystem.model.Student;  //Call Student.java in model package
import org.springframework.data.jpa.repository.JpaRepository;  //JpaRepository automatically creates standard CRUD
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface StudentRepository extends JpaRepository<Student,Long>{  //This extend helps to get instant methods - save(Student) findById(ID), deleteById(ID)....
    Optional<Student> findByEmail(String email);  //fetche student from database using their email
    boolean existsByEmail(String email);   //this checkes if email already in the DB
}


    //This java class helps to extend Jpa repository and database queries