package be.ucll.backend2.springsec.repository;

import be.ucll.backend2.springsec.entity.BestellingsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BestellingItemRepository extends JpaRepository<BestellingsItem, Long> {

    List<BestellingsItem> findByBestellingId(Long bestellingId);
}