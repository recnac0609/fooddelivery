package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Klant;
import be.ucll.backend2.springsec.repository.KlantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    public class KlantServiceTest {

        @Mock
        private KlantRepository klantRepository;

        @InjectMocks
        private KlantService klantService;

        private Klant klant;

        @BeforeEach
        void setUp() {
            klant = new Klant();
            klant.setId(1L);
            klant.setEmail("jan@example.com");
        }

        @Test
        void testRegistreerKlant() {
            when(klantRepository.findByEmail("jan@example.com")).thenReturn(null);
            when(klantRepository.save(klant)).thenReturn(klant);

            Klant geregistreerdeKlant = klantService.maakKlant(klant);

            assertThat(geregistreerdeKlant.getEmail()).isEqualTo("jan@example.com");
        }

    }
