package tech.getarrays.forestelearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.getarrays.forestelearning.dao.CourseDAO;
import tech.getarrays.forestelearning.dao.QuizDAO;
import tech.getarrays.forestelearning.model.Course;
import tech.getarrays.forestelearning.service.CourseService;
import tech.getarrays.forestelearning.wrapper.CourseWrapper;

import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    QuizDAO quizDAO;

    @Override
    public ResponseEntity<List<CourseWrapper>> getAllCourses() {
        try {
            return new ResponseEntity<>(courseDAO.getAllCourses(),HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> createCourse(Map<String, String> requestMap) {
        try {
            Course course = courseDAO.findByTitle(requestMap.get("title"));
            if (!Objects.isNull(course)) {
                return new ResponseEntity<>("Cursul cu titlul dat există deja.", HttpStatus.BAD_REQUEST);
            }
            Course newCourse = new Course();
            newCourse.setTitle(requestMap.get("title"));
            newCourse.setImageUrl(requestMap.get("image_url"));
            newCourse.setHtmlDescription(requestMap.get("html_description"));
            courseDAO.save(newCourse);
            return new ResponseEntity<>("Cursul a fost creat cu succes.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ceva nu a mers bine la crearea cursului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Course> getCourseById(Long id) {
        try {
            return new ResponseEntity<>(courseDAO.findById(id),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteCourseById(Long id) {
        try {
           Course course= courseDAO.findById(id);
            if (Objects.isNull(course)) {
                return new ResponseEntity<>("Cursul cu ID-ul dat nu există.", HttpStatus.NOT_FOUND);
            }
            quizDAO.deleteByCursId(id);
            courseDAO.deleteById(id);
            return new ResponseEntity<>("Cursul a fost șters cu succes.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ceva nu a mers bine la ștergerea cursului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
