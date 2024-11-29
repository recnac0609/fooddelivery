package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Klant;
import be.ucll.backend2.springsec.repository.KlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class KlantService {

    private final KlantRepository klantRepository;

    @Autowired
    public KlantService(KlantRepository klantRepository) {
        this.klantRepository = klantRepository;
    }

    public Klant maakKlant(Klant klant) {
        return klantRepository.save(klant);
    }

    public Optional<Klant> getKlantById(Long id) {
        return klantRepository.findById(id);
    }

    public Optional<Klant> getKlantByEmail(String email) {
        return Optional.ofNullable(klantRepository.findByEmail(email));
    }
    public List<Klant> getAllUsers() {
        return klantRepository.findAll();
    }
    public void updateBestellingStatus(Long bestellingId, String status) {
        throw new UnsupportedOperationException("Nog niet geïmplementeerd");
    }
}

