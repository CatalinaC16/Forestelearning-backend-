package tech.getarrays.forestelearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import tech.getarrays.forestelearning.constants.UserConstants;
import tech.getarrays.forestelearning.controller.QuizController;
import tech.getarrays.forestelearning.model.Course;
import tech.getarrays.forestelearning.model.Quiz;
import tech.getarrays.forestelearning.service.QuizService;
import tech.getarrays.forestelearning.utils.UserUtils;
import tech.getarrays.forestelearning.wrapper.CourseWrapper;
import tech.getarrays.forestelearning.wrapper.QuizWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class QuizControllerImpl implements QuizController {

    @Autowired
    QuizService quizService;

    @Override
    public ResponseEntity<Quiz> getQuizById(Long id) {
        try {
            return quizService.getQuizById(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<QuizWrapper>> getAllQuizes() {
        try {
            return quizService.getAllQuizes();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteQuizById(Long id) {
        try {
            return quizService.deleteQuizById(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> createQuiz(Map<String, String> requestMap) {
        try {
            return quizService.createQuiz(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG_CREATE_QUIZ,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
