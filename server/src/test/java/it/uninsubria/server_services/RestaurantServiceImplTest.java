package it.uninsubria.server_services;

import it.uninsubria.DBConnection;
import it.uninsubria.dto.AddressDTO;
import it.uninsubria.dto.CuisineType;
import it.uninsubria.dto.RestaurantDTO;
import it.uninsubria.dto.SearchCriteriaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class RestaurantServiceImplTest {

    @BeforeEach
    void premise() {
        String[] args = {"theknife", "password"};
        try {
            DBConnection.login(args);
        } catch (Exception e) {
            fail("DBConnection failed to login with valid credentials");
        }
    }

    @Test
    void searchRestaurants() {
        SearchCriteriaDTO criteria = SearchCriteriaDTO.builder()
                .coordinates(15.0, 5.0).deliveryAvailable(true)
                .build();

        List<RestaurantDTO> result = null;
        try {
            result = new RestaurantServiceImpl().searchRestaurants(criteria);
        } catch (RemoteException e) {
            // fail test
            fail("RemoteException thrown during searchRestaurants with valid criteria");
        }
        assertNotNull(result);
        assertNotEquals(0, result.size(), "Expected non-empty list of restaurants");
    }

    @Test
    void getFavoriteRestaurants() {
        // Happy path: valid userId returns non-null list
        String userId = "testUser";
        List<RestaurantDTO> result = null;
        try {
            result = new RestaurantServiceImpl().getFavoriteRestaurants(userId);
        } catch (RemoteException e) {
            // fail test
            fail("RemoteException thrown during getFavoriteRestaurants with valid userId");
        }
        assertNotNull(result);

        // Edge case: empty userId should not throw and return a list
        List<RestaurantDTO> emptyUserResult = null;
        try {
            emptyUserResult = new RestaurantServiceImpl().getFavoriteRestaurants("");
        } catch (RemoteException e) {
            fail("RemoteException thrown during getFavoriteRestaurants with empty userId");
        }
        assertNotNull(emptyUserResult);
    }

    @Test
    void getOwnedRestaurants() {
        // Happy path: valid userId returns non-null list
        String userId = "testUser";
        List<RestaurantDTO> result = null;
        try {
            result = new RestaurantServiceImpl().getOwnedRestaurants(userId);
        } catch (RemoteException e) {
            // fail test
            fail("RemoteException thrown during getOwnedRestaurants with valid userId");
        }
        assertNotNull(result);

        // Edge case: empty userId should not throw and return a list
        List<RestaurantDTO> emptyUserResult = null;
        try {
            emptyUserResult = new RestaurantServiceImpl().getOwnedRestaurants("");
        } catch (RemoteException e) {
            fail("RemoteException thrown during getOwnedRestaurants with empty userId");
        }
        assertNotNull(emptyUserResult);
    }

    @Test
    void getReviewedRestaurants() {
        // Happy path: valid userId returns non-null list
        String userId = "testUser";
        List<RestaurantDTO> result = null;
        try {
            result = new RestaurantServiceImpl().getReviewedRestaurants(userId);
        } catch (RemoteException e) {
            // fail test
            fail("RemoteException thrown during getReviewedRestaurants with valid userId");
        }
        assertNotNull(result);

        // Edge case: empty userId should not throw and return a list
        List<RestaurantDTO> emptyUserResult = null;
        try {
            emptyUserResult = new RestaurantServiceImpl().getReviewedRestaurants("");
        } catch (RemoteException e) {
            fail("RemoteException thrown during getReviewedRestaurants with empty userId");
        }
        assertNotNull(emptyUserResult);
    }

    @Test
    void addFavoriteRestaurant() {
        String userId = "Zion.Schroeder";
        String restaurantId = "1";
        boolean result = false;
        try {
            result = new RestaurantServiceImpl().addFavoriteRestaurant(userId, restaurantId);
        } catch (RemoteException e) {
            fail("RemoteException thrown during addFavoriteRestaurant with valid input");
        }
        assertTrue(result);
    }

    @Test
    void removeFavoriteRestaurant() {
        String userId = "Zion.Schroeder";
        String restaurantId = "1";
        boolean result = false;
        try {
            result = new RestaurantServiceImpl().removeFavoriteRestaurant(userId, restaurantId);
        } catch (RemoteException e) {
            fail("RemoteException thrown during removeFavoriteRestaurant with valid input");
        }
        assertTrue(result);

        // Edge case: empty userId or restaurantId should return false or not throw
        try {
            boolean emptyResult = new RestaurantServiceImpl().removeFavoriteRestaurant("", "");
        } catch (RemoteException e) {
            fail("RemoteException thrown during removeFavoriteRestaurant with empty input");
        }
    }

    @Test
    void createRestaurant() {
        String testName = "Test Restaurant";
        RestaurantDTO restaurant = new RestaurantDTO("Valerie_Treutel", testName, 10.0, true,
                false, CuisineType.COLOMBIAN, (new AddressDTO("Italy", "Como", "Via Roma 14", 12.0, 44.0)));
        RestaurantDTO result = null;
        try {
            result = new RestaurantServiceImpl().createRestaurant(restaurant, restaurant.getR_owner());
        } catch (RemoteException e) {
            fail("RemoteException thrown during createRestaurant with valid input");
        } catch (SecurityException e) {
            System.err.println("SecurityException: " + e.getMessage());
            fail("SecurityException thrown during createRestaurant with valid input");
        }
        assertNotNull(result);
        assertEquals(testName, result.getR_name());
    }
}