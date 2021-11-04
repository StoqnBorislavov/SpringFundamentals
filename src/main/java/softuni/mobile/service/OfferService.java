package softuni.mobile.service;

import softuni.mobile.model.binding.OfferAddBindingModel;
import softuni.mobile.model.service.OfferAddServiceModel;
import softuni.mobile.model.service.OfferUpdateServiceModel;
import softuni.mobile.model.view.OfferDetailsView;
import softuni.mobile.model.view.OfferSummaryView;

import java.util.List;

public interface OfferService {
    void initializeOffers();

    List<OfferSummaryView> getAllOffers();

    OfferDetailsView findById(Long id);

    void deleteOffer(Long id);

    void updateOffer(OfferUpdateServiceModel offerModel);

    OfferAddServiceModel addOffer(OfferAddBindingModel offerAddBindingModel, String ownerId);
}
