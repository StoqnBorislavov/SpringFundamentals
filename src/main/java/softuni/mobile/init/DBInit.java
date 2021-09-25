package softuni.mobile.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.mobile.model.entity.Brand;
import softuni.mobile.model.entity.Model;
import softuni.mobile.model.entity.User;
import softuni.mobile.model.enums.Category;
import softuni.mobile.repository.BrandRepository;
import softuni.mobile.repository.UserRepository;

import java.time.Instant;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandRepository brandRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBInit(BrandRepository brandRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.brandRepository = brandRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
     initializeBrandAndModels();
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

    private void initializeBrandAndModels(){
        if (brandRepository.count() == 0) {
            Brand ford = new Brand();
            ford.setName("Ford").setCreated(Instant.now());

            Model fiesta = new Model();
            fiesta.setBrand(ford).
                    setCategory(Category.CAR).
                    setName("Fiesta").
                    setStartYear(1976).
                    setImageUrl(
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/1920px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg")
            ;

            Model escort = new Model();
            escort.setBrand(ford).
                    setCategory(Category.CAR).
                    setName("Escort").
                    setStartYear(1967).
                    setEndYear(2004).
                    setImageUrl(
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/420px-Ford_Escort_RS2000_MkI.jpg")
            ;
            ford.setModels(List.of(escort, fiesta));

            brandRepository.save(ford);
        }
    }
}
