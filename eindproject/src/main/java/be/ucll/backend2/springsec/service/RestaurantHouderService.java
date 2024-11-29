package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Restaurant;
import be.ucll.backend2.springsec.entity.RestaurantHouder;
import be.ucll.backend2.springsec.repository.RestaurantHouderRepository;
import be.ucll.backend2.springsec.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RestaurantHouderService {

    private final RestaurantHouderRepository restaurantHouderRepository;

    @Autowired
    public RestaurantHouderService(RestaurantHouderRepository restaurantHouderRepository) {
        this.restaurantHouderRepository = restaurantHouderRepository;
    }

    public RestaurantHouder registreerRestaurantHouder(RestaurantHouder restaurantHouder) {
        return restaurantHouderRepository.save(restaurantHouder);
    }

    public Optional<RestaurantHouder> getRestaurantHouderById(Long id) {
        return restaurantHouderRepository.findById(id);
    }
}
