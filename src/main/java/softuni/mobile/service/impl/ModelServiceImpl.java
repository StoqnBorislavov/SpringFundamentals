package softuni.mobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mobile.model.entity.Brand;
import softuni.mobile.model.entity.Model;
import softuni.mobile.model.enums.CategoryEnum;
import softuni.mobile.repository.ModelRepository;
import softuni.mobile.service.BrandService;
import softuni.mobile.service.ModelService;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandService brandService;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandService brandService) {
        this.modelRepository = modelRepository;
        this.brandService = brandService;
    }


    @Override
    public void initializeModels() {

        if(modelRepository.count() == 0){
            Brand ford = this.brandService.findByName("Ford");
            Brand bmw = this.brandService.findByName("BMW");
            Model fiesta = new Model();
            fiesta.setBrand(ford).
                    setCategory(CategoryEnum.CAR).
                    setName("Fiesta").
                    setStartYear(1976).
                    setImageUrl(
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/1920px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg");

            Model escort = new Model();
            escort.setBrand(ford).
                    setCategory(CategoryEnum.CAR).
                    setName("Escort").
                    setStartYear(1967).
                    setEndYear(2004).
                    setImageUrl(
                            "https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/420px-Ford_Escort_RS2000_MkI.jpg");
            ford.setModels(List.of(escort, fiesta));

            Model e46 = new Model();
            e46.setBrand(bmw).
                    setCategory(CategoryEnum.CAR).
                    setName("330 ci").
                    setStartYear(1998).
                    setEndYear(2006).
                    setImageUrl(
                            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.mad4wheels.com%2Fbmw%2F330ci-e46-performance-package-2005&psig=AOvVaw2Y21dfBySYqr5vvAD1mDHd&ust=1634317593106000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCOCYn9SxyvMCFQAAAAAdAAAAABAD");
            Model m3 = new Model();
            m3.setBrand(bmw).
                    setCategory(CategoryEnum.CAR).
                    setName("m3").
                    setStartYear(1999).
                    setEndYear(2006).
                    setImageUrl(
                            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwallpapercave.com%2Fbmw-e46-m3-wallpaper&psig=AOvVaw2Y21dfBySYqr5vvAD1mDHd&ust=1634317593106000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCOCYn9SxyvMCFQAAAAAdAAAAABAJ");

            bmw.setModels(List.of(e46, m3));

            this.modelRepository.save(fiesta);
            this.modelRepository.save(escort);
            this.modelRepository.save(m3);
            this.modelRepository.save(e46);
        }

    }

    @Override
    public Model findById(Long id) {
        return this.modelRepository.findById(id).orElse(null);
    }

    @Override
    public List<Model> findAllModels() {
        return this.modelRepository.findAll();
    }
}
