package tech.getarrays.forestelearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.getarrays.forestelearning.dao.CourseDAO;
import tech.getarrays.forestelearning.dao.ProgressUserDAO;
import tech.getarrays.forestelearning.dao.QuizDAO;
import tech.getarrays.forestelearning.model.Course;
import tech.getarrays.forestelearning.model.Quiz;
import tech.getarrays.forestelearning.service.QuizService;
import tech.getarrays.forestelearning.wrapper.QuizWrapper;

import java.util.*;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    CourseDAO courseDAO;

    @Autowired
    ProgressUserDAO progressUserDAO;

    @Override
    public ResponseEntity<String> createQuiz(Map<String, String> requestMap) {
        try {
            Quiz quiz = quizDAO.findByTitle(requestMap.get("title"));
            if (!Objects.isNull(quiz)) {
                return new ResponseEntity<>("Quizul cu titlul dat există deja.", HttpStatus.BAD_REQUEST);
            }
            Quiz newQuiz = new Quiz();
            newQuiz.setTitle(requestMap.get("title"));
            Course course = courseDAO.findByTitle(requestMap.get("title"));
            newQuiz.setCourse(course);
            newQuiz.setCorrectOptions(requestMap.get("correct_options"));
            newQuiz.setHtmlDescription(requestMap.get("html_description"));
            quizDAO.save(newQuiz);
            return new ResponseEntity<>("Quizul a fost creat cu succes.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ceva nu a mers bine la crearea quizului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<QuizWrapper>> getAllQuizes() {
        try {
            return new ResponseEntity<>(quizDAO.getAllQuizes(),HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Quiz> getQuizById(Long id) {
        try {
            return new ResponseEntity<>(quizDAO.findById(id),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> deleteQuizById(Long id) {
        try {
            Quiz quiz= quizDAO.findById(id);
            if (Objects.isNull(quiz)) {
                return new ResponseEntity<>("Quiz cu ID-ul dat nu există.", HttpStatus.NOT_FOUND);
            }
            progressUserDAO.deleteByQuizId(id);
            quizDAO.deleteById(id);
            return new ResponseEntity<>("Quizul a fost șters cu succes.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ceva nu a mers bine la ștergerea quizului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
