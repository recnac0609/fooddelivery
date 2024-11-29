package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Restaurant;
import be.ucll.backend2.springsec.entity.RestaurantHouder;
import be.ucll.backend2.springsec.repository.RestaurantHouderRepository;
import be.ucll.backend2.springsec.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class RestaurantHouderServiceTest {

    @Mock
    private RestaurantHouderRepository restaurantHouderRepository;

    @InjectMocks
    private RestaurantHouderService restaurantHouderService;

    private RestaurantHouder restaurantHouder;

    @BeforeEach
    public void setUp() {
        restaurantHouder = new RestaurantHouder();
        restaurantHouder.setId(1L);
        restaurantHouder.setVoornaam("Jan");
        restaurantHouder.setAchternaam("Janssen");
        restaurantHouder.setEmail("jan.janssen@example.com");
        restaurantHouder.setTelefoonnummer("123456789");
    }

    @Test
    public void testRegistreerRestaurantHouder() {
        when(restaurantHouderRepository.save(restaurantHouder)).thenReturn(restaurantHouder);

        RestaurantHouder result = restaurantHouderService.registreerRestaurantHouder(restaurantHouder);

        assertNotNull(result);
        assertEquals("Jan", result.getVoornaam());
        assertEquals("Janssen", result.getAchternaam());
        verify(restaurantHouderRepository, times(1)).save(restaurantHouder);
    }

    @Test
    public void testGetRestaurantHouderById_Found() {
        when(restaurantHouderRepository.findById(1L)).thenReturn(Optional.of(restaurantHouder));

        Optional<RestaurantHouder> result = restaurantHouderService.getRestaurantHouderById(1L);

        assertTrue(result.isPresent());
        assertEquals("Jan", result.get().getVoornaam());
        assertEquals("Janssen", result.get().getAchternaam());
        verify(restaurantHouderRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetRestaurantHouderById_NotFound() {
        when(restaurantHouderRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<RestaurantHouder> result = restaurantHouderService.getRestaurantHouderById(1L);

        assertFalse(result.isPresent());
        verify(restaurantHouderRepository, times(1)).findById(1L);
    }
}
