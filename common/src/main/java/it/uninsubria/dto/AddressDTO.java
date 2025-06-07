package it.uninsubria.dto;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Data Transfer Object for Address information.
 * This class is responsible for encapsulating address data to transfer between client and server.
 * Uses public fields for direct access with minimal constructors for different use cases.
 *
 * @author Lorenzo Radice, 753252, CO
 */
public class AddressDTO implements Serializable {
    /** Serial version UID for serialization compatibility */
    private static final long serialVersionUID = 1L;
    /** Country of the address */
    private String country;
    /** City of the address */
    private String city;
    /** Street of the address */
    private String street;
    /** House Number of the address */
    private String houseNumber = null;
    /** Latitude of the address */
    private Double latitude;
    /** Longitude of the address */
    private Double longitude;

    /**
     * Default constructor.
     * Initializes a completely empty AddressDTO.
     */
    public AddressDTO() {}

    /**
     * Constructor with some address information.
     * Useful for full data transfer operations.
     *
     * @param country nation
     * @param city city
     * @param address address containing street and house number
     * @param latitude latitude
     * @param longitude longitude
     */
    public AddressDTO(String country, String city, String address, Double latitude, Double longitude) {
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        try {
            String[] parts = address.split(" ");
            if (parts.length > 1) {
                String[] copyOfRange = Arrays.copyOfRange(parts, 1, parts.length);
                this.street = String.join(" ", copyOfRange).trim().replace("'", "");
                this.houseNumber = parts[0].trim().replace("'", "");
            }
        } catch (Exception e) {
            this.street = address;
            this.houseNumber = null;
        }
    }
    /**
     * Constructor with all address information.
     * Useful for full data transfer operations.
     *
     * @param country User's nation of residence
     * @param city User's city of residence
     * @param address User's street address
     * @param houseNumber User's house number
     */
    public AddressDTO(String country, String city, String address, String houseNumber) {
        this.country = country;
        this.city = city;
        this.street = address;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "nation='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'';
    }

    /**
     * Returns the full address as a formatted string.
     * @return Formatted string containing the full address.
     */
    public String getFullAddress() {
        return country + ", " + city + ", " + street + (houseNumber != null ? ", " + houseNumber : "");
    }
    /**
     * Getters and Setters for all fields.
     * These methods allow access to private fields while maintaining encapsulation.
     */
    public String getCountry() {
        return country;
    }
    /**
     * Sets the country of the address.
     * @param country Country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }
    /**
     * Gets the city of the address.
     * @return City of the address
     */
    public String getCity() {
        return city;
    }
    /**
     * Sets the city of the address.
     * @param city City to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * Gets the street of the address.
     * @return Street of the address
     */
    public String getStreet() {
        return street;
    }
/**
     * Sets the street of the address.
     * @param street Street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }
    /**
     * Gets the house number of the address.
     * @return House number of the address
     */
    public String getHouseNumber() {
        return houseNumber;
    }
    /**
     * Sets the house number of the address.
     * @param houseNumber House number to set
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
    /**
     * Gets the latitude of the address.
     * @return Latitude of the address
     */
    public Double getLatitude() {
        return latitude;
    }
    /**
     * Sets the latitude of the address.
     * @param latitude Latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    /**
     * Gets the longitude of the address.
     * @return Longitude of the address
     */
    public Double getLongitude() {
        return longitude;
    }
    /**
     * Sets the longitude of the address.
     * @param longitude Longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
