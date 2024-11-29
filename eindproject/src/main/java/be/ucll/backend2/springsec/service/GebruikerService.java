package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Gebruiker;
import be.ucll.backend2.springsec.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GebruikerService {

    private final GebruikerRepository gebruikerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public GebruikerService(GebruikerRepository gebruikerRepository, PasswordEncoder passwordEncoder) {
        this.gebruikerRepository = gebruikerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Gebruiker registreerGebruiker(Gebruiker gebruiker) {
        if (gebruikerRepository.findByEmail(gebruiker.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Gebruiker met dit e-mailadres bestaat al.");
        }
        gebruiker.setWachtwoord(passwordEncoder.encode(gebruiker.getWachtwoord()));
        return gebruikerRepository.save(gebruiker);
    }
}