package tech.getarrays.forestelearning.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.getarrays.forestelearning.dao.CourseDAO;
import tech.getarrays.forestelearning.dao.ProgressUserDAO;
import tech.getarrays.forestelearning.dao.QuizDAO;
import tech.getarrays.forestelearning.dao.UserDAO;
import tech.getarrays.forestelearning.model.ProgressUser;
import tech.getarrays.forestelearning.model.Quiz;
import tech.getarrays.forestelearning.model.User;
import tech.getarrays.forestelearning.service.ProgressService;
import tech.getarrays.forestelearning.wrapper.ProgressWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProgressServiceImpl implements ProgressService {

    @Autowired
    ProgressUserDAO progressUserDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    QuizDAO quizDAO;

    @Override
    public ResponseEntity<List<ProgressUser>> getProgressByUser(Long id) {
        try {
            return new ResponseEntity<>(progressUserDAO.findByUserId(Math.toIntExact(id)), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<List<ProgressWrapper>> getProgress() {
        try {
            return new ResponseEntity<>(progressUserDAO.getAllProgress(), HttpStatus.OK);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> addProgress(Map<String, String> requestMap) {
        try {
            Long userId = Long.parseLong(requestMap.get("userId"));
            Long quizId = Long.parseLong(requestMap.get("quizId"));
            Integer grade = Integer.parseInt(requestMap.get("grade"));

            Optional<User> userOptional = userDAO.findById(Math.toIntExact(userId));
            Optional<Quiz> quizOptional = Optional.ofNullable(quizDAO.findById(quizId));

            if (userOptional.isPresent() && quizOptional.isPresent()) {
                User user = userOptional.get();
                Quiz quiz = quizOptional.get();

                Optional<ProgressUser> existingProgressOptional = progressUserDAO.findByUserAndQuiz(user, quiz);

                if (existingProgressOptional.isPresent()) {
                    ProgressUser existingProgress = existingProgressOptional.get();
                    existingProgress.setGrade(grade);
                    progressUserDAO.save(existingProgress);
                } else {
                    ProgressUser newProgress = new ProgressUser();
                    newProgress.setUser(user);
                    newProgress.setQuiz(quiz);
                    newProgress.setGrade(grade);
                    progressUserDAO.save(newProgress);
                }

                return ResponseEntity.ok("Progress added successfully");
            } else {
                return ResponseEntity.badRequest().body("User or Quiz not found");
            }
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid input format");
        }
    }
}
