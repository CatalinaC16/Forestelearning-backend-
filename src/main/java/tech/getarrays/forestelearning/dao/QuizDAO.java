package tech.getarrays.forestelearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tech.getarrays.forestelearning.model.Quiz;
import tech.getarrays.forestelearning.wrapper.CourseWrapper;
import tech.getarrays.forestelearning.wrapper.QuizWrapper;

import java.util.List;

public interface QuizDAO extends JpaRepository<Quiz, Integer> {

    List<QuizWrapper> getAllQuizes();

    Quiz findById(@Param("id") Long id);

    Quiz findByTitle(@Param("title") String title);

    @Transactional
    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Quiz p WHERE p.course.id = :cursId")
    void deleteByCursId(@Param("cursId") Long cursId);
}
