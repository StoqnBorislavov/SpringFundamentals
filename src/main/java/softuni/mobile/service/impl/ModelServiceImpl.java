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

            this.modelRepository.save(fiesta);
            this.modelRepository.save(escort);
        }

    }

    @Override
    public Model findById(Long id) {
        return this.modelRepository.findById(id).orElse(null);
    }
}
