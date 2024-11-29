package be.ucll.backend2.springsec.repository;

import be.ucll.backend2.springsec.entity.RestaurantHouder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantHouderRepository extends JpaRepository<RestaurantHouder, Long> {
    RestaurantHouder findByEmail(String email);
}