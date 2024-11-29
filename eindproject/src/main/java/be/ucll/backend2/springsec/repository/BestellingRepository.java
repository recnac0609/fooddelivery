package be.ucll.backend2.springsec.repository;

import be.ucll.backend2.springsec.entity.Bestelling;
import ch.qos.logback.core.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestellingRepository extends JpaRepository<Bestelling, Long> {
    List<Bestelling> findByKlantId(Long klantId);

    List<Bestelling> findByRestaurantId(Long restaurantId);

    List<Bestelling> findByStatus(Status status);
}