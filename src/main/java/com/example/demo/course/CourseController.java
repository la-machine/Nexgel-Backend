package com.example.demo.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/course",method = RequestMethod.POST)
    public Course createCourse(@RequestBody Course course){
        return courseService.createCourse(course);
    }
    @GetMapping(path = "course/allcourses")
    public List<Course> getCourses(){
        List<Course> getAll=courseRepository.findAll();
        return getAll;
    }
    @RequestMapping(value="/course/{courseid}", method=RequestMethod.PUT)
    public Course readCourse(@PathVariable(value = "courseid") Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @RequestMapping(value="/course/{courseid}", method=RequestMethod.DELETE)
    public void deleteCourse(@PathVariable(value = "courseid") Long id) {
        courseService.deleteCourse(id);
    }
}
