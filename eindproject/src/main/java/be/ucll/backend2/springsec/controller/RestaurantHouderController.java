package be.ucll.backend2.springsec.controller;

import be.ucll.backend2.springsec.entity.RestaurantHouder;
import be.ucll.backend2.springsec.service.RestaurantHouderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restauranthouders")
public class RestaurantHouderController {

    private final RestaurantHouderService restaurantHouderService;

    @Autowired
    public RestaurantHouderController(RestaurantHouderService restaurantHouderService) {
        this.restaurantHouderService = restaurantHouderService;
    }

    @PostMapping("/registreer")
    public ResponseEntity<RestaurantHouder> registreerRestaurantHouder(@RequestBody RestaurantHouder restaurantHouder) {
        RestaurantHouder createdRestaurantHouder = restaurantHouderService.registreerRestaurantHouder(restaurantHouder);
        return new ResponseEntity<>(createdRestaurantHouder, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantHouder> getRestaurantHouderById(@PathVariable Long id) {
        return restaurantHouderService.getRestaurantHouderById(id)
                .map(restaurantHouder -> new ResponseEntity<>(restaurantHouder, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
