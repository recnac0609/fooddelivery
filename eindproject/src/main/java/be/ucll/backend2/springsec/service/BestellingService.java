package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Bestelling;
import be.ucll.backend2.springsec.repository.BestellingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BestellingService {

    private final BestellingRepository bestellingRepository;

    @Autowired
    public BestellingService(BestellingRepository bestellingRepository) {
        this.bestellingRepository = bestellingRepository;
    }

    public Bestelling plaatsBestelling(Bestelling bestelling) {
        return bestellingRepository.save(bestelling);
    }

    public List<Bestelling> getBestellingenByKlantId(Long klantId) {
        return bestellingRepository.findByKlantId(klantId);
    }

    public Bestelling bevestigBestelling(Long bestellingId) {
        Bestelling bestelling = bestellingRepository.findById(bestellingId).orElseThrow(() -> new RuntimeException("Bestelling niet gevonden"));
        bestelling.setStatus(Bestelling.Status.KLANT_BEVESTIGD);
        return bestellingRepository.save(bestelling);
    }

    public void annuleerBestelling(Long bestellingId) {
        Bestelling bestelling = bestellingRepository.findById(bestellingId).orElseThrow(() -> new RuntimeException("Bestelling niet gevonden"));
        bestelling.setStatus(Bestelling.Status.PENDING);  // De status kan PENDING blijven als de bestelling wordt geannuleerd
        bestellingRepository.delete(bestelling);
    }
}
