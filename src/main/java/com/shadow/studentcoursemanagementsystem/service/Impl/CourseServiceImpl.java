package com.shadow.studentcoursemanagementsystem.service.Impl;


import com.shadow.studentcoursemanagementsystem.dto.CourseRequestDTO;
import com.shadow.studentcoursemanagementsystem.dto.CourseResponseDTO;
import com.shadow.studentcoursemanagementsystem.model.CoreCourse;
import com.shadow.studentcoursemanagementsystem.model.Course;
import com.shadow.studentcoursemanagementsystem.model.ElectiveCourse;
import com.shadow.studentcoursemanagementsystem.repository.CourseRepository;
import com.shadow.studentcoursemanagementsystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    //Connect to Course Repository
    @Autowired
    private CourseRepository courseRepository;

    // Check the Course code in the Database
    public CourseResponseDTO addCourse(CourseRequestDTO dto){
        // If not have
        if(courseRepository.existsByCourseCode(dto.getCourseCode())){
            throw new RuntimeException("Course code already exists");
        }
        //If have
        Course course;

        if("CORE".equalsIgnoreCase(dto.getType())){
            course = new CoreCourse(
                    null,
                    dto.getCourseCode(),
                    dto.getCourseName(),
                    dto.getDepartment(),
                    dto.getCredits(),
                    dto.getIsMandatory() != null && dto.getIsMandatory()
            );
        }
        else{
            course = new ElectiveCourse(
                    null,
                    dto.getCourseCode(),
                    dto.getCourseName(),
                    dto.getDepartment(),
                    dto.getCredits(),
                    dto.getElectiveCategory()
            );
        }
        //Save in Database
        Course saved = courseRepository.save(course);
        // Save data convert into CourseResponseDTO
        return mapToResponse(saved);
    }
    



}
