package softuni.mobile.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mobile.model.entity.Brand;
import softuni.mobile.model.entity.Model;
import softuni.mobile.model.view.BrandViewModel;
import softuni.mobile.model.view.ModelViewModel;
import softuni.mobile.repository.BrandRepository;
import softuni.mobile.service.BrandService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BrandServiceImpl implements BrandService {


    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initializeBrands() {
        if(brandRepository.count() == 0){
            Brand ford = new Brand();
            ford.setName("Ford");
            this.brandRepository.saveAndFlush(ford);
            Brand bmw = new Brand();
            bmw.setName("BMW");
            this.brandRepository.saveAndFlush(bmw);
            Brand audi = new Brand();
            audi.setName("Audi");
            this.brandRepository.saveAndFlush(audi);
            Brand mercedes = new Brand();
            mercedes.setName("Mercedes");
            this.brandRepository.saveAndFlush(mercedes);
            Brand opel = new Brand();
            opel.setName("Opel");
            this.brandRepository.saveAndFlush(opel);
        }
    }

    @Override
    public Brand findByName(String name) {
        return this.brandRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<BrandViewModel> findAllBrands() {
        return this.brandRepository.findAll()
                .stream()
                .map(brand -> {
                    BrandViewModel brandViewModel = new BrandViewModel();
                    brandViewModel.setName(brand.getName());
                    brandViewModel.setModels(
                            brand.getModels()
                                    .stream()
                                    .map(this::map)
                                    .collect(Collectors.toList()));
                            return brandViewModel;

                }).collect(Collectors.toList());
    }

    private ModelViewModel map(Model model){
        return modelMapper.map(model, ModelViewModel.class);
    }

}
