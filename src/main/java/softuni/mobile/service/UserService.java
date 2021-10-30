package softuni.mobile.service;

import softuni.mobile.model.entity.User;
import softuni.mobile.model.entity.UserRole;
import softuni.mobile.model.enums.UserRoleEnum;
import softuni.mobile.model.service.UserLoginServiceModel;
import softuni.mobile.model.service.UserRegisterServiceModel;

public interface UserService {

    void initializeUsersAndRoles();

    User findByUsername(String username);

    void registerAndLoginUser(UserRegisterServiceModel userRegisterServiceModel);

    boolean isUsernameFree(String username);
}
