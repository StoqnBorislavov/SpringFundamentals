package softuni.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mobile.model.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Model findByName(String model);
}
