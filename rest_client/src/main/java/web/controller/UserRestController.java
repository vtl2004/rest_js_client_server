package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/users")
public class UserRestController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping
    public User addUser(User user, @RequestParam(name = "role") String[] roles, Authentication authentication) throws URISyntaxException {
        Set<Role> rolSet = new HashSet<>();
        for (String strRole : roles) {
            Role role = roleService.findByRole(strRole);
            rolSet.add(role);
        }
        user.setRoles(rolSet);
        return userService.addUser(user);
    }

    @PutMapping
    public User updateUser(@ModelAttribute User user, @RequestParam(name = "role") String[] roles){
        Set<Role> rolSett = new HashSet<>();
        for (String strRole : roles) {
            Role role = roleService.findByRole(strRole);
            rolSett.add(role);
        }
        user.setRoles(rolSett);
        return userService.updateUser(user);
    }

    @PostMapping("delete")
    public  void deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
    }


    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){

        return userService.getById(id);
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles(){

        return roleService.findAllRoles();
    }
}
