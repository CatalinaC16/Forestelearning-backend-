package tech.getarrays.forestelearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tech.getarrays.forestelearning.model.ProgressUser;
import tech.getarrays.forestelearning.model.Quiz;
import tech.getarrays.forestelearning.model.User;
import tech.getarrays.forestelearning.wrapper.ProgressWrapper;

import java.util.List;
import java.util.Optional;

public interface ProgressUserDAO extends JpaRepository<ProgressUser, Integer> {

    List<ProgressUser> findByUserId(Integer user_id);

    List<ProgressWrapper> getAllProgress();

    Optional<ProgressUser> findByUserAndQuiz(User user, Quiz quiz);

    @Modifying
    @Transactional
    @Query("DELETE FROM ProgressUser p WHERE p.quiz.id = :quizId")
    void deleteByQuizId(@Param("quizId") Long quizId);
}
