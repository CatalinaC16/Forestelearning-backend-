package tech.getarrays.forestelearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.forestelearning.model.Course;
import tech.getarrays.forestelearning.wrapper.CourseWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/courses")
public interface CourseController {

    @GetMapping("/getAllCourses")
    ResponseEntity<List<CourseWrapper>> getAllCourses();

    @GetMapping("/getCourse/{id}")
    ResponseEntity<Course> getCourseById(@PathVariable Long id);

    @PostMapping("/deleteCourse/{id}")
    ResponseEntity<String> deleteCourseById(@PathVariable Long id);

    @PostMapping(path = "/add")
    ResponseEntity<String> createCourse(@RequestBody(required = true) Map<String, String> requestMap);

}
