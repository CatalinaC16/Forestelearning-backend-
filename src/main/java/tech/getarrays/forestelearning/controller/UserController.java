package tech.getarrays.forestelearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.forestelearning.model.User;
import tech.getarrays.forestelearning.wrapper.UserWrapper;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/user")
public interface UserController {

    @PostMapping(path = "/signup")
    ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/login")
    ResponseEntity<Map<String, Object>> login(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/getAllUsers")
    ResponseEntity<List<UserWrapper>> getAllUsers();

    @GetMapping(path = "/getAllStudents")
    ResponseEntity<List<UserWrapper>> getAllUsersStudents();

    @PostMapping(path = "/update")
    ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path = "/checkToken")
    ResponseEntity<String> checkToken();

    @PostMapping(path = "/changePassword")
    ResponseEntity<String> changePassword(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path = "/forgotPassword")
    ResponseEntity<String> forgotPassword(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping("/deleteUser/{id}")
    ResponseEntity<String> deleteUserById(@PathVariable Integer id);

    @PostMapping("/getUser/{id}")
    ResponseEntity<User> getUserById(@PathVariable Integer id);
}
