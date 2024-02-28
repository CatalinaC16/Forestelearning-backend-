package tech.getarrays.forestelearning.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import tech.getarrays.forestelearning.constants.UserConstants;
import tech.getarrays.forestelearning.controller.UserController;
import tech.getarrays.forestelearning.model.User;
import tech.getarrays.forestelearning.service.UserService;
import tech.getarrays.forestelearning.utils.UserUtils;
import tech.getarrays.forestelearning.wrapper.UserWrapper;

import java.lang.constant.Constable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserControllerImpl implements UserController {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
       try{
           return userService.signUp(requestMap);
       }catch (Exception exception){
           exception.printStackTrace();
       }
      return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG_SIGNUP,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> login(Map<String, String> requestMap) {
        try{
            return userService.login(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(Collections.singletonMap("message", UserConstants.SOMETHING_WENT_WRONG),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
       try {
            return userService.getAllUsers();
       }catch (Exception exception){
           exception.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsersStudents() {
        try {
            return userService.getAllUsersStudents();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try{
            return userService.update(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG_UPDATE_USER,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        try{
            return userService.checkToken();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try{
            return userService.changePassword(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try{
            return userService.forgotPassword(requestMap);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteUserById(Integer id) {
        try {
            return userService.deleteUserById(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<User> getUserById(Integer id) {
        try {
            return userService.getUserById(id);
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
