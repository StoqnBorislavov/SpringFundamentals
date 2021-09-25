package softuni.mobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import softuni.mobile.model.entity.User;
import softuni.mobile.model.service.UserLoginServiceModel;
import softuni.mobile.repository.UserRepository;
import softuni.mobile.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }
    @Override
    public boolean login(UserLoginServiceModel loginServiceModel) {

        Optional<User> optionalUser = userRepository.findByUsername(loginServiceModel.getUsername());

        if(optionalUser.isEmpty()){
            return false;
        } else {
            return passwordEncoder.matches(loginServiceModel.getRawPassword(), optionalUser.get().getPassword());
        }
    }
}
