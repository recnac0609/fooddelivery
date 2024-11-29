package be.ucll.backend2.springsec.repository;

import be.ucll.backend2.springsec.entity.Klant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KlantRepository extends JpaRepository<Klant, Long> {

    Klant findByEmail(String email);


}
