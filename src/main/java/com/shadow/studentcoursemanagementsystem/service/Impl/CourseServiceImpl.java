package com.shadow.studentcoursemanagementsystem.service.Impl;


import com.shadow.studentcoursemanagementsystem.repository.CourseRepository;
import com.shadow.studentcoursemanagementsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    //Connect to Course Repository
    @Autowired
    private CourseRepository courseRepository;



}
