package softuni.mobile.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.mobile.model.entity.Offer;
import softuni.mobile.model.enums.EngineEnum;
import softuni.mobile.model.enums.TransmissionEnum;
import softuni.mobile.model.view.OfferSummaryView;
import softuni.mobile.repository.OfferRepository;
import softuni.mobile.service.ModelService;
import softuni.mobile.service.OfferService;
import softuni.mobile.service.UserService;

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
                    setModel(this.modelService.findById(1L)).
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
                    setModel(this.modelService.findById(2L)).
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

    private OfferSummaryView map(Offer offer) {
        OfferSummaryView offerSummaryView = this.modelMapper.map(offer, OfferSummaryView.class);
        offerSummaryView.setModel(offer.getModel().getName());
        return offerSummaryView;
    }
}
