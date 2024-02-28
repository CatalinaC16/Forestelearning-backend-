package tech.getarrays.forestelearning.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tech.getarrays.forestelearning.model.User;
import tech.getarrays.forestelearning.wrapper.UserWrapper;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {

    User findByEmailId(@Param("email") String email);

    List<UserWrapper> getAllUsers();

    List<UserWrapper> getAllUsersStudents();

    User findByEmail(String email);

    User getById(@Param("id") Integer id);

    @Transactional
    void deleteById(Integer id);
}
