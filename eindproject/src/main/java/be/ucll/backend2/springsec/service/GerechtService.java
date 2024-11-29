package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Gerecht;
import be.ucll.backend2.springsec.entity.RestaurantHouder;
import be.ucll.backend2.springsec.repository.GerechtRepository;
import be.ucll.backend2.springsec.repository.RestaurantHouderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GerechtService {

    @Autowired
    private GerechtRepository gerechtRepository;

    public Gerecht voegGerechtToe(Gerecht gerecht) {
        return gerechtRepository.save(gerecht);
    }

    public Optional<Gerecht> getGerechtById(Long id) {
        return gerechtRepository.findById(id);
    }

    public List<Gerecht> getAllGerechten() {
        return gerechtRepository.findAll();
    }

    public Gerecht updateGerecht(Long id, Gerecht gerechtDetails) {
        Gerecht gerecht = gerechtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gerecht niet gevonden voor id: " + id));

        gerecht.setNaam(gerechtDetails.getNaam());
        gerecht.setBeschrijving(gerechtDetails.getBeschrijving());
        gerecht.setPrijs(gerechtDetails.getPrijs());

        return gerechtRepository.save(gerecht);
    }
    public void verwijderGerecht(Long id) {
        Gerecht gerecht = gerechtRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gerecht niet gevonden voor id: " + id));

        gerechtRepository.delete(gerecht);
    }

    public List<Gerecht> getGerechtenByRestaurant(Long restaurantId) {
        return gerechtRepository.findByRestaurantId(restaurantId);
    }
}
