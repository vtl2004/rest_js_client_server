package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    List<User> findAllUsers();
    User addUser(User user);
    void deleteUser(Long id);
    User updateUser(User user);
    User getById(Long id);
    User getByUsernameAndPasswordService(String username, String password);

}
