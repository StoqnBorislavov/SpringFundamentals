package softuni.mobile.service;

import softuni.mobile.model.entity.UserRole;
import softuni.mobile.model.enums.UserRoleEnum;
import softuni.mobile.model.service.UserLoginServiceModel;

public interface UserService {

    void initializeUsersAndRoles();

    boolean login(UserLoginServiceModel loginServiceModel);
}
