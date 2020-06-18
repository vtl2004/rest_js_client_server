package web.service;

import org.springframework.security.core.Authentication;
import web.model.Role;
import web.model.User;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

public interface UserService {
    User findByUsername(String username);
    List<User> findAllUsers();
    User addUser(User user) ;
    void deleteUser(Long id);
    User updateUser(User user);
    User getById(Long id);
    User getByUsernameAndPasswordService(String username, String password);

}
