package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import web.repository.UserRepository;

import java.util.List;
@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    @Transactional
    @Override
    public User addUser(User user) {
            userRepository.save(user);
        return user;
    }
    @Transactional
    @Override
    public void deleteUser(Long id) {
            userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User updateUser(User user) {

        userRepository.save(user);
        return user;
    }
    @Transactional
    @Override
    public User getById(Long id) {
        return userRepository.findUserById(id);
    }
    @Transactional
    @Override
    public User getByUsernameAndPasswordService(String username, String password) {
        return userRepository.getByUsernameAndPassword(username, password);
    }
}
