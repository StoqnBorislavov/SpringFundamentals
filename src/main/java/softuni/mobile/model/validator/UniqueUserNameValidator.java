package softuni.mobile.model.validator;

import org.springframework.beans.factory.annotation.Autowired;
import softuni.mobile.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    private final UserService userService;

    @Autowired
    public UniqueUserNameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
       if(username == null){
           return true;
       }
        return userService.isUsernameFree(username);
    }
}
