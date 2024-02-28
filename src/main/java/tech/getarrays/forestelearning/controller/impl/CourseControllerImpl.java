package tech.getarrays.forestelearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.forestelearning.constants.UserConstants;
import tech.getarrays.forestelearning.controller.CourseController;
import tech.getarrays.forestelearning.model.Course;
import tech.getarrays.forestelearning.service.CourseService;
import tech.getarrays.forestelearning.utils.UserUtils;
import tech.getarrays.forestelearning.wrapper.CourseWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CourseControllerImpl implements CourseController {

    @Autowired
    CourseService courseService;

    @Override
    public ResponseEntity<List<CourseWrapper>> getAllCourses() {
        try {
            return courseService.getAllCourses();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Course> getCourseById(Long id) {
        try {
            return courseService.getCourseById(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> createCourse(Map<String, String> requestMap) {
        try {
            return courseService.createCourse(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG_CREATE_COURSE,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteCourseById(Long id) {
        try {
            return courseService.deleteCourseById(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
