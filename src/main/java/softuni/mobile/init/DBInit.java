package softuni.mobile.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import softuni.mobile.service.BrandService;
import softuni.mobile.service.ModelService;
import softuni.mobile.service.OfferService;
import softuni.mobile.service.UserService;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandService brandService;
    private final ModelService modelService;
    private final UserService userService;
    private final OfferService offerService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBInit(BrandService brandService, ModelService modelService, UserService userService, OfferService offerService, PasswordEncoder passwordEncoder) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.userService = userService;
        this.offerService = offerService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
     this.brandService.initializeBrands();
     this.modelService.initializeModels();
     this.userService.initializeUsersAndRoles();
     this.offerService.initializeOffers();
    }
}
