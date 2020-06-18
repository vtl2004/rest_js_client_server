package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import web.model.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private UserServiceRest userServiceRest;
    @Autowired
    public UserDetailsServiceImpl(UserServiceRest userServiceRest) {
        this.userServiceRest = userServiceRest;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userServiceRest.findByUsername(username);
        if (user != null) {
            return user;
        } else throw new IllegalArgumentException("Required user not found!");
    }
}
