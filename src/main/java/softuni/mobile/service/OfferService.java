package softuni.mobile.service;

import softuni.mobile.model.entity.Offer;
import softuni.mobile.model.view.OfferDetailsView;
import softuni.mobile.model.view.OfferSummaryView;

import java.util.List;

public interface OfferService {
    void initializeOffers();

    List<OfferSummaryView> getAllOffers();

    OfferDetailsView findById(Long id);
}
