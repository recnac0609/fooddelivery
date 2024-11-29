package be.ucll.backend2.springsec.controller;

import be.ucll.backend2.springsec.entity.Klant;
import be.ucll.backend2.springsec.service.KlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdministratorController {

    private final KlantService klantService;

    @Autowired
    public AdministratorController(KlantService klantService) {
        this.klantService = klantService;
    }

    @GetMapping("/admin/gebruikers")
    public ResponseEntity<List<Klant>> getAllUsers() {
        return ResponseEntity.ok(klantService.getAllUsers());
    }

    @PutMapping("/admin/bestellingen/{id}/status/{status}")
    public ResponseEntity<Void> updateBestellingStatus(@PathVariable Long id, @PathVariable String status) {
        klantService.updateBestellingStatus(id, status);
        return ResponseEntity.ok().build();
    }




}