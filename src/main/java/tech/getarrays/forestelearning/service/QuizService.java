package tech.getarrays.forestelearning.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import tech.getarrays.forestelearning.model.Quiz;
import tech.getarrays.forestelearning.wrapper.CourseWrapper;
import tech.getarrays.forestelearning.wrapper.QuizWrapper;

import java.util.List;
import java.util.Map;

public interface QuizService {

    ResponseEntity<List<QuizWrapper>> getAllQuizes();
    ResponseEntity<String> createQuiz(Map<String,String> requestMap);
    ResponseEntity<Quiz> getQuizById(@PathVariable Long id);
    ResponseEntity<String> deleteQuizById(@PathVariable Long id);
}
