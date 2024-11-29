package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Gerecht;
import be.ucll.backend2.springsec.repository.GerechtRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GerechtServiceTest {

    @Mock
    private GerechtRepository gerechtRepository;

    @InjectMocks
    private GerechtService gerechtService;

    private Gerecht gerecht;

    @BeforeEach
    void setUp() {
        gerecht = new Gerecht();
        gerecht.setNaam("Pizza");
        gerecht.setBeschrijving("Heerlijke pizza");
        gerecht.setPrijs(12.5);
    }

    @Test
    void testVoegGerechtToe() {
        when(gerechtRepository.save(gerecht)).thenReturn(gerecht);

        Gerecht result = gerechtService.voegGerechtToe(gerecht);

        assertNotNull(result);
        assertEquals("Pizza", result.getNaam());
        verify(gerechtRepository, times(1)).save(gerecht);
    }

    @Test
    void testGetGerechtById_GerechtBestaat() {
        when(gerechtRepository.findById(1L)).thenReturn(Optional.of(gerecht));

        Optional<Gerecht> result = gerechtService.getGerechtById(1L);

        assertTrue(result.isPresent());
        assertEquals("Pizza", result.get().getNaam());
        verify(gerechtRepository, times(1)).findById(1L);
    }

    @Test
    void testGetGerechtById_GerechtBestaatNiet() {
        when(gerechtRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Gerecht> result = gerechtService.getGerechtById(1L);

        assertFalse(result.isPresent());
        verify(gerechtRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateGerecht() {
        Gerecht bestaandeGerecht = new Gerecht();
        bestaandeGerecht.setId(1L);
        bestaandeGerecht.setNaam("Pizza");
        bestaandeGerecht.setBeschrijving("Heerlijke pizza");
        bestaandeGerecht.setPrijs(12.5);

        when(gerechtRepository.findById(1L)).thenReturn(Optional.of(bestaandeGerecht));
        when(gerechtRepository.save(bestaandeGerecht)).thenReturn(bestaandeGerecht);

        Gerecht bijgewerktGerecht = new Gerecht();
        bijgewerktGerecht.setNaam("Vegetarische Pizza");
        bijgewerktGerecht.setBeschrijving("Heerlijke vegetarische pizza");
        bijgewerktGerecht.setPrijs(14.0);

        Gerecht result = gerechtService.updateGerecht(1L, bijgewerktGerecht);

        assertEquals("Vegetarische Pizza", result.getNaam());
        assertEquals("Heerlijke vegetarische pizza", result.getBeschrijving());
        assertEquals(14.0, result.getPrijs());
        verify(gerechtRepository, times(1)).findById(1L);
        verify(gerechtRepository, times(1)).save(bestaandeGerecht);
    }

    @Test
    void testVerwijderGerecht() {
        Gerecht bestaandeGerecht = new Gerecht();
        bestaandeGerecht.setId(1L);
        bestaandeGerecht.setNaam("Pizza");
        bestaandeGerecht.setBeschrijving("Heerlijke pizza");
        bestaandeGerecht.setPrijs(12.5);

        when(gerechtRepository.findById(1L)).thenReturn(Optional.of(bestaandeGerecht));

        gerechtService.verwijderGerecht(1L);
        verify(gerechtRepository, times(1)).delete(bestaandeGerecht);
    }

    @Test
    void testVerwijderGerecht_GerechtBestaatNiet() {
        when(gerechtRepository.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            gerechtService.verwijderGerecht(1L);
        });
        assertEquals("Gerecht niet gevonden voor id: 1", exception.getMessage());
    }

}