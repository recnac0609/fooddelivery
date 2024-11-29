package be.ucll.backend2.springsec.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String naam;
    private String email;
    private String wachtwoord;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "gebruiker_roles", joinColumns = @JoinColumn(name = "gebruiker_id"))
    @Column(name = "role")
    private Set<String> rollen = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public Set<String> getRollen() {
        return rollen;
    }

    public void setRollen(Set<String> rollen) {
        this.rollen = rollen;
    }
}