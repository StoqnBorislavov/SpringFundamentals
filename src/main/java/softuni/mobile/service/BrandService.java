package softuni.mobile.service;

import softuni.mobile.model.entity.Brand;

import java.util.Optional;

public interface BrandService {
    void initializeBrands();

    Brand findByName(String name);
}
