package be.ucll.backend2.springsec.controller;

import be.ucll.backend2.springsec.entity.Bestelling;
import be.ucll.backend2.springsec.service.BestellingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/bestellingen")
public class BestellingController {

    private final BestellingService bestellingService;

    @Autowired
    public BestellingController(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }

    @PostMapping("/plaats")
    public ResponseEntity<Bestelling> plaatsBestelling(@RequestBody Bestelling bestelling) {
        Bestelling createdBestelling = bestellingService.plaatsBestelling(bestelling);
        return new ResponseEntity<>(createdBestelling, HttpStatus.CREATED);
    }

    @GetMapping("/klant/{klantId}")
    public ResponseEntity<List<Bestelling>> getBestellingenByKlant(@PathVariable Long klantId) {
        List<Bestelling> bestellingen = bestellingService.getBestellingenByKlantId(klantId);
        return new ResponseEntity<>(bestellingen, HttpStatus.OK);
    }

    @PutMapping("/bevestig/{bestellingId}")
    public ResponseEntity<Bestelling> bevestigBestelling(@PathVariable Long bestellingId) {
        Bestelling updatedBestelling = bestellingService.bevestigBestelling(bestellingId);
        return new ResponseEntity<>(updatedBestelling, HttpStatus.OK);
    }

    @DeleteMapping("/annuleer/{bestellingId}")
    public ResponseEntity<Void> annuleerBestelling(@PathVariable Long bestellingId) {
        bestellingService.annuleerBestelling(bestellingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
