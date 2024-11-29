package be.ucll.backend2.springsec.service;

import be.ucll.backend2.springsec.entity.Restaurant;
import be.ucll.backend2.springsec.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setNaam("De Molen");
        when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

        Restaurant result = restaurantService.voegRestaurantToe(restaurant);

        assertNotNull(result, "The created restaurant should not be null");
        assertTrue(result.getNaam().equals("De Molen"), "Restaurant name should match");
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    public void testDeleteRestaurant() {

        Long restaurantId = 1L;

        restaurantService.deleteRestaurant(restaurantId);

        verify(restaurantRepository, times(1)).deleteById(restaurantId);
    }
}