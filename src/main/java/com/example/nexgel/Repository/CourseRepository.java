package com.example.nexgel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.nexgel.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
