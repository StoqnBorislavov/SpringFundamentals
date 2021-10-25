package softuni.mobile.service;

import softuni.mobile.model.entity.Brand;
import softuni.mobile.model.view.BrandViewModel;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    void initializeBrands();

    Brand findByName(String name);

    List<BrandViewModel> findAllBrands();
}
