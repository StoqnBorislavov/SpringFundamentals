package softuni.mobile.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.mobile.model.binding.UserLoginBindingModel;
import softuni.mobile.model.service.UserLoginServiceModel;
import softuni.mobile.service.UserService;

import javax.validation.Valid;

@Controller
public class UserLoginController {

    private final UserService userService;
    private static Logger LOGGER = LoggerFactory.getLogger(UserLoginController.class);

    @Autowired
    public UserLoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/login")
    public String login(Model model){
//        model.addAttribute("isExists", true);
        return "auth-login";
    }
    @PostMapping("/users/login")
    public String login(@Valid UserLoginBindingModel userLoginBindingModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult);
            return "redirect:/users/login";
        }
        boolean loginSuccessful = userService.login(new UserLoginServiceModel()
                .setUsername(userLoginBindingModel.getUsername())
                .setRawPassword(userLoginBindingModel.getPassword()));

        LOGGER.info("User tried to login. User with name {} tried to login. Success = {}?",
                userLoginBindingModel.getUsername(),
                loginSuccessful);

        if(!loginSuccessful){
            redirectAttributes
                    .addFlashAttribute("userLoginBindingModel", userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult)
                    .addFlashAttribute("isExists", true);

            return "redirect:login";
        }

        return "redirect:/";
    }

    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();

    }
}
