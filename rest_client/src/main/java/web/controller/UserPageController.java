package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.Role;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserPageController {
    private UserService userService;

    @Autowired
    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUserPersonalPage(Model model, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        for (Role r:user.getRoles()) {
            if (r.getRole().equals("user")){
                model.addAttribute("username", username);
                return "/user_page";
            }
        }
       return "redirect:/logout";
    }
}
