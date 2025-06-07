package it.uninsubria.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for restaurant search criteria.
 * This class encapsulates the various search parameters that can be used to filter restaurants
 * in the searchRestaurant() functionality. It implements Serializable to allow
 * transfer between client and server components.
 * This class implements the Builder pattern to allow for easy, modular construction
 * of search criteria with only the parameters that are needed.
 *
 * @author Lorenzo Radice, 753252, CO
 */
public class SearchCriteriaDTO implements Serializable {
    /** Serial version UID for serialization compatibility */
    private static final long serialVersionUID = 1L;
    /** Type of cuisine to filter by */
    private CuisineType cuisineType;
    /** Minimum price range in euros */
    private Double minPrice;
    /** Maximum price range in euros */
    private Double maxPrice;
    /** Whether delivery service should be available */
    private Boolean deliveryAvailable;
    /** Whether online booking should be available */
    private Boolean onlineBookingAvailable;
    /** Minimum average rating (1-5 scale) */
    private Integer minRating;
    /** Geographic latitude for the search */
    private Double latitude;
    /** Geographic longitude for the search */
    private Double longitude;

    /**
     * Private constructor used by the Builder.
     */
    public SearchCriteriaDTO() {}

    /**
     * Creates a new Builder instance for constructing a SearchCriteriaDTO.
     *
     * @return A new Builder instance
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Validates whether this search criteria has the mandatory location information.
     * The latitude and longitude coordinates must be provided.
     *
     * @return true if the criteria contains valid location coordinates, false otherwise
     */
    public boolean hasValidCoordinates() {
        return latitude != null && longitude != null;
    }

    /**
     * Nested Builder class for constructing SearchCriteriaDTO instances.
     */
    public static class Builder {
        private final SearchCriteriaDTO criteria;

        /**
         * Constructor for the Builder.
         */
        private Builder() {
            criteria = new SearchCriteriaDTO();
        }

        /**
         * Sets the geographic coordinates for the search.
         * This is a mandatory parameter for all searches.
         *
         * @param latitude Geographic latitude
         * @param longitude Geographic longitude
         * @return This Builder instance for method chaining
         */
        public Builder coordinates(Double latitude, Double longitude) {
            criteria.setLatitude(latitude);
            criteria.setLongitude(longitude);
            return this;
        }

        /**
         * Sets the cuisine type filter.
         *
         * @param cuisineType The type of cuisine to filter by
         * @return This Builder instance for method chaining
         */
        public Builder cuisineType(CuisineType cuisineType) {
            criteria.setCuisineType(cuisineType);
            return this;
        }

        /**
         * Sets the price range filter.
         * Either minPrice or maxPrice can be null to indicate no lower or upper bound.
         * If both are provided, maxPrice must be greater than or equal to minPrice.
         *
         * @param minPrice Minimum price in euros (can be null)
         * @param maxPrice Maximum price in euros (can be null)
         * @return This Builder instance for method chaining
         * @throws IllegalArgumentException If maxPrice is less than minPrice when both are provided
         */
        public Builder priceRange(Double minPrice, Double maxPrice) {
            // check min<max
            if (minPrice != null && maxPrice != null && maxPrice < minPrice) {
                throw new IllegalArgumentException("Maximum price must be greater than or equal to minimum price");
            }

            criteria.setMinPrice(minPrice);
            criteria.setMaxPrice(maxPrice);
            return this;
        }

        /**
         * Sets the delivery service availability filter.
         *
         * @param available Whether delivery service should be available
         * @return This Builder instance for method chaining
         */
        public Builder deliveryAvailable(Boolean available) {
            criteria.setDeliveryAvailable(available);
            return this;
        }

        /**
         * Sets the online booking availability filter.
         *
         * @param available Whether online booking should be available
         * @return This Builder instance for method chaining
         */
        public Builder onlineBookingAvailable(Boolean available) {
            criteria.setOnlineBookingAvailable(available);
            return this;
        }

        /**
         * Sets the minimum rating filter.
         *
         * @param rating Minimum average rating (1-5 scale)
         * @return This Builder instance for method chaining
         */
        public Builder minRating(Integer rating) {
            if (rating != null && rating >= 1 && rating <= 5) {
                criteria.setMinRating(rating);
            }
            return this;
        }

        /**
         * Builds and returns the constructed SearchCriteriaDTO instance.
         *
         * @return The constructed SearchCriteriaDTO
         * @throws IllegalStateException if mandatory coordinates are not provided
         */
        public SearchCriteriaDTO build() {
            if (criteria.getLatitude() == null || criteria.getLongitude() == null) {
                throw new IllegalStateException("Coordinates (latitude and longitude) are mandatory for search criteria");
            }
            return criteria;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchCriteriaDTO{");
        sb.append("coordinates=[").append(latitude).append(", ").append(longitude).append("]");
        if (cuisineType != null) {
            sb.append(", cuisineType=").append(cuisineType);
        }
        if (minPrice != null || maxPrice != null) {
            sb.append(", priceRange=[");
            sb.append(minPrice != null ? minPrice : "any");
            sb.append(" to ");
            sb.append(maxPrice != null ? maxPrice : "any");
            sb.append("]");
        }
        if (deliveryAvailable != null) {
            sb.append(", deliveryAvailable=").append(deliveryAvailable);
        }
        if (onlineBookingAvailable != null) {
            sb.append(", onlineBookingAvailable=").append(onlineBookingAvailable);
        }
        if (minRating != null) {
            sb.append(", minRating=").append(minRating);
        }
        sb.append('}');
        return sb.toString();
    }
    /**
     * Gets the cuisine type filter.
     * @return The type of cuisine to filter by
     */
    public CuisineType getCuisineType() {
        return cuisineType;
    }
    /**
     * Sets the cuisine type filter.
     * @param cuisineType The type of cuisine to filter by
     */
    public void setCuisineType(CuisineType cuisineType) {
        this.cuisineType = cuisineType;
    }
    /**
     * Gets the minimum price range in euros.
     * @return Minimum price in euros, or null if not set
     */
    public Double getMinPrice() {
        return minPrice;
    }
    /**
     * Sets the minimum price range in euros.
     * @param minPrice Minimum price in euros, or null if not set
     */
    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }
    /**
     * Gets the maximum price range in euros.
     * @return Maximum price in euros, or null if not set
     */
    public Double getMaxPrice() {
        return maxPrice;
    }
    /**
     * Sets the maximum price range in euros.
     * @param maxPrice Maximum price in euros, or null if not set
     */
    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }
    /**
     * Gets whether delivery service should be available.
     * @return True if delivery is available, false otherwise, or null if not specified
     */
    public Boolean getDeliveryAvailable() {
        return deliveryAvailable;
    }
    /**
     * Sets whether delivery service should be available.
     * @param deliveryAvailable True if delivery is available, false otherwise
     */
    public void setDeliveryAvailable(Boolean deliveryAvailable) {
        this.deliveryAvailable = deliveryAvailable;
    }
    /**
     * Gets whether online booking should be available.
     * @return True if online booking is available, false otherwise, or null if not specified
     */
    public Boolean getOnlineBookingAvailable() {
        return onlineBookingAvailable;
    }
    /**
     * Sets whether online booking should be available.
     * @param onlineBookingAvailable True if online booking is available, false otherwise
     */
    public void setOnlineBookingAvailable(Boolean onlineBookingAvailable) {
        this.onlineBookingAvailable = onlineBookingAvailable;
    }
    /**
     * Gets the minimum average rating (1-5 scale).
     * @return Minimum rating, or null if not set
     */
    public Integer getMinRating() {
        return minRating;
    }
    /**
     * Sets the minimum average rating (1-5 scale).
     * @param minRating Minimum rating to set, must be between 1.0 and 5.0, or null if not set
     */
    public void setMinRating(Integer minRating) {
        this.minRating = minRating;
    }
    /**
     * Gets the geographic latitude for the search.
     * @return Latitude coordinate, or null if not set
     */
    public Double getLatitude() {
        return latitude;
    }
    /**
     * Sets the geographic latitude for the search.
     * @param latitude Latitude coordinate to set, cannot be null
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    /**
     * Gets the geographic longitude for the search.
     * @return Longitude coordinate, or null if not set
     */
    public Double getLongitude() {
        return longitude;
    }
    /**
     * Sets the geographic longitude for the search.
     * @param longitude Longitude coordinate to set, cannot be null
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
