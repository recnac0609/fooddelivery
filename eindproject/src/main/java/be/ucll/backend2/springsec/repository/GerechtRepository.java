package be.ucll.backend2.springsec.repository;

import be.ucll.backend2.springsec.entity.Gerecht;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GerechtRepository extends JpaRepository<Gerecht, Long> {
    List<Gerecht> findByNaam(String naam);

    List<Gerecht> findByRestaurantId(Long restaurantId);
}
