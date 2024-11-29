package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Bestelling;
import be.ucll.backend2.springsec.repository.BestellingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

public class BestellingServiceTest {

    @Mock
    private BestellingRepository bestellingRepository;

    @InjectMocks
    private BestellingService bestellingService;

    private Bestelling bestelling;

    @BeforeEach
    void setUp() {
        bestelling = new Bestelling();
        bestelling.setId(1L);
        bestelling.setStatus(Bestelling.Status.PENDING);
    }

    @Test
    void testBevestigBestelling() {

        when(bestellingRepository.findById(1L)).thenReturn(java.util.Optional.of(bestelling));


        Bestelling bevestigdBestelling = bestellingService.bevestigBestelling(1L);


        assertThat(bevestigdBestelling.getStatus()).isEqualTo(Bestelling.Status.KLANT_BEVESTIGD);


        verify(bestellingRepository).save(bestelling);
    }

    @Test
    void testBevestigBestelling_BestellingNietGevonden() {

        when(bestellingRepository.findById(1L)).thenReturn(java.util.Optional.empty());


        assertThatThrownBy(() -> bestellingService.bevestigBestelling(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Bestelling niet gevonden");
    }







}
