package tech.getarrays.forestelearning.service.impl;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import tech.getarrays.forestelearning.constants.UserConstants;
import tech.getarrays.forestelearning.dao.UserDAO;
import tech.getarrays.forestelearning.jwt.JwtFilter;
import tech.getarrays.forestelearning.jwt.JwtUtils;
import tech.getarrays.forestelearning.jwt.UserDetailsService;
import tech.getarrays.forestelearning.model.User;
import tech.getarrays.forestelearning.service.UserService;
import tech.getarrays.forestelearning.utils.EmailUtils;
import tech.getarrays.forestelearning.utils.UserUtils;
import tech.getarrays.forestelearning.wrapper.UserWrapper;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserUtils userUtils;

    @Autowired
    EmailUtils emailUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Inside signup {}", requestMap);
        try {
            System.out.println(requestMap);
            if (this.userUtils.validateSignUpMap(requestMap)) {
                User user = userDAO.findByEmailId(requestMap.get("email"));
                if (Objects.isNull(user)) {
                    userDAO.save(this.userUtils.getUserFromMap(requestMap));
                    return UserUtils.getResponseEntity("Contul a fost creat cu succes. Bine ați venit!", HttpStatus.OK);
                } else {
                    return UserUtils.getResponseEntity("Email-ul pe care l-ați introdus există deja!", HttpStatus.BAD_REQUEST);
                }
            } else {
                return UserUtils.getResponseEntity(UserConstants.INVALID_DATA_AT_SIGNUP, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG_SIGNUP, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Map<String, Object>> login(Map<String, String> requestMap) {
        log.info("Inside login");
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));
            if (authentication.isAuthenticated()) {
                if (userDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
                    String token = jwtUtils.generateToken(userDetailsService.getUserDetail().getEmail(), userDetailsService.getUserDetail().getRole());

                    Map<String, Object> response = new HashMap<>();
                    response.put("token", token);
                    response.put("user", userDetailsService.getUserDetail());

                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(Collections.singletonMap("message", UserConstants.WAIT_FOR_ADMIN_APPROVE), HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception exception) {
            log.error("{}", exception);
        }
        return new ResponseEntity<>(Collections.singletonMap("message", UserConstants.INVALID_DATA_AT_SIGNUP), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try {
            return new ResponseEntity<>(userDAO.getAllUsers(), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsersStudents() {
        try {
            return new ResponseEntity<>(userDAO.getAllUsersStudents(), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMap) {
        try {
            User user = userDAO.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) {
                user.setName(requestMap.get("name"));
                user.setRole(requestMap.get("role"));
                user.setContactNumber(requestMap.get("contactNumber"));
                userDAO.save(user);
                return UserUtils.getResponseEntity("Date modificate cu succes!", HttpStatus.OK);
            }
            return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return UserUtils.getResponseEntity("true", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
        try {
            User user = userDAO.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) {
                if (user.getPassword().equals(requestMap.get("oldPassword"))) {
                    user.setPassword(requestMap.get("newPassword"));
                    userDAO.save(user);
                    return UserUtils.getResponseEntity("Password Updated Successfully!", HttpStatus.OK);
                }
                return UserUtils.getResponseEntity("Incorrect old password", HttpStatus.BAD_REQUEST);
            }
            return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
        try {
            User user = userDAO.findByEmail(requestMap.get("email"));
            if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail())) {
                emailUtils.forgotMail(user.getEmail(), "Amintește parola pentru ForestryEdu!", user.getPassword());
                return UserUtils.getResponseEntity("Parola a fost trimisă cu succes!", HttpStatus.OK);
            }
            return UserUtils.getResponseEntity("Adresa de email nu a fost găsită!", HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            ex.printStackTrace();
            return UserUtils.getResponseEntity(UserConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<User> getUserById(Integer id) {
        try {
            User user= userDAO.getById(id);
            if (Objects.isNull(user)) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteUserById(Integer id) {
        try {
            User user= userDAO.getById(id);
            if (Objects.isNull(user)) {
                return new ResponseEntity<>("Userul cu ID-ul dat nu există.", HttpStatus.NOT_FOUND);
            }
            userDAO.deleteById(id);
            return new ResponseEntity<>("Userul a fost șters cu succes.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Ceva nu a mers bine la ștergerea userului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
