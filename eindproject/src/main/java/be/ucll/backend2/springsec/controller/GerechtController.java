package be.ucll.backend2.springsec.controller;

import be.ucll.backend2.springsec.entity.Gerecht;
import be.ucll.backend2.springsec.service.GerechtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gerechten")
public class GerechtController {

    private final GerechtService gerechtService;

    @Autowired
    public GerechtController(GerechtService gerechtService) {
        this.gerechtService = gerechtService;
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Gerecht>> getGerechtenByRestaurant(@PathVariable Long restaurantId) {
        List<Gerecht> gerechten = gerechtService.getGerechtenByRestaurant(restaurantId);
        return new ResponseEntity<>(gerechten, HttpStatus.OK);
    }

    @PostMapping("/toevoegen")
    public ResponseEntity<Gerecht> voegGerechtToe(@RequestBody Gerecht gerecht) {
        Gerecht createdGerecht = gerechtService.voegGerechtToe(gerecht);
        return new ResponseEntity<>(createdGerecht, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Gerecht> updateGerecht(@PathVariable Long id, @RequestBody Gerecht gerecht) {
        Gerecht updatedGerecht = gerechtService.updateGerecht(id, gerecht);
        return new ResponseEntity<>(updatedGerecht, HttpStatus.OK);
    }

    @DeleteMapping("/verwijder/{id}")
    public ResponseEntity<Void> deleteGerecht(@PathVariable Long id) {
        gerechtService.verwijderGerecht(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}