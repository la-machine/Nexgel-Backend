package com.example.demo.course;

import com.example.demo.coursedocument.CourseDocumentService;
import com.example.demo.coursedocument.CourseDocuments;
import com.example.demo.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;
    //create
    public Course createCourse(Course course){

        return courseRepository.save(course);
    }

    //read
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    //delete
    public void deleteCourse(Long courseid){
        courseRepository.deleteById(courseid);
    }
    //Update
    public Course updateCourse(long courseid, Course course){
        Course cours= courseRepository.findById(courseid).get();
        cours.setTitle(cours.getTitle());
        cours.setDescription(cours.getDescription());
        cours.setCourseLogo(cours.getCourseLogo());
        return courseRepository.save(cours);
    }

}
