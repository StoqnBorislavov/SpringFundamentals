package softuni.mobile.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mobile.model.binding.OfferAddBindingModel;
import softuni.mobile.model.entity.Model;
import softuni.mobile.model.entity.Offer;
import softuni.mobile.model.enums.EngineEnum;
import softuni.mobile.model.enums.TransmissionEnum;
import softuni.mobile.model.service.OfferAddServiceModel;
import softuni.mobile.model.service.OfferUpdateServiceModel;
import softuni.mobile.model.view.OfferDetailsView;
import softuni.mobile.model.view.OfferSummaryView;
import softuni.mobile.repository.OfferRepository;
import softuni.mobile.service.ModelService;
import softuni.mobile.service.OfferService;
import softuni.mobile.service.UserService;
import softuni.mobile.web.exception.ObjectNotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ModelService modelService;
    private final UserService userService;

    @Autowired
    private OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, ModelService modelService, UserService userService) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.modelService = modelService;
        this.userService = userService;
    }

    @Override
    public void initializeOffers() {
        //TODO
        if(this.offerRepository.count() == 0){
            Offer offer1 = new Offer();
            offer1.
                    setModel(this.modelService.findById(2L)).
                    setEngine(EngineEnum.GASOLINE).
                    setTransmission(TransmissionEnum.MANUAL).
                    setMileage(22500).
                    setYear(1992).
                    setPrice(120000).
                    setDescription("The car is Escort RS Cosworth model. Externally rare. Well maintained. The car is in very good condition.").
                    setSeller(this.userService.findByUsername("pesho")).
                    setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/1996_Ford_Escort_RS_Cosworth_2.0_Front.jpg/280px-1996_Ford_Escort_RS_Cosworth_2.0_Front.jpg");
            Offer offer2 = new Offer();
            offer2.
                    setModel(this.modelService.findById(1L)).
                    setEngine(EngineEnum.DIESEL).
                    setTransmission(TransmissionEnum.AUTOMATIC).
                    setMileage(1200).
                    setYear(2019).
                    setPrice(12500).
                    setDescription("The car is almost intouch. It is with 3 keys and all needed documents.").
                    setSeller(this.userService.findByUsername("admin")).
                    setImageUrl("https://images.ams.bg/images/galleries/107476/ford-fiesta-1460777781_big.jpg");

            this.offerRepository.saveAll(List.of(offer1, offer2));
        }
    }

    @Override
    public List<OfferSummaryView> getAllOffers() {
        return this.offerRepository.
                findAll().
                stream().
                map(this::map).
                collect(Collectors.toList());

    }

    @Override
    public OfferDetailsView findById(Long id) {
        OfferDetailsView offerDetailsView = this.offerRepository.findById(id).map(this::mapDetailsView).get();
        return offerDetailsView;
    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);
    }

    @Override
    public void updateOffer(OfferUpdateServiceModel offerModel) {
        Offer offer = offerRepository.findById(offerModel.getId())
                .orElseThrow(()->
                        new ObjectNotFoundException("Offer with " + offerModel.getId() + " not found!"));

        offer
                .setPrice(offerModel.getPrice())
                .setDescription(offerModel.getDescription())
                .setEngine(offerModel.getEngine())
                .setImageUrl(offerModel.getImageUrl())
                .setMileage(offerModel.getMileage())
                .setTransmission(offerModel.getTransmission())
                .setYear(offerModel.getYear());

        offerRepository.save(offer);
    }

    @Override
    public OfferAddServiceModel addOffer(OfferAddBindingModel offerAddBindingModel, String ownerId) {
        OfferAddServiceModel offerAddServiceModel = modelMapper.map(offerAddBindingModel, OfferAddServiceModel.class);
        Offer newOffer = modelMapper.map(offerAddServiceModel, Offer.class);
        newOffer.setCreated(Instant.now());
        newOffer.setId(null);
        newOffer.setSeller(userService.findByUsername(ownerId));
        Model model = modelService.findById(offerAddBindingModel.getModelId());
        newOffer.setModel(model);
        Offer savedOffer = offerRepository.save(newOffer);
        return modelMapper.map(savedOffer, OfferAddServiceModel.class);
    }

    private OfferSummaryView map(Offer offer) {
        OfferSummaryView offerSummaryView = this.modelMapper.map(offer, OfferSummaryView.class);
        offerSummaryView.setModel(offer.getModel().getName());
        offerSummaryView.setBrand(offer.getModel().getBrand().getName());
        return offerSummaryView;
    }

    private OfferDetailsView mapDetailsView(Offer offer) {
        OfferDetailsView offerDetailsView = this.modelMapper.map(offer, OfferDetailsView.class);
        offerDetailsView.setModel(offer.getModel().getName());
        offerDetailsView.setBrand(offer.getModel().getBrand().getName());
        offerDetailsView.setSellerFullName(offer.getSeller().getFirstName() + " " + offer.getSeller().getLastName());
        return offerDetailsView;
    }
}
