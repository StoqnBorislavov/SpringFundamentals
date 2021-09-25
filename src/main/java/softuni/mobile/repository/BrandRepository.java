package softuni.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mobile.model.entity.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

}