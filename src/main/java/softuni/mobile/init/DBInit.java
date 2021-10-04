package softuni.mobile.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.mobile.model.entity.User;
import softuni.mobile.repository.BrandRepository;
import softuni.mobile.repository.UserRepository;
import softuni.mobile.service.BrandService;
import softuni.mobile.service.ModelService;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandService brandService;
    private final ModelService modelService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBInit(BrandService brandService, ModelService modelService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
     this.brandService.initializeBrands();
     this.modelService.initializeModels();
     initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {
            User admin = new User();
            admin.
                    setActive(true).
                    setUsername("admin").
                    setFirstName("Admin").setLastName("Adminov").
                    setPassword(passwordEncoder.encode("test"));

            userRepository.save(admin);
        }
    }
}
