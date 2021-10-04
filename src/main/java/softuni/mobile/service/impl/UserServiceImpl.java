package softuni.mobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.mobile.model.entity.User;
import softuni.mobile.model.entity.UserRole;
import softuni.mobile.model.enums.UserRoleEnum;
import softuni.mobile.model.service.UserLoginServiceModel;
import softuni.mobile.repository.UserRepository;
import softuni.mobile.repository.UserRoleRepository;
import softuni.mobile.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void initializeUsersAndRoles() {
        this.initializeUsersRoles();
        this.initializeUsers();
    }

    public void initializeUsers(){
        if (this.userRepository.count() == 0) {
            UserRole adminRole = this.userRoleRepository.findByRole(UserRoleEnum.ADMIN);
            UserRole userRole = this.userRoleRepository.findByRole(UserRoleEnum.USER);

            User admin = new User();
            admin.
                    setActive(true).
                    setUsername("admin").
                    setFirstName("Admin").
                    setLastName("Adminov").
                    setActive(true).
                    setPassword(passwordEncoder.encode("test")).
                    setRoles(Set.of(adminRole, userRole));

            userRepository.save(admin);

            User user = new User();
            user
                    .setUsername("pesho")
                    .setFirstName("Pesho")
                    .setLastName("Petrov")
                    .setPassword(passwordEncoder.encode("test"))
                    .setActive(true)
                    .setRoles(Set.of(userRole));

            userRepository.save(user);
        }
    }
    public void initializeUsersRoles() {
        if(this.userRoleRepository.count() == 0){
            UserRole admin = new UserRole();
            admin.setRole(UserRoleEnum.ADMIN);

            UserRole user = new UserRole();
            user.setRole(UserRoleEnum.USER);

            this.userRoleRepository.saveAll(List.of(admin, user));
        }
    }

    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {

        Optional<User> optionalUser = this.userRepository.findByUsername(loginServiceModel.getUsername());

        if(optionalUser.isEmpty()){
            return false;
        } else {
            return passwordEncoder.matches(loginServiceModel.getRawPassword(), optionalUser.get().getPassword());
        }
    }
}
