package tech.getarrays.forestelearning.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import tech.getarrays.forestelearning.model.Course;
import tech.getarrays.forestelearning.wrapper.CourseWrapper;

import java.util.List;
import java.util.Map;

public interface CourseService {

    ResponseEntity<List<CourseWrapper>> getAllCourses();
    ResponseEntity<String> createCourse(Map<String,String> requestMap);
    ResponseEntity<Course> getCourseById(@PathVariable Long id);
    ResponseEntity<String> deleteCourseById(@PathVariable Long id);

}
