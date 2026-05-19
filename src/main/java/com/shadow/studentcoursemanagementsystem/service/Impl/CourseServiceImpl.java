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

import java.util.List;
import java.util.stream.Collectors;

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

    //Read All the Course
    @Override
    public List<CourseResponseDTO> getAllCourses() {
        return  courseRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    //Read the one Course
    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id : "+id));
        return mapToResponse(course);
    }

    @Override
    public CourseResponseDTO updateCourse(Long id,  CourseRequestDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        course.setCourseName(dto.getCourseName());
        course.setDepartment(dto.getDepartment());
        course.setCredits(dto.getCredits());

        if (course instanceof CoreCourse cc) {
            cc.setMandatory(dto.getIsMandatory() != null && dto.getIsMandatory());
        }
        else if (course instanceof ElectiveCourse ec) {
            ec.setElectiveCategory(dto.getElectiveCategory());
        }
        Course updated = courseRepository.save(course);
        return mapToResponse(updated);


    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    //Create the mapToResponse method
    private CourseResponseDTO mapToResponse(Course course){
        String type =(course instanceof CoreCourse) ? "CORE" : "ELECTIVE";
        return  new CourseResponseDTO(
                course.getId(),
                course.getCourseCode(),
                course.getCourseName(),
                course.getDepartment(),
                course.getCredits(),
                type,
                course.getDescription()

        );
    }
}
