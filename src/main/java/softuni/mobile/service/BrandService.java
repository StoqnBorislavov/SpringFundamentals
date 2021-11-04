package softuni.mobile.service;

import softuni.mobile.model.entity.Brand;
import softuni.mobile.model.view.BrandViewModel;

import java.util.List;

public interface BrandService {
    void initializeBrands();

    Brand findByName(String name);

    List<BrandViewModel> findAllBrands();
}
