package it.uninsubria.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for Restaurant information.
 * This class is responsible for encapsulating restaurant data to transfer between client and server.
 * Uses public fields for direct access without getters and setters.
 *
 * @author Lorenzo Radice, 753252, CO
 */
public class RestaurantDTO implements Serializable {
    /** Serial version UID for serialization compatibility */
    private static final long serialVersionUID = 1L;
    /** Unique identifier for the restaurant */
    private String restaurant_id;
    /** Username of the restaurant owner */
    private String r_owner;
    /** Name of the restaurant */
    private String r_name;
    /** Average price in euros */
    private Double avg_price;
    /** Whether the restaurant offers delivery service */
    private Boolean delivery;
    /** Whether the restaurant allows online booking */
    private Boolean booking;
    /** Type of cuisine offered by the restaurant */
    private CuisineType r_type;
    /** Address information encapsulated in AddressDTO */
    private AddressDTO address;
    /** Rating of the restaurant */
    private Double rating;
    /** Reviews number of the restaurant */
    private Integer reviewsNumber;

    /**
     * Constructor with all fields.
     * @param restaurant_id Unique identifier for the restaurant
     * @param r_owner Username of the restaurant owner
     * @param r_name Name of the restaurant
     * @param avg_price Average price in euros
     * @param delivery Whether the restaurant offers delivery service
     * @param booking Whether the restaurant allows online booking
     * @param r_type Type of cuisine from the enumeration
     * @param address Address information encapsulated in AddressDTO
     */
    public RestaurantDTO(String restaurant_id, String r_owner, String r_name,
                           Double avg_price, Boolean delivery, Boolean booking,
                           CuisineType r_type, AddressDTO address) {
        this.restaurant_id = restaurant_id;
        this.r_owner = r_owner;
        this.r_name = r_name;
        this.avg_price = avg_price;
        this.delivery = delivery;
        this.booking = booking;
        this.r_type = r_type;
        this.address = address;
    }

    /**
     * Constructor without ID for creating a new restaurant.
     * The ID will be assigned by the database.
     * @param r_owner Username of the restaurant owner
     * @param r_name Name of the restaurant
     * @param avg_price Average price in euros
     * @param delivery Whether the restaurant offers delivery service
     * @param booking Whether the restaurant allows online booking
     * @param r_type Type of cuisine from the enumeration
     * @param address Address information encapsulated in AddressDTO
     */
    public RestaurantDTO(String r_owner, String r_name,
                           Double avg_price, Boolean delivery, Boolean booking,
                           CuisineType r_type, AddressDTO address) {
        this.restaurant_id = null; // ID will be assigned by the database
        this.r_owner = r_owner;
        this.r_name = r_name;
        this.avg_price = avg_price;
        this.delivery = delivery;
        this.booking = booking;
        this.r_type = r_type;
        this.address = address;
    }

    /**
     * Default constructor for serialization.
     * Initializes a completely empty RestaurantDTO.
     */
    public RestaurantDTO() {}

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "id='" + restaurant_id + '\'' +
                ", name='" + r_name + "', "
                + address + '\'' +
                ", avg_price=" + avg_price +
                ", delivery=" + delivery +
                ", online_booking=" + booking +
                ", cuisine=" + r_type +
                ", owner_usrId='" + r_owner + '\'' +
                '}';
    }

    /**
     * Getters and Setters for all fields.
     * These methods allow access to private fields while maintaining encapsulation.
     */
    public String getRestaurant_id() {
        return restaurant_id;
    }
    /**
     * Sets the unique identifier for the restaurant.
     * @param restaurant_id Unique identifier to set
     */
    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
    /**
     * Gets the username of the restaurant owner.
     * @return Username of the restaurant owner
     */
    public String getR_owner() {
        return r_owner;
    }
    /**
     * Sets the username of the restaurant owner.
     * @param r_owner Username to set
     */
    public void setR_owner(String r_owner) {
        this.r_owner = r_owner;
    }
    /**
     * Gets the name of the restaurant.
     * @return Name of the restaurant
     */
    public String getR_name() {
        return r_name;
    }
    /**
     * Sets the name of the restaurant.
     * @param r_name Name to set
     */
    public void setR_name(String r_name) {
        this.r_name = r_name;
    }
    /**
     * Gets the average price in euros.
     * @return Average price
     */
    public Double getAvg_price() {
        return avg_price;
    }
    /**
     * Sets the average price in euros.
     * @param avg_price Average price to set
     */
    public void setAvg_price(Double avg_price) {
        this.avg_price = avg_price;
    }
    /**
     * Gets whether the restaurant offers delivery service.
     * @return True if delivery is available, false otherwise
     */
    public Boolean getDelivery() {
        return delivery;
    }
    /**
     * Sets whether the restaurant offers delivery service.
     * @param delivery True if delivery is available, false otherwise
     */
    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }
    /**
     * Gets whether the restaurant allows online booking.
     * @return True if booking is available, false otherwise
     */
    public Boolean getBooking() {
        return booking;
    }
    /**
     * Sets whether the restaurant allows online booking.
     * @param booking True if booking is available, false otherwise
     */
    public void setBooking(Boolean booking) {
        this.booking = booking;
    }
    /**
     * Gets the type of cuisine offered by the restaurant.
     * @return CuisineType enumeration value
     */
    public CuisineType getR_type() {
        return r_type;
    }
    /**
     * Sets the type of cuisine offered by the restaurant.
     * @param r_type CuisineType enumeration value to set
     */
    public void setR_type(CuisineType r_type) {
        this.r_type = r_type;
    }
    /**
     * Gets the address information encapsulated in AddressDTO.
     * @return AddressDTO containing address details
     */
    public AddressDTO getAddress() {
        return address;
    }
    /**
     * Sets the address information encapsulated in AddressDTO.
     * @param address AddressDTO containing address details to set
     */
    public void setAddress(AddressDTO address) {
        this.address = address;
    }
    /**
     * Gets the rating of the restaurant.
     * This field is not part of the original DTO but is used for displaying ratings.
     * @return Rating of the restaurant, can be null if not set
     */
    public Double getAvgRating() {
        return this.rating;
    }
    /**
     * Sets the rating of the restaurant.
     * The rating must be between 0 and 5, inclusive. If the value is outside this range,
     * it will be set to null.
     * @param avgRating Average rating to set, can be null
     */
    public void setAvgRating(Double avgRating) {
        if (avgRating != null && avgRating >= 0 && avgRating <= 5) {
            this.rating = avgRating;
        } else {
            this.rating = null;
        }
    }
    /**
     * Gets the number of reviews for the restaurant.
     * This field is not part of the original DTO but is used for displaying review counts.
     * @return Number of reviews, can be null if not set
     */
    public Integer getReviewsNumber() {
        return reviewsNumber;
    }
    /**
     * Sets the number of reviews for the restaurant.
     * @param reviewsNumber Number of reviews to set, can be null
     */
    public void setReviewsNumber(Integer reviewsNumber) {
        this.reviewsNumber = reviewsNumber;
    }
}
