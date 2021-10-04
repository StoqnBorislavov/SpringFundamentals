package softuni.mobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mobile.model.entity.Brand;
import softuni.mobile.repository.BrandRepository;
import softuni.mobile.service.BrandService;

import java.util.Optional;


@Service
public class BrandServiceImpl implements BrandService {


    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public void initializeBrands() {
        if(brandRepository.count() == 0){
            Brand ford = new Brand();
            ford.setName("Ford");
            this.brandRepository.saveAndFlush(ford);
        }
    }

    @Override
    public Brand findByName(String name) {
        return this.brandRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    }

}
