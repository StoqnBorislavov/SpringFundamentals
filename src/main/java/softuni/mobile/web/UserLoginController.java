package softuni.mobile.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import softuni.mobile.model.binding.UserLoginBindingModel;
import softuni.mobile.model.service.UserLoginServiceModel;
import softuni.mobile.service.UserService;

@Controller
public class UserLoginController {

    private final UserService userService;
    private static Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login(){
        return "auth-login";
    }
    @PostMapping("/users/login")
    public String login(UserLoginBindingModel userLoginBindingModel){

        boolean loginSuccessful = userService.login(new UserLoginServiceModel()
                .setUsername(userLoginBindingModel.getUsername())
                .setRawPassword(userLoginBindingModel.getPassword()));

        LOGGER.info("User tried to login. User with name {} tried to login. Success = {}?",
                userLoginBindingModel.getUsername(),
                loginSuccessful);

        return "redirect:/users/login";
    }
}
