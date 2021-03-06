package softuni.mobile.web;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.mobile.model.binding.OfferAddBindingModel;
import softuni.mobile.model.binding.OfferUpdateBindingModel;
import softuni.mobile.model.enums.EngineEnum;
import softuni.mobile.model.enums.TransmissionEnum;
import softuni.mobile.model.service.OfferAddServiceModel;
import softuni.mobile.model.service.OfferUpdateServiceModel;
import softuni.mobile.model.view.OfferDetailsView;
import softuni.mobile.service.BrandService;
import softuni.mobile.service.ModelService;
import softuni.mobile.service.OfferService;
import softuni.mobile.service.impl.MobileleUser;

import javax.validation.Valid;

@Controller
public class OffersController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;
    private final ModelService modelService;


    public OffersController(OfferService offerService, ModelMapper modelMapper, BrandService brandService, ModelService modelService) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model){
        model.addAttribute("offers", this.offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/offers/{id}/details")
    public String showOffer(@PathVariable Long id, Model model){
        model.addAttribute("offer", this.offerService.findById(id));
        return "details";
    }

    //Delete
    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable Long id){
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    //Update
    @GetMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model){
        OfferDetailsView offerDetailsView = offerService.findById(id);


        OfferUpdateBindingModel offerModel =
                modelMapper.map(offerDetailsView, OfferUpdateBindingModel.class);
        offerModel.setId(id);

        model.addAttribute("offerModel", offerModel);
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        return "update";
    }


    @GetMapping("/offers/{id}/edit/errors")
    public String editOfferErrors(@PathVariable Long id, Model model){
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        return "update";
    }

    // We will update the object partial, because of that we use Patch.
    @PatchMapping("/offers/{id}/edit")
    public  String editOffer(@PathVariable Long id,
                             @Valid OfferUpdateBindingModel offerModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes
                    .addFlashAttribute("offerModel", offerModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerModel",
                            bindingResult);
            return "redirect:/offers/" + id + "/edit/errors";
        }

        OfferUpdateServiceModel serviceModel =
                modelMapper.map(offerModel, OfferUpdateServiceModel.class);
        serviceModel.setId(id);
        offerService.updateOffer(serviceModel);

        return "redirect:/offers/" + id + "/details";

    }

    @GetMapping("/offers/add")
    public String addOffer(Model model){
        if (!model.containsAttribute("offerAddBindingModel")) {
            model.addAttribute("offerAddBindingModel", new OfferAddBindingModel()).
                    addAttribute("brandsModels", brandService.findAllBrands());
        }
        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOfferConfirm(@Valid OfferAddBindingModel offerAddBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  @AuthenticationPrincipal MobileleUser user){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindingModel", bindingResult)
                    .addFlashAttribute("brandsModels", brandService.findAllBrands());
            return "redirect:add";
        }

        OfferAddServiceModel savedOfferAddServiceModel = offerService.addOffer(offerAddBindingModel, user.getUserIdentifier());
        return "redirect:/offers/" + savedOfferAddServiceModel.getId() + "/details";
    }

}
