package softuni.mobile.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.mobile.model.binding.UserRegisterBindingModel;
import softuni.mobile.model.service.UserRegisterServiceModel;
import softuni.mobile.service.UserService;

import javax.validation.Valid;
import java.net.http.HttpClient;

@Controller
public class UserRegistrationController {


    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRegistrationController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @ModelAttribute("userRegisterBindingModel")
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }


    @GetMapping("/users/register")
    public String registerUser() {

        return "auth-register";
    }

    @PostMapping("/users/register")
    public String register(@Valid UserRegisterBindingModel userRegisterBindingModel,
                           BindingResult result,
                           RedirectAttributes redirectAttributes,
                           Model model) {

        if(result.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", result);
            return "redirect:/users/register";
        }

        UserRegisterServiceModel serviceModel =
                modelMapper.map(userRegisterBindingModel, UserRegisterServiceModel.class);

//        if (!this.userService.isUsernameFree(serviceModel.getUsername())) {
//            redirectAttributes.addFlashAttribute("userNameOccupied", true);
//            return "redirect:/users/register";
//        } else {

            userService.registerAndLoginUser(serviceModel);

            return "redirect:/";
//        }
    }
}
