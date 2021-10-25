package softuni.mobile.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import softuni.mobile.model.view.BrandViewModel;
import softuni.mobile.service.BrandService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BrandsController {

    private final BrandService brandService;
    private final ModelMapper modelMapper;


    @Autowired
    public BrandsController(BrandService brandService, ModelMapper modelMapper) {
        this.brandService = brandService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/brands/all")
    public String allBrands(Model model){
        List<BrandViewModel> brandViewModels =
                brandService.findAllBrands()
                        .stream()
                        .map(brandServiceModel -> modelMapper.map(brandServiceModel, BrandViewModel.class))
                .collect(Collectors.toList());
        model.addAttribute("allBrands", brandViewModels);
        return "brands";


    }
}
