package softuni.mobile.web;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import softuni.mobile.model.binding.OfferUpdateBindingModel;
import softuni.mobile.model.enums.EngineEnum;
import softuni.mobile.model.enums.TransmissionEnum;
import softuni.mobile.model.service.OfferUpdateServiceModel;
import softuni.mobile.model.view.OfferDetailsView;
import softuni.mobile.service.OfferService;

import javax.validation.Valid;

@Controller
public class OffersController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OffersController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
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
            return "redirect:/users/login";
        }

        OfferUpdateServiceModel serviceModel =
                modelMapper.map(offerModel, OfferUpdateServiceModel.class);
        serviceModel.setId(id);
        offerService.updateOffer(serviceModel);

        return "redirect:/offers/" + id + "/details";

    }

}
