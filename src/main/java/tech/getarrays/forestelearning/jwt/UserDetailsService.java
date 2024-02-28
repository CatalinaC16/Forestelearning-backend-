package tech.getarrays.forestelearning.jwt;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.getarrays.forestelearning.dao.UserDAO;
import tech.getarrays.forestelearning.model.User;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserDAO userDAO;

    @Getter
    private User userDetail;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}",username);
        userDetail = userDAO.findByEmailId(username);
        if (!Objects.isNull(userDetail)) {
            return new org.springframework.security.core.userdetails.User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User getUserDetail(){
        return userDetail;
    }
}
