package tech.getarrays.forestelearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tech.getarrays.forestelearning.model.Course;
import tech.getarrays.forestelearning.wrapper.CourseWrapper;


import java.util.List;

public interface CourseDAO extends JpaRepository<Course, Integer> {

    List<CourseWrapper> getAllCourses();

    Course findById(@Param("id") Long id);

    Course findByTitle(@Param("title") String title);

    @Transactional
    void deleteById(Long id);
}
