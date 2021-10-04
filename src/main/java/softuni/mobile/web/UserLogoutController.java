package softuni.mobile.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.mobile.service.UserService;

@Controller
public class UserLogoutController {

    private final UserService userService;

    @Autowired
    public UserLogoutController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/logout")
    public String logout(){
        this.userService.logout();
        return "redirect:/";
    }
}
