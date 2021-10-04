package softuni.mobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mobile.model.entity.UserRole;
import softuni.mobile.model.enums.UserRoleEnum;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByRole(UserRoleEnum role);
}
