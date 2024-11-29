package be.ucll.backend2.springsec.controller;

import be.ucll.backend2.springsec.entity.Klant;
import be.ucll.backend2.springsec.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/klanten")
public class KlantController {

    private final KlantService klantService;

    @Autowired
    public KlantController(KlantService klantService) {
        this.klantService = klantService;
    }

    @PostMapping("/registreer")
    public ResponseEntity<Klant> registreerKlant(@RequestBody Klant klant) {
        Klant createdKlant = klantService.maakKlant(klant);
        return new ResponseEntity<>(createdKlant, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Klant> getKlantById(@PathVariable Long id) {
        return klantService.getKlantById(id)
                .map(klant -> new ResponseEntity<>(klant, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}