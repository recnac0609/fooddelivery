package be.ucll.backend2.springsec.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Bestelling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Klant klant;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bestelling")
    private List<BestellingsItem> items = new ArrayList<>();

    private Double totaalPrijs = 0.0;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING, KLANT_BEVESTIGD, RESTAURANT_BEVESTIGD
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Klant getKlant() {
        return klant;
    }

    public void setKlant(Klant klant) {
        this.klant = klant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<BestellingsItem> getItems() {
        return items;
    }

    public void setItems(List<BestellingsItem> items) {
        this.items = items;
    }

    public Double getTotaalPrijs() {
        return totaalPrijs;
    }

    public void setTotaalPrijs(Double totaalPrijs) {
        this.totaalPrijs = totaalPrijs;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}