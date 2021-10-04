package softuni.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mobile.model.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
}
