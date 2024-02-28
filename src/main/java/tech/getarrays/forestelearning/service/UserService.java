package tech.getarrays.forestelearning.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import tech.getarrays.forestelearning.model.User;
import tech.getarrays.forestelearning.wrapper.UserWrapper;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseEntity<String> signUp(Map<String,String> requestMap);
    ResponseEntity<Map<String, Object>> login(Map<String,String> requestMap);
    ResponseEntity<List<UserWrapper>> getAllUsers();
    ResponseEntity<List<UserWrapper>> getAllUsersStudents();
    ResponseEntity<String> update(Map<String,String> requestMap);
    ResponseEntity<String> checkToken();
    ResponseEntity<String> changePassword(Map<String,String> requestMap);
    ResponseEntity<String> forgotPassword(Map<String,String> requestMap);
    ResponseEntity<String> deleteUserById(@PathVariable Integer id);
    ResponseEntity<User> getUserById(@PathVariable Integer id);
}
