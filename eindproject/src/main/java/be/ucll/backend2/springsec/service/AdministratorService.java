package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Administrator;
import be.ucll.backend2.springsec.repository.AdministratorRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.default.email}")
    private String defaultAdminEmail;

    @Autowired
    public AdministratorService(AdministratorRepository administratorRepository, PasswordEncoder passwordEncoder) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createDefaultAdminIfNotExists() {
        Optional<Administrator> adminOpt = administratorRepository.findByEmail(defaultAdminEmail);

        if (adminOpt.isEmpty()) {
            String randomPassword = generateRandomPassword(12);
            Administrator admin = new Administrator(defaultAdminEmail, passwordEncoder.encode(randomPassword));
            administratorRepository.save(admin);

            System.out.println("Default administrator aangemaakt.");
            System.out.println("E-mail: " + defaultAdminEmail);
            System.out.println("Wachtwoord: " + randomPassword);
        }
    }

    private String generateRandomPassword(int length) {
        SecureRandom random = new SecureRandom();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}
