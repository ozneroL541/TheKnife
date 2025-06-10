package it.uninsubria.utilclient;

import it.uninsubria.dto.AddressDTO;
import it.uninsubria.dto.CuisineType;
import it.uninsubria.dto.RestaurantDTO;
import it.uninsubria.dto.ReviewDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for client-side operations and mock data.
 * This class provides temporary mock data and utility functions for the client application.
 *
 * @author Lorenzo Radice
 */
public class ClientUtil {

    /**
     * Returns a mock list of restaurants for testing purposes.
     * TODO: Replace this method with actual RMI call to RestaurantService.searchRestaurants()
     * when the server implementation is ready.
     *
     * @return A list of mock restaurant data
     */
    public static List<RestaurantDTO> getRestaurantList() {
        List<RestaurantDTO> restaurants = new ArrayList<>();

        // Mock restaurant 1
        RestaurantDTO restaurant1 = new RestaurantDTO("1", "owner", "Osteria del Borgo", 35.0, true, true, CuisineType.ITALIAN,
                new AddressDTO("Italy", "Varese", "Via Roma 15",
                        45.8206, 8.8257));
        restaurants.add(restaurant1);

        // Mock restaurant 2
        RestaurantDTO restaurant2 = new RestaurantDTO("2", "owner2", "Sushi Zen", 45.0, false, true, CuisineType.JAPANESE,
                new AddressDTO("Italy", "Varese", "Corso Matteotti 8",
                        45.8189, 8.8245));
        restaurants.add(restaurant2);

        // Mock restaurant 3
        RestaurantDTO restaurant3 = new RestaurantDTO("3", "owner3", "La Brasserie", 28.0, true, false, CuisineType.FRENCH,
                new AddressDTO("Italy", "Varese", "Via Cairoli 22",
                        45.8195, 8.8263));
        restaurants.add(restaurant3);

        // Mock restaurant 4
        RestaurantDTO restaurant4 = new RestaurantDTO("4", "owner4", "Spice Garden", 22.0, true, true, CuisineType.INDIAN,
                new AddressDTO("Italy", "Varese", "Piazza San Vittore 5",
                        45.8178, 8.8234));
        restaurants.add(restaurant4);

        // Mock restaurant 5
        RestaurantDTO restaurant5 = new RestaurantDTO("5", "owner5", "El Toro Loco", 18.0, true, false, CuisineType.MEXICAN,
                new AddressDTO("Italy", "Varese", "Via Verdi 12",
                        45.8201, 8.8278));
        restaurants.add(restaurant5);

        return restaurants;
    }

    /**
     * Returns a mock list of reviews for a specific restaurant.
     * TODO: Replace this method with actual RMI call to ReviewService.getReviews(restaurantId)
     * when the server implementation is ready.
     *
     * @param restaurantId The ID of the restaurant to get reviews for
     * @return A list of mock review data for the restaurant
     */
    public static List<ReviewDTO> getReviewList(String restaurantId) {
        List<ReviewDTO> reviews = new ArrayList<>();

        switch (restaurantId) {
            case "1": // Osteria del Borgo
                reviews.add(new ReviewDTO("marco_rossi", "1",
                        5,
                        "Excellent traditional Italian cuisine! The pasta was perfectly cooked and the atmosphere was cozy. Highly recommended for a romantic dinner.",
                        "Thank you Marco! We're delighted you enjoyed your evening with us. We look forward to welcoming you back soon!"));

                reviews.add(new ReviewDTO("giulia_m", "1",
                        4,
                        "Good food but service was a bit slow. The risotto was amazing though, definitely worth the wait. Will come back.",
                        null));

                reviews.add(new ReviewDTO("tourist_2024", "1",
                        3,
                        "Authentic Italian experience! The staff was friendly and the wine selection was impressive. Perfect for our vacation dinner.",
                        "Grazie mille! It was our pleasure to make your vacation special. Buon viaggio!"));
                break;

            case "2": // Sushi Zen
                reviews.add(new ReviewDTO("sushi_lover", "2",
                        5,
                        "Best sushi in Varese! Fresh fish, expertly prepared. The chef's special was outstanding. A bit pricey but worth every euro.",
                        "Thank you for appreciating our commitment to quality. We source our fish daily to ensure the best experience."));

                reviews.add(new ReviewDTO("anna_b", "2",
                        4,
                        "Beautiful presentation and delicious food. The atmosphere is very zen-like and peaceful. Perfect for a quiet lunch.",
                        null));

                reviews.add(new ReviewDTO("foodie_critic", "2",
                        5,
                        "Impressive technique and authentic flavors. The omakase menu was a journey through Japanese cuisine. Highly recommend booking in advance.",
                        null));
                break;

            case "3": // La Brasserie
                reviews.add(new ReviewDTO("french_fan", "3",
                        3,
                        "Decent French bistro. The coq au vin was good but not exceptional. Nice wine list and friendly service.",
                        "Merci for your feedback! We're always working to improve our dishes. Please try our new seasonal menu next time."));

                reviews.add(new ReviewDTO("couple_date", "3",
                        4,
                        "Lovely atmosphere for our anniversary dinner. The escargot was perfectly prepared and the dessert was divine!",
                        "Félicitations on your anniversary! We're honored to have been part of your special celebration."));
                break;

            case "4": // Spice Garden
                reviews.add(new ReviewDTO("spice_enthusiast", "4",
                        4,
                        "Authentic Indian flavors! The curry had the perfect level of spice and the naan was freshly baked. Great value for money.",
                        "Thank you! Our chef uses traditional spice blends imported directly from India. We're glad you enjoyed the authentic taste."));

                reviews.add(new ReviewDTO("vegetarian_v", "4",
                        5,
                        "Excellent vegetarian options! The dal was creamy and flavorful. Staff was very accommodating with dietary requirements.",
                        null));

                reviews.add(new ReviewDTO("local_resident", "4",
                        4,
                        "Our go-to place for Indian food. Consistent quality and quick delivery service. The butter chicken is always perfect.",
                        "We appreciate your loyalty! It's customers like you that make us strive for excellence every day."));
                break;

            case "5": // El Toro Loco
                reviews.add(new ReviewDTO("mexican_food_lover", "5",
                        4,"Fun atmosphere and tasty food! The tacos were authentic and the margaritas were strong. Perfect for a casual night out.",
                        "¡Gracias amigo! We're happy you had a great time. Our bartender takes pride in those margaritas!"));

                reviews.add(new ReviewDTO("student_budget", "5",
                        4,"Great prices and generous portions! Perfect for students. The nachos were loaded with toppings.",
                        null));
                break;

            default:
                // Return empty list for unknown restaurant IDs
                break;
        }

        return reviews;
    }

    /**
     * Calculates the distance between two geographic points using the Haversine formula.
     *
     * @param lat1 Latitude of the first point
     * @param lon1 Longitude of the first point
     * @param lat2 Latitude of the second point
     * @param lon2 Longitude of the second point
     * @return Distance in kilometers
     */
    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371; // Earth's radius in kilometers

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }
}