package web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserControllerRest {
    private UserService userService;
    private RoleService roleService;
    @Autowired
    public UserControllerRest(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable("username") String username){
        return userService.findByUsername(username);
    }
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.findAllRoles();
    }

    @PostMapping
    public User addUser(@RequestBody User user){

        return userService.addUser( user);
    }

    @GetMapping("/rol/{role}")
    public Role getRole(@PathVariable("role") String role){
        return roleService.findByRole(role);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){

        return userService.updateUser(user);
    }
    @PostMapping("/delete")
    public void deleteUser(@RequestParam("id") Long id){

         userService.deleteUser(id);
    }
    @GetMapping("/id/{id}")
    public User getById(@PathVariable("id") Long id){
        return userService.getById(id);
    }



}
