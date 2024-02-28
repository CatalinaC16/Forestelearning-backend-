package tech.getarrays.forestelearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tech.getarrays.forestelearning.model.Quiz;
import tech.getarrays.forestelearning.wrapper.QuizWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/quizes")
public interface QuizController {

    @GetMapping("/getQuiz/{id}")
    ResponseEntity<Quiz> getQuizById(@PathVariable Long id);

    @PostMapping("/deleteQuiz/{id}")
    ResponseEntity<String> deleteQuizById(@PathVariable Long id);

    @PostMapping(path = "/add")
    ResponseEntity<String> createQuiz(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping("/getAllQuizes")
    ResponseEntity<List<QuizWrapper>> getAllQuizes();

}
