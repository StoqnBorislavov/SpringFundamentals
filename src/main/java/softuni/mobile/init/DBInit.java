package softuni.mobile.init;


import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.mobile.model.entity.Brand;
import softuni.mobile.model.entity.Model;
import softuni.mobile.model.enums.Category;
import softuni.mobile.repository.BrandRepository;

import java.time.Instant;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {

    private final BrandRepository brandRepository;

    public DBInit(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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
                    .setCreated(Instant.now());

            Model escort = new Model();
            escort.setBrand(ford).
                    setCategory(Category.CAR).
                    setName("Escort").
                    setStartYear(1967).
                    setEndYear(2004).
                    setImageUrl(
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/420px-Ford_Escort_RS2000_MkI.jpg")
                    .setCreated(Instant.now());
            ford.setModels(List.of(escort, fiesta));

            brandRepository.save(ford);
        }
    }
}
